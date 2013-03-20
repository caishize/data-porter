(ns data-porters.core
  (:gen-class)
  (:use [data-porters.ext])
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (let [thread1 (Thread. #(ext-mysql))
        thread2 (Thread. #(write-data))]
    (.start thread1)
    (Thread/sleep 3000)
   (.start thread2)
  )
  (println "Bye, World!"))
