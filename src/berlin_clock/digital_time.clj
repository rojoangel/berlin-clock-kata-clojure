(ns berlin-clock.digital-time
  (:require [clojure.string :as str]))

(defn- on? [lamp]
  (not= \O lamp))

(defn- parse-lamp-row [time & {:keys [start end lamp-on-fn lamp-value]}]
  (apply + (map #(if (lamp-on-fn %) lamp-value 0) (subs time start end))))

(defn seconds [time]
  (parse-lamp-row time
                  :start 0
                  :end 1
                  :lamp-on-fn (complement on?)
                  :lamp-value 1))

(defn minutes [time]
  (+ (parse-lamp-row time
                     :start 9
                     :end 20
                     :lamp-on-fn on?
                     :lamp-value 5)
     (parse-lamp-row time
                     :start 20
                     :end 24
                     :lamp-on-fn on?
                     :lamp-value 1)))

(defn hours [time]
  (+ (parse-lamp-row time
                     :start 2
                     :end 6
                     :lamp-on-fn on?
                     :lamp-value 5)
     (parse-lamp-row time
                     :start 6
                     :end 10
                     :lamp-on-fn on?
                     :lamp-value 1)))

(defn convert [time]
  (->> time
       ((juxt hours minutes seconds))
       (map #(format "%02d" %))
       (str/join ":")))
