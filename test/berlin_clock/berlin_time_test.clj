(ns berlin-clock.berlin-time-test
  (:require [clojure.test :refer :all]
            [berlin-clock.berlin-time :as berlin-time]))

(deftest converting-digital-to-berlin-time
  (testing "The single minutes row should"
    (testing "accurately tell the time to the minute"
      (is (= "OOOO" (berlin-time/single-minutes-row "00:00:00")))
      (is (= "YYYY" (berlin-time/single-minutes-row "23:59:59")))
      (is (= "YYOO" (berlin-time/single-minutes-row "12:32:00")))
      (is (= "YYYY" (berlin-time/single-minutes-row "12:34:00")))
      (is (= "OOOO" (berlin-time/single-minutes-row "12:35:00")))))
  (testing "The five minutes row should"
    (testing "tell higher minute amounts more easily at a glance"
      (is (= "OOOOOOOOOOO" (berlin-time/five-minutes-row "00:00:00")))
      (is (= "YYRYYRYYRYY" (berlin-time/five-minutes-row "23:59:59")))
      (is (= "OOOOOOOOOOO" (berlin-time/five-minutes-row "12:04:00")))
      (is (= "YYRYOOOOOOO" (berlin-time/five-minutes-row "12:23:00")))
      (is (= "YYRYYRYOOOO" (berlin-time/five-minutes-row "12:35:00")))))
  (testing "The single hours row should"
    (testing "tell what hour it is"
      (is (= "OOOO" (berlin-time/single-hours-row "00:00:00")))
      (is (= "RRRO" (berlin-time/single-hours-row "23:59:59")))
      (is (= "RROO" (berlin-time/single-hours-row "02:04:00")))
      (is (= "RRRO" (berlin-time/single-hours-row "08:23:00")))
      (is (= "RRRR" (berlin-time/single-hours-row "14:35:00")))))
  (testing "The five hours row should"
    (testing "tell higher hour amounts more easily at a glance"
      (is (= "OOOO" (berlin-time/five-hours-row "00:00:00")))
      (is (= "RRRR" (berlin-time/five-hours-row "23:59:59")))
      (is (= "OOOO" (berlin-time/five-hours-row "02:04:00")))
      (is (= "ROOO" (berlin-time/five-hours-row "08:23:00")))
      (is (= "RRRO" (berlin-time/five-hours-row "16:35:00")))))
  (testing "The seconds lamp should"
    (testing "show the seconds passing"
      (is (= "Y" (berlin-time/seconds-lamp "00:00:00")))
      (is (= "O" (berlin-time/seconds-lamp "23:59:59")))))
  (testing "The entire clock should"
    (testing "tell what time it is at a glance"
      (is (= "YOOOOOOOOOOOOOOOOOOOOOOO" (berlin-time/convert "00:00:00")))
      (is (= "ORRRRRRROYYRYYRYYRYYYYYY" (berlin-time/convert "23:59:59")))
      (is (= "YRRROROOOYYRYYRYYRYOOOOO" (berlin-time/convert "16:50:06")))
      (is (= "ORROOROOOYYRYYRYOOOOYYOO" (berlin-time/convert "11:37:01"))))))
