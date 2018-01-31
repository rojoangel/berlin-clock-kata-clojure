(ns berlin-clock.core
  (:require [clojure.string :as str]))

(defn to-berlin-minutes [time]
  (let [minutes (-> (second (str/split time #":"))
                    (Integer/parseInt)
                    (mod 5))]
    (if (> minutes 0)
      (apply str (repeat minutes "Y"))
      "OOOO")))
