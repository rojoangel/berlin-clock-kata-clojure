(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn to-berlin-single-minutes-row [time]
  (let [single-minutes (-> (second (str/split time #":"))
                           (Integer/parseInt)
                           (mod 5))]
    (apply str (concat (take single-minutes (cycle [\Y])) (repeat (- 4 single-minutes) \O)))))

(defn to-berlin-five-minutes-row [time]
  (let [five-minutes (-> (second (str/split time #":"))
                         (Integer/parseInt)
                         (quot 5))]
    (apply str (concat (take five-minutes (cycle [\Y \Y \R])) (repeat (- 11 five-minutes) \O)))))

(defn to-berlin-single-hours-row [time]
  (let [single-hours (-> (first (str/split time #":"))
                         (Integer/parseInt)
                         (mod 5))]
    (apply str (concat (take single-hours (cycle [\R])) (repeat (- 4 single-hours) \O)))))