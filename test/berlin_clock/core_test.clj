(ns berlin-clock.core-test
  (:require [clojure.test :refer :all]
            [berlin-clock.core :as berlin-clock]
            [berlin-clock.time :as time]))

(deftest converting-digital-to-berlin-time
  (testing "The single minutes row should"
    (testing "accurately tell the time to the minute"
      (is (= "OOOO" (berlin-clock/single-minutes-row "00:00:00")))
      (is (= "YYYY" (berlin-clock/single-minutes-row "23:59:59")))
      (is (= "YYOO" (berlin-clock/single-minutes-row "12:32:00")))
      (is (= "YYYY" (berlin-clock/single-minutes-row "12:34:00")))
      (is (= "OOOO" (berlin-clock/single-minutes-row "12:35:00")))))
  (testing "The five minutes row should"
    (testing "tell higher minute amounts more easily at a glance"
      (is (= "OOOOOOOOOOO" (berlin-clock/five-minutes-row "00:00:00")))
      (is (= "YYRYYRYYRYY" (berlin-clock/five-minutes-row "23:59:59")))
      (is (= "OOOOOOOOOOO" (berlin-clock/five-minutes-row "12:04:00")))
      (is (= "YYRYOOOOOOO" (berlin-clock/five-minutes-row "12:23:00")))
      (is (= "YYRYYRYOOOO" (berlin-clock/five-minutes-row "12:35:00")))))
  (testing "The single hours row should"
    (testing "tell what hour it is"
      (is (= "OOOO" (berlin-clock/single-hours-row "00:00:00")))
      (is (= "RRRO" (berlin-clock/single-hours-row "23:59:59")))
      (is (= "RROO" (berlin-clock/single-hours-row "02:04:00")))
      (is (= "RRRO" (berlin-clock/single-hours-row "08:23:00")))
      (is (= "RRRR" (berlin-clock/single-hours-row "14:35:00")))))
  (testing "The five hours row should"
    (testing "tell higher hour amounts more easily at a glance"
      (is (= "OOOO" (berlin-clock/five-hours-row "00:00:00")))
      (is (= "RRRR" (berlin-clock/five-hours-row "23:59:59")))
      (is (= "OOOO" (berlin-clock/five-hours-row "02:04:00")))
      (is (= "ROOO" (berlin-clock/five-hours-row "08:23:00")))
      (is (= "RRRO" (berlin-clock/five-hours-row "16:35:00")))))
  (testing "The seconds lamp should"
    (testing "show the seconds passing"
      (is (= "Y" (berlin-clock/seconds-lamp "00:00:00")))
      (is (= "O" (berlin-clock/seconds-lamp "23:59:59")))))
  (testing "The entire clock should"
    (testing "tell what time it is at a glance"
      (is (= "YOOOOOOOOOOOOOOOOOOOOOOO" (berlin-clock/to-berlin-time "00:00:00")))
      (is (= "ORRRRRRROYYRYYRYYRYYYYYY" (berlin-clock/to-berlin-time "23:59:59")))
      (is (= "YRRROROOOYYRYYRYYRYOOOOO" (berlin-clock/to-berlin-time "16:50:06")))
      (is (= "ORROOROOOYYRYYRYOOOOYYOO" (berlin-clock/to-berlin-time "11:37:01"))))))

(deftest converting-berlin-to-digital-time
  (testing "Converting Berlin Time to Digital should tell what time it is more easily"
    (testing "Digital Seconds - note that can only distinguish between even and odd"
      (is (= 0 (time/seconds (berlin-clock/to-digital-time "YOOOOOOOOOOOOOOOOOOOOOOO"))))
      (is (= 1 (time/seconds (berlin-clock/to-digital-time "ORRRRRRROYYRYYRYYRYYYYYY")))))
    (testing "Digital Minutes"
      (is (= 0 (time/minutes (berlin-clock/to-digital-time "YOOOOOOOOOOOOOOOOOOOOOOO"))))
      (is (= 59 (time/minutes (berlin-clock/to-digital-time "ORRRRRRROYYRYYRYYRYYYYYY")))))
    (testing "Digital Hours"
      (is (= 0 (time/hours (berlin-clock/to-digital-time "YOOOOOOOOOOOOOOOOOOOOOOO"))))
      (is (= 23 (time/hours (berlin-clock/to-digital-time "ORRRRRRROYYRYYRYYRYYYYYY")))))
    (testing "Formatting"
      (is (= "00:00:00" (berlin-clock/to-digital-time "YOOOOOOOOOOOOOOOOOOOOOOO")))
      (is (= "23:59:01" (berlin-clock/to-digital-time "ORRRRRRROYYRYYRYYRYYYYYY")))
      (is (= "16:50:00" (berlin-clock/to-digital-time "YRRROROOOYYRYYRYYRYOOOOO")))
      (is (= "11:37:01" (berlin-clock/to-digital-time "ORROOROOOYYRYYRYOOOOYYOO"))))))