(ns fifty-seven.p07)

(def ^:private conversion-constant
  0.09290304)

(defn- read-int [prompt]
  (print prompt)
  (flush)
  (Integer/parseInt (read-line) 10))

(defn- format-double [n]
  (format "%.3f" n))

(defn- generate-output [length width]
  (let [area-in-feet (* length width)
        area-in-meters (* area-in-feet conversion-constant)]
    (str "You entered dimensions of " length " feet by " width " feet.\n"
         "The area is\n"
         area-in-feet " square feet\n"
         (format-double area-in-meters) " square meters")))

(defn -main [& args]
  (let [length (read-int "What is the length of the room in feet? ")
        width (read-int "What is the width of the room in feet? ")
        output (generate-output length width)]
    (println output)))
