(ns fifty-seven.p05
  (:require [clojure.edn :as edn]
            [clojure.string :as string]
            [fifty-seven.util :as util]))

(def ^:private operators
  (-> "config/p05.edn" slurp edn/read-string :operators))

(defn- try-parse-natural-number [string]
  (let [n (util/try-parse-number string)]
    (if (and n
             (<= 0 n))
      n)))

(defn- read-number-with-prompt! [prompt]
  (util/prompt-for-value! :prompt prompt
                          :converter try-parse-natural-number
                          :fail-prompt "You must enter a natural number. "))

(defn- calculate-output [n1 n2]
  (->> operators
       (map (fn [op]
              (let [result ((resolve op) n1 n2)]
                (str n1 " " (str op) " " n2 " = " result))))
       (string/join \newline)))

(defn -main [& args]
  (let [n1 (read-number-with-prompt! "What is the first number? ")
        n2 (read-number-with-prompt! "What is the second number? ")
        output (calculate-output n1 n2)]
    (println output)))
