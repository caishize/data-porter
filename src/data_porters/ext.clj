(ns data-porters.ext
  (:require [clojure.java.jdbc :as sql]))
;  (import 'java.util.concurrent.atomic.AtomicInteger)

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
    (def lines-count 0)
    (sql/with-query-results rs
      ["SELECT * FROM t1 limit 20000000;"]
      (doseq [row rs]
        (def lines-count (inc lines-count))
        (if (== (mod lines-count 10000) 0)
          (println lines-count)
        )
      )
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
      ((reset! lines (count @rows))
      (println @lines))
    )
  )
;  (def lines-count 0)
;  (doseq [row rows]
;    (def lines-count (+ 1 lines-count)))
;  (println lines-count)
  (println "bye write")
)
