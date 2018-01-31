(ns berlin-clock.core-test
  (:require [clojure.test :refer :all]
            [berlin-clock.core :as clock]))

(deftest converting-digital-to-berlin-time
  (testing "The single minutes row should"
    (testing "accurately tell the time to the minute"
      (is (= "OOOO" (clock/to-berlin-single-minutes-row "00:00:00")))
      (is (= "YYYY" (clock/to-berlin-single-minutes-row "23:59:59")))
      (is (= "YYOO" (clock/to-berlin-single-minutes-row "12:32:00")))
      (is (= "YYYY" (clock/to-berlin-single-minutes-row "12:34:00")))
      (is (= "OOOO" (clock/to-berlin-single-minutes-row "12:35:00"))))))
