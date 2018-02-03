(ns berlin-clock.digital-time
  (:require [clojure.string :as str]))

(defn- on? [lamp]
  (not= \O lamp))

(defn seconds [time]
  (+ (apply + (map #(if (not (on? %)) 5 0) (subs time 0 0)))
     (apply + (map #(if (not (on? %)) 1 0) (subs time 0 1)))))

(defn minutes [time]
  (+ (apply + (map #(if (on? %) 5 0) (subs time 9 20)))
     (apply + (map #(if (on? %) 1 0) (subs time 20 24)))))

(defn hours [time]
  (+ (apply + (map #(if (on? %) 5 0) (subs time 2 6)))
     (apply + (map #(if (on? %) 1 0) (subs time 6 10)))))

(defn convert [time]
  (str/join ":" (map #(format "%02d" %) ((juxt hours minutes seconds) time))))
