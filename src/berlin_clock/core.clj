(ns berlin-clock.core
  (:require [berlin-clock.time :as time]))

(defn- row [time & {:keys [row-length on-pattern fill-lamp time-conversion-f]}]
  (let [value (time-conversion-f time)]
    (apply str (concat (take value (cycle on-pattern)) (repeat (- row-length value) fill-lamp)))))

(defn to-berlin-single-minutes-row [time]
  (row time
       :row-length 4
       :on-pattern [\Y]
       :fill-lamp \O
       :time-conversion-f time/to-single-minutes))

(defn to-berlin-five-minutes-row [time]
  (let [five-minutes (time/to-five-minutes time)]
    (apply str (concat (take five-minutes (cycle [\Y \Y \R])) (repeat (- 11 five-minutes) \O)))))

(defn to-berlin-single-hours-row [time]
  (let [single-hours (time/to-single-hours time)]
    (apply str (concat (take single-hours (cycle [\R])) (repeat (- 4 single-hours) \O)))))

(defn to-berlin-five-hours-row [time]
  (let [five-hours (time/to-five-hours time)]
    (apply str (concat (take five-hours (cycle [\R])) (repeat (- 4 five-hours) \O)))))

(defn to-berlin-seconds-lamp [time]
  (let [seconds (time/to-seconds time)]
    (apply str (concat (take seconds (cycle [\O])) (repeat (- 1 seconds) \Y)))))