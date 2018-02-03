(ns berlin-clock.digital-time
  (:require [clojure.string :as str]))

(defn seconds [time]
  (+ (apply + (map #(if (= \Y %) 0 5) (subs time 0 0)))
     (apply + (map #(if (= \Y %) 0 1) (subs time 0 1)))))

(defn minutes [time]
  (+ (apply + (map #(if (= \O %) 0 5) (subs time 9 20)))
     (apply + (map #(if (= \O %) 0 1) (subs time 20 24)))))

(defn hours [time]
  (+ (apply + (map #(if (= \O %) 0 5) (subs time 2 6)))
     (apply + (map #(if (= \O %) 0 1) (subs time 6 10)))))

(defn convert [time]
  (str/join ":" (map #(format "%02d" %) ((juxt hours minutes seconds) time))))
