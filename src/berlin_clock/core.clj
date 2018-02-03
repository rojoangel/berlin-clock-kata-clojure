(ns berlin-clock.core
  (:require [berlin-clock.time :as time]
            [clojure.string :as str]))

(defn digital-seconds [time]
  (let [seconds-lamp (first time)]
    (if (= \Y seconds-lamp)
      0
      1)))

(defn digital-minutes [time]
  (let [single-minutes-row (take-last 4 time)
        five-minutes-row (take-last 11 (drop-last 4 time))]
    (+ (apply + (map #(if (= \O %) 0 1) single-minutes-row))
       (apply + (map #(if (= \O %) 0 5) five-minutes-row)))))

(defn digital-hours [time]
  (let [single-hours-row (subs time 6 10)
        five-hours-row (subs time 2 6)]
    (+ (apply + (map #(if (= \O %) 0 1) single-hours-row))
       (apply + (map #(if (= \O %) 0 5) five-hours-row)))))

(defn to-digital-time [time]
  (str/join ":" (map #(format "%02d" %) ((juxt  digital-hours digital-minutes digital-seconds) time))))
