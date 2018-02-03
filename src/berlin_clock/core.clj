(ns berlin-clock.core
  (:require [berlin-clock.time :as time]
            [clojure.string :as str]))

(defn- row [time & {:keys [row-length on-pattern fill-lamp time-conversion-f]}]
  (let [value (time-conversion-f time)]
    (apply str (concat (take value (cycle on-pattern)) (repeat (- row-length value) fill-lamp)))))

(defn single-minutes-row [time]
  (row time
       :row-length 4
       :on-pattern [\Y]
       :fill-lamp \O
       :time-conversion-f time/to-single-minutes))

(defn five-minutes-row [time]
  (row time
       :row-length 11
       :on-pattern [\Y \Y \R]
       :fill-lamp \O
       :time-conversion-f time/to-five-minutes))

(defn single-hours-row [time]
  (row time
       :row-length 4
       :on-pattern [\R]
       :fill-lamp \O
       :time-conversion-f time/to-single-hours))

(defn five-hours-row [time]
  (row time
       :row-length 4
       :on-pattern [\R]
       :fill-lamp \O
       :time-conversion-f time/to-five-hours))

(defn seconds-lamp [time]
  (row time
       :row-length 1
       :on-pattern [\O]
       :fill-lamp \Y
       :time-conversion-f time/to-seconds))

(defn to-berlin-time [time]
  (->> time
       ((juxt seconds-lamp five-hours-row single-hours-row five-minutes-row single-minutes-row))
       (apply str)))

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
