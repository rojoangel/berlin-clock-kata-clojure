(ns berlin-clock.digital-time
  (:require [clojure.string :as str]))

(defn seconds [time]
  (let [seconds-lamp (first time)]
    (if (= \Y seconds-lamp)
      0
      1)))

(defn minutes [time]
  (let [single-minutes-row (subs time 20 24)
        five-minutes-row (subs time 9 20)]
    (+ (apply + (map #(if (= \O %) 0 1) single-minutes-row))
       (apply + (map #(if (= \O %) 0 5) five-minutes-row)))))

(defn hours [time]
  (let [single-hours-row (subs time 6 10)
        five-hours-row (subs time 2 6)]
    (+ (apply + (map #(if (= \O %) 0 1) single-hours-row))
       (apply + (map #(if (= \O %) 0 5) five-hours-row)))))

(defn convert [time]
  (str/join ":" (map #(format "%02d" %) ((juxt hours minutes seconds) time))))
