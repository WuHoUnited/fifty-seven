(ns fifty-seven.util)

(defn prompt!
  [message]
  (print message)
  (flush)
  (read-line))

(defn prompt-for-value!
  [& {:keys [prompt converter fail-prompt]}]
  (loop [string (prompt! prompt)]
    (let [converted (converter string)]
      (if (some? converted)
        converted
        (recur (prompt! fail-prompt))))))

(defmacro ^:private try-or-nil [& body]
  `(try
     ~@body
     (catch Exception e#)))

(defn try-parse-number [string]
  (try-or-nil (Long/parseLong string 10)))

(defn- parse-long [string]
  (Long/parseLong string))

(defn prompt-number! [prompt]
  (-> (prompt! prompt)
      parse-long))

(defn prompt-number-safe! [prompt]
  (prompt-for-value!
   :prompt prompt
   :converter try-parse-number
   :fail-prompt prompt))
