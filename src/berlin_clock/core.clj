(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn to-berlin-single-minutes-row [time]
  (let [minutes (-> (second (str/split time #":"))
                    (Integer/parseInt)
                    (mod 5))]
    (apply str (concat (repeat minutes \Y) (repeat (- 4 minutes) \O)))))