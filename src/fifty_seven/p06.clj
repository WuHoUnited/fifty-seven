(ns fifty-seven.p06
  (:import [java.time LocalDate]))

(defn- parse-int [string]
  (Integer/parseInt string))

(defn- read-number [prompt]
  (print prompt)
  (flush)
  (parse-int (read-line)))

(defn- get-current-year []
  (.getYear (LocalDate/now)))

(defn- calculate-retirement-message [current-age retirement-age]
  (let [current-year (get-current-year)
        years-until-retirement (- retirement-age current-age)
        retirement-year (+ current-year years-until-retirement)
        second-line (if (neg? years-until-retirement)
                      "You can already retire!"
                      (str "It's " current-year ", so you can retire in " retirement-year "."))]
    (str "You have " years-until-retirement " years left until you can retire.\n"
         second-line)))

(defn -main [& args]
  (let [current-age (read-number "What is your current age? ")
        retirement-age (read-number "At what age would you like to retire? ")
        output (calculate-retirement-message current-age retirement-age)]
    (println output)))
