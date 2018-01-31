(ns berlin-clock.core
  (:require [berlin-clock.time :as time]))

(defn to-berlin-single-minutes-row [time]
  (let [single-minutes (-> time
                           (time/minutes)
                           (mod 5))]
    (apply str (concat (take single-minutes (cycle [\Y])) (repeat (- 4 single-minutes) \O)))))

(defn to-berlin-five-minutes-row [time]
  (let [five-minutes (-> time
                         (time/minutes)
                         (quot 5))]
    (apply str (concat (take five-minutes (cycle [\Y \Y \R])) (repeat (- 11 five-minutes) \O)))))

(defn to-berlin-single-hours-row [time]
  (let [single-hours (-> time
                         (time/hours)
                         (mod 5))]
    (apply str (concat (take single-hours (cycle [\R])) (repeat (- 4 single-hours) \O)))))

(defn to-berlin-five-hours-row [time]
  (let [five-hours (-> time
                       (time/hours)
                       (quot 5))]
    (apply str (concat (take five-hours (cycle [\R])) (repeat (- 4 five-hours) \O)))))

(defn to-berlin-seconds-lamp [time]
  (let [seconds (-> time
                    (time/seconds)
                    (mod 2))]
    (apply str (concat (take seconds (cycle [\O])) (repeat (- 1 seconds) \Y)))))