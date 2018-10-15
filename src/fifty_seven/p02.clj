(ns fifty-seven.p02)

(defn- read-non-empty-line []
  (let [line (read-line)]
    (if (empty? line)
      (do (println "You must enter a non empty string.")
          (recur))
      line)))

(defn- get-string []
  (print "What is the input string? ")
  (flush)
  (read-non-empty-line))

(defn- generate-output [string]
  (str string " has " (count string) " characters."))

(defn -main [& args]
  (let [string (get-string)]
    (println (generate-output string))))
