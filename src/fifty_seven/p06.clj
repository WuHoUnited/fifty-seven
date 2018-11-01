(ns fifty-seven.p06
  (:require [fifty-seven.util :as util])
  (:import [java.time LocalDate]))

(defn- get-current-year! []
  (.getYear (LocalDate/now)))

(defn- calculate-retirement-message [current-age retirement-age current-year]
  (let [years-until-retirement (- retirement-age current-age)
        retirement-year (+ current-year years-until-retirement)
        second-line (if (neg? years-until-retirement)
                      "You can already retire!"
                      (str "It's " current-year ", so you can retire in " retirement-year "."))]
    (str "You have " years-until-retirement " years left until you can retire.\n"
         second-line)))

(defn -main [& args]
  (let [current-age (util/prompt-number! "What is your current age? ")
        retirement-age (util/prompt-number! "At what age would you like to retire? ")
        current-year (get-current-year!)
        output (calculate-retirement-message current-age retirement-age current-year)]
    (println output)))
