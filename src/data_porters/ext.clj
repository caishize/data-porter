(ns data-porters.ext
  (:require [clojure.java.jdbc :as sql]))

(def ^:dynamic rows (atom []))

(defn ext-mysql
  "extract mysql"
  [& args]
  (println "ext-mysql!")
  
  (def mysql-db {:subprotocol "mysql"
                 :subname "//10.12.218.199:3306/test"
                 :user "hive"
                 :password "mypassword"})

;  (sql/with-connection mysql-db
;    (sql/insert-records :fruit
;      {:name "Apple" :appearance "rosy" :cost 24}
;      {:name "Orange" :appearance "round" :cost 49}))

  (sql/with-connection mysql-db
    (sql/with-query-results rs
      ["SELECT * FROM t1 limit 2000000;"]
      (doseq [row rs]
        (reset! rows (into [row] @rows)))
    )
  )
)


(defn write-data
  "write data"
  [& args]
  (println "write data!")
  (def lines (atom 0))
  (while (< (count @rows) 1000000)
    (if (and (== (mod (count @rows) 10000) 0) (> (count @rows) @lines))
      (reset! lines (atom (count @rows)))
      (println @lines)
    )
  )
;  (def lines-count 0)
;  (doseq [row rows]
;    (def lines-count (+ 1 lines-count)))
;  (println lines-count)
)
