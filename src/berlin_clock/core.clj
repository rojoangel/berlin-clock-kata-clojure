(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn- parse-int [str]
  (Integer/parseInt str))

(defn- to-elements [time]
  (map parse-int (str/split time #":")))

(defn- minutes [time]
  (->> time
       (to-elements)
       (second)))

(defn- hours [time]
  (->> time
       (to-elements)
       (first)))

(defn- seconds [time]
  (->> time
       (to-elements)
       (last)))

(defn to-berlin-single-minutes-row [time]
  (let [single-minutes (-> time
                           (minutes)
                           (mod 5))]
    (apply str (concat (take single-minutes (cycle [\Y])) (repeat (- 4 single-minutes) \O)))))

(defn to-berlin-five-minutes-row [time]
  (let [five-minutes (-> time
                         (minutes)
                         (quot 5))]
    (apply str (concat (take five-minutes (cycle [\Y \Y \R])) (repeat (- 11 five-minutes) \O)))))

(defn to-berlin-single-hours-row [time]
  (let [single-hours (-> time
                         (hours)
                         (mod 5))]
    (apply str (concat (take single-hours (cycle [\R])) (repeat (- 4 single-hours) \O)))))

(defn to-berlin-five-hours-row [time]
  (let [five-hours (-> time
                       (hours)
                       (quot 5))]
    (apply str (concat (take five-hours (cycle [\R])) (repeat (- 4 five-hours) \O)))))

(defn to-berlin-seconds-lamp [time]
  (let [seconds (-> time
                    (seconds)
                    (mod 2))]
    (apply str (concat (take seconds (cycle [\O])) (repeat (- 1 seconds) \Y)))))