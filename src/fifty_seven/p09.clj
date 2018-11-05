(ns fifty-seven.p09
  (:require [clojure.edn :as edn]
            [fifty-seven.util :as util]))

(def ^:private square-feet-per-gallon
  (-> "config/p09.edn"
      slurp
      edn/read-string
      :square-feet-per-gallon))

(defn- floor [n]
  (bigint n))

(defn- ceil [n]
  (let [floor-n (floor n)]
    (if (== n floor-n)
      floor-n
      (inc floor-n))))

(defn- calculate-gallons-needed [area]
  (let [gallons-needed (/ area square-feet-per-gallon)]
    (ceil gallons-needed)))

(defn- format-output [area gallons]
  (str "You will need to purchase "
       gallons
       " gallons of paint to cover "
       area
       " square feet."))

(defn -main [& args]
  (let [width (util/prompt-number-safe! "Please enter the length in feet: ")
        height (util/prompt-number-safe! "Please enter the width in feet: ")
        area (* width height)
        gallons (calculate-gallons-needed area)
        output (format-output area gallons)]
    (println output)))
