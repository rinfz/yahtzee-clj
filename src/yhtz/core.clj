(ns yhtz.core
  (:require [clojure.string :as str]))

(defn roll-dice []
  (inc (rand-int 6)))

(defn roll-n [n]
  (repeatedly n roll-dice))

(defn keep-dice [hand indexes]
  (map #(nth hand %) (map dec indexes)))

(defn play-go [current turn]
  (let [hand (concat current (roll-n (- 5 (count current))))
        next-turn (dec turn)] ; this is what actually rolls the hand
    (if (= next-turn 0)
      hand
      (do
        (println hand)
        (println "Which to keep?")
        (recur (keep-dice hand (map #(Integer/parseInt %) (str/split (read-line) #" "))) next-turn)))))

(defn -main [& args]
  (let [hand (play-go [] 3)]
    (println (doall hand))))