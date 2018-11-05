(ns fifty-seven.p08
  (:require [clojure.edn :as edn]
            [fifty-seven.util :as util]))

(def ^:private slices-per-pizza
  (-> "config/p08.edn"
      slurp
      edn/read-string
      :slices-per-pizza))

(defn- pizza-summary [people pizzas]
  (let [total-slices (* slices-per-pizza pizzas)
        pieces-per-person (quot total-slices people)
        leftover-slices (mod total-slices people)]
    {:people people
     :pizzas pizzas
     :pieces-per-person pieces-per-person
     :leftover-slices leftover-slices}))

(defn- is-are-pluralization [n]
  (if (== 1 n)
    "is"
    "are"))

(defn- piece-pluralization [n]
  (if (== 1 n)
    "piece"
    "pieces"))

(defn- format-pizza-summary
  [{:keys [people pizzas pieces-per-person leftover-slices]}]
  (str people
       " people with "
       pizzas
       " pizzas.\nEach person gets "
       pieces-per-person
       " "
       (piece-pluralization pieces-per-person)
       " of pizza.\nThere "
       (is-are-pluralization leftover-slices)
       " "
       leftover-slices
       " leftover "
       (piece-pluralization leftover-slices)
       "."))

(defn -main [& args]
  (let [people (util/prompt-number-safe! "How many people? ")
        pizzas (util/prompt-number-safe! "How many pizzas do you have? ")]
    (-> (pizza-summary people pizzas)
        format-pizza-summary
        println)))
