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
      (is (= "OOOO" (clock/to-berlin-single-minutes-row "12:35:00")))))
  (testing "The five minutes row should"
    (testing "tell higher minute amounts more easily at a glance"
      (is (= "OOOOOOOOOOO" (clock/to-berlin-five-minutes-row "00:00:00")))
      (is (= "YYRYYRYYRYY" (clock/to-berlin-five-minutes-row "23:59:59")))
      (is (= "OOOOOOOOOOO" (clock/to-berlin-five-minutes-row "12:04:00")))
      (is (= "YYRYOOOOOOO" (clock/to-berlin-five-minutes-row "12:23:00")))
      (is (= "YYRYYRYOOOO" (clock/to-berlin-five-minutes-row "12:35:00")))))
  (testing "The single hours row should"
    (testing "tell what hour it is"
      (is (= "OOOO" (clock/to-berlin-single-hours-row "00:00:00")))
      (is (= "RRRO" (clock/to-berlin-single-hours-row "23:59:59")))
      (is (= "RROO" (clock/to-berlin-single-hours-row "02:04:00")))
      (is (= "RRRO" (clock/to-berlin-single-hours-row "08:23:00")))
      (is (= "RRRR" (clock/to-berlin-single-hours-row "14:35:00")))))
  (testing "The five hours row should"
    (testing "tell higher hour amounts more easily at a glance"
      (is (= "OOOO" (clock/to-berlin-five-hours-row "00:00:00")))
      (is (= "RRRR" (clock/to-berlin-five-hours-row "23:59:59")))
      (is (= "OOOO" (clock/to-berlin-five-hours-row "02:04:00")))
      (is (= "ROOO" (clock/to-berlin-five-hours-row "08:23:00")))
      (is (= "RRRO" (clock/to-berlin-five-hours-row "16:35:00")))))
  (testing "The seconds lamp should"
    (testing "show the seconds passing"
      (is (= "Y" (clock/to-berlin-seconds-lamp "00:00:00")))
      (is (= "O" (clock/to-berlin-seconds-lamp "23:59:59"))))))