(ns fifty-seven.p04
  (:require [clojure.edn :as edn]
            [clojure.string :as string]))

(def ^:private story
  (-> "config/p04.edn" slurp edn/read-string :story))

(def ^:private vowels
  #{\a \e \i \o \u})

(defn- single-article [word]
  (let [first-letter (first word)]
    (if (vowels first-letter)
      "a"
      "an")))

(defn- extract-prompts [story]
  (filter keyword? story))

(defn- prompt-single [type]
  (let [name (name type)
        prefix (single-article name)]
    (print (str "Enter " prefix " " name ": "))
    (flush)
    (read-line)))

(defn- get-blanks [story]
  (->> story
       extract-prompts
       (map prompt-single)
       doall))

(defn- reify-story [story user-data]
  (let [story-fragments (->> story
                             (reduce (fn [{:keys [fragments user-data]}
                                          fragment]
                                       (if (keyword? fragment)
                                         {:fragments (conj fragments (first user-data))
                                          :user-data (rest user-data)}
                                         {:fragments (conj fragments fragment)
                                          :user-data user-data}))
                                     {:fragments []
                                      :user-data user-data})
                             :fragments)] 
    (string/join story-fragments)))

(defn -main [& args]
  (let [user-data (get-blanks story)
        reified-story (reify-story story user-data)]
    (println reified-story)))
