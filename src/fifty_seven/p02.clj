(ns fifty-seven.p02
  (:require [fifty-seven.util :as util]))

(defn- non-empty-string-converter [string]
  (if (seq string)
    string))

(defn- get-string! []
  (util/prompt-for-value!
   :prompt "What is the input string? "
   :converter non-empty-string-converter
   :fail-prompt "You must enter a non empty string. "))

(defn- generate-output [string]
  (str string " has " (count string) " characters."))

(defn -main [& args]
  (let [string (get-string!)]
    (println (generate-output string))))
