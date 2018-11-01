(ns fifty-seven.p01
  (:require [clojure.edn :as edn]
            [fifty-seven.util :as util]))

(def ^:private config (-> "config/p01.edn" slurp edn/read-string))

(def ^:private default-greeting
  (:default-greeting config))

(def ^:private greetings
  (:greetings config))

(defn- get-greeting [name]
  (let [greeting (get greetings
                      name
                      default-greeting)]
    (format greeting name)))

(defn -main [& args]
  (-> (util/prompt! "What is your name? ")
      get-greeting
      println))
