(ns berlin-clock.core-test
  (:require [clojure.test :refer :all]
            [berlin-clock.core :as berlin-clock]
            [berlin-clock.time :as time]))

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