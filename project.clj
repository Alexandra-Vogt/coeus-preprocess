(defproject coeus-preprocess "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.csv "0.1.2"]
                 [org.apache.commons/commons-io "1.3.2"]]
  :main ^:skip-aot coeus-preprocess.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
