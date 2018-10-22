(ns fifty-seven.p05
  (:require [clojure.edn :as edn]
            [clojure.string :as string]))

(def ^:private operators
  (-> "config/p05.edn" slurp edn/read-string :operators))

(defmacro ^:private try-or-nil [& body]
  `(try
     ~@body
     (catch Exception e#)))

(defn- try-parse-number [string]
  (try-or-nil (Integer/parseInt string 10)))

(defn- try-parse-natural-number [string]
  (let [n (try-parse-number string)]
    (if (and n
             (<= 0 n))
      n)))

(defn- read-natural-number []
  (let [line (read-line)
        number (try-parse-natural-number line)]
    (if (nil? number)
      (do (println "You must a natural number.")
          (recur))
      number)))

(defn- read-number-with-prompt [prompt]
  (print prompt)
  (flush)
  (read-natural-number))

(defn- calculate-output [n1 n2]
  (->> operators
       (map (fn [op]
              (let [result ((resolve op) n1 n2)]
                (str n1 " " (str op) " " n2 " = " result))))
       (string/join \newline)))

(defn -main [& args]
  (let [n1 (read-number-with-prompt "What is the first number? ")
        n2 (read-number-with-prompt "What is the second number? ")
        output (calculate-output n1 n2)]
    (println output)))
