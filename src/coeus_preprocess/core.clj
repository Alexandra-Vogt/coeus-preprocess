(ns coeus-preprocess.core
  (:gen-class)
  (:require [clojure.data.csv :as csv]
            [util.gzip :as gzip]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]))


(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            (repeat (count (rest csv-data))))
       (rest csv-data)))

(defn get-raw-eurostat-data
  "Gets raw eurostat data."
  []
  (-> "https://ec.europa.eu/eurostat/estat-navtree-portlet-prod/BulkDownloadListing?file=data/ef_lsk_bovine.tsv.gz"
      (io/input-stream)
      (gzip/gzipped-input-stream->str  "UTF-8")))

(defn raw-data->csv-data
  "converts eurostat data into a more sane format."
  [data]
  (let [csv-data (csv/read-csv data :separator \tab)]
    (map concat
         (map (fn [table] (-> table
                              (first)
                              (csv/read-csv)
                              (flatten)))
              csv-data)
         (map rest csv-data))))

(defn -main
  "I don't do a whole lot ... yet."
  []
  (->> (get-raw-eurostat-data)
       (take 2)
       (raw-data->csv-data)
       (csv-data->maps)

       ;; first
       ;; (->> ;; First row is the header
       ;;       (map keyword) ;; Drop if you want string keys instead
       ;;       (repeat))
       (map :n_head)
       (pprint/pprint)))
