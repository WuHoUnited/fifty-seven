(ns fifty-seven.p01
  (:require [clojure.edn :as edn]))

(def ^:private config (-> "config/p01.edn" slurp edn/read-string))

(def ^:private default-greeting
  (:default-greeting config))

(def ^:private greetings
  (:greetings config))

(defn- get-greeting [name]
  (let [greeting (get greetings
                      name default-greeting)]
    (format greeting name)))

(defn- prompt-for-name []
  (print "What is your name? ")
  (flush)
  (read-line))

(defn -main [& args]
  (let [name (prompt-for-name)
        greeting (get-greeting name)]
    (println greeting)))
