(ns fifty-seven.p10
  (:require [clojure.edn :as edn]
            [fifty-seven.util :as util]))

(def ^:private tax-rate
  (-> "config/p10.edn"
      slurp
      edn/read-string
      :tax-rate))

(defn- number-or-none-converter [string]
  (if (= "" string)
    :none
    (util/try-or-nil (bigdec string))))

(defn- get-item! [n]
  (let [user-index (inc n)
        price (util/prompt-for-value!
               :prompt (str "Enter the price of item " user-index ": ")
               :converter number-or-none-converter
               :fail-prompt "You must enter a price")]
    (if (not= :none price)
      {:price price
       :quantity (util/prompt-number-safe!
                  (str "Enter the quantity of item " user-index ": "))})))

(defn- get-items! []
  (->> (range)
       (map get-item!)
       (take-while some?)))

(defn- calculate-item-price [{:keys [price quantity]}]
  (* price quantity))

(defn- sum [coll]
  (apply + coll))

(defn- calculate-tax [amount]
  (* amount tax-rate))

(defn- calculate-invoice [items]
  (let [subtotal (->> items
                      (map calculate-item-price)
                      sum)
        tax (calculate-tax subtotal)]
    {:subtotal subtotal
     :tax tax
     :total (+ subtotal tax)}))

(defn- format-currency [n]
  (format "%.2f" n))

(defn- format-invoice [{:keys [subtotal tax total]}]
  (str "Subtotal: $"
       (format-currency subtotal)
       "\nTax: $"
       (format-currency tax)
       "\nTotal: $"
       (format-currency total)))

(defn -main [& args]
  (-> (get-items!)
      calculate-invoice
      format-invoice
      println))
