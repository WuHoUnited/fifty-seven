(ns fifty-seven.core
  (:gen-class))

(defn -main [problem & args]
  (let [padded-problem (format "%02d" (Integer/parseInt problem))
        namespace-name (str "fifty-seven.p" padded-problem)
        namespace-symbol (symbol namespace-name)
        method-symbol (symbol namespace-name "-main")]
    (require namespace-symbol)
    (apply (find-var method-symbol) args)))
