(ns fifty-seven.p03
  (:require [clojure.edn :as edn]
            [clojure.string :as string]))

(def ^:private config
  (-> "config/p03.edn" slurp edn/read-string))

(def ^:private quotes
  (:quotes config))

(defn- format-quote [{:keys [quote author]}]
  (str author " says, " \" quote \"))

(defn- generate-output []
  (->> quotes
       (map format-quote)
       (string/join "\n")))

(defn -main [& args]
  (println (generate-output)))
