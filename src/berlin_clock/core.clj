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
  (row time
       :row-length 11
       :on-pattern [\Y \Y \R]
       :fill-lamp \O
       :time-conversion-f time/to-five-minutes))

(defn to-berlin-single-hours-row [time]
  (row time
       :row-length 4
       :on-pattern [\R]
       :fill-lamp \O
       :time-conversion-f time/to-single-hours))

(defn to-berlin-five-hours-row [time]
  (row time
       :row-length 4
       :on-pattern [\R]
       :fill-lamp \O
       :time-conversion-f time/to-five-hours))

(defn to-berlin-seconds-lamp [time]
  (row time
       :row-length 1
       :on-pattern [\O]
       :fill-lamp \Y
       :time-conversion-f time/to-seconds))
