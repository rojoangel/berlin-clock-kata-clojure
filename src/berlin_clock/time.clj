(ns berlin-clock.time
  (:require [clojure.string :as str]))

(defn- parse-int [str]
  (Integer/parseInt str))

(defn- to-elements [time]
  (map parse-int (str/split time #":")))

(defn minutes [time]
  (->> time
       (to-elements)
       (second)))

(defn hours [time]
  (->> time
       (to-elements)
       (first)))

(defn seconds [time]
  (->> time
       (to-elements)
       (last)))

(defn to-single-minutes [time]
  (-> time
      (minutes)
      (mod 5)))

(defn to-five-minutes [time]
  (-> time
      (minutes)
      (quot 5)))

(defn to-single-hours [time]
  (-> time
      (hours)
      (mod 5)))

(defn to-five-hours [time]
  (-> time
      (hours)
      (quot 5)))

(defn to-seconds [time]
  (-> time
      (seconds)
      (mod 2)))
