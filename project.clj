(defproject data-porters "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                ,[org.clojure/java.jdbc "0.2.3"]
                ,[mysql/mysql-connector-java "5.1.6"]
                ,[com.microsoft/sqljdbc4 "3.0"]]
  :aot [data-porters.ext, data-porters.core]
  :main data-porters.core)
