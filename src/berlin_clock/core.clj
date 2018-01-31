(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn to-berlin-minutes [time]
  (let [minutes (-> (second (str/split time #":"))
                    (Integer/parseInt)
                    (mod 5))]
    (apply str (concat (repeat minutes \Y) (repeat (- 4 minutes) \O)))))