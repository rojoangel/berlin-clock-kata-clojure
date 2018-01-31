(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn to-berlin-single-minutes-row [time]
  (let [minutes (-> (second (str/split time #":"))
                    (Integer/parseInt)
                    (mod 5))]
    (apply str (concat (take minutes (cycle [\Y])) (repeat (- 4 minutes) \O)))))

(defn to-berlin-five-minutes-row [time]
  (let [minutes (-> (second (str/split time #":"))
                    (Integer/parseInt)
                    (quot 5))]
    (apply str (concat (take minutes (cycle [\Y \Y \R])) (repeat (- 11 minutes) \O)))))

(defn to-berlin-single-hours-row [time]
  (let [hours (-> (first (str/split time #":"))
                  (Integer/parseInt)
                  (quot 5))]
    (apply str (concat (take hours (cycle [\R])) (repeat (- 4 hours) \O)))))