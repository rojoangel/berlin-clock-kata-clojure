(ns berlin-clock.digital-time-test
  (:require [clojure.test :refer :all]
            [berlin-clock.digital-time :as digital-time]))

(deftest converting-berlin-to-digital-time
  (testing "Converting Berlin Time to Digital should tell what time it is more easily"
    (testing "Digital Seconds - note that can only distinguish between even and odd"
      (is (= 0 (digital-time/seconds "YOOOOOOOOOOOOOOOOOOOOOOO")))
      (is (= 1 (digital-time/seconds "ORRRRRRROYYRYYRYYRYYYYYY"))))
    (testing "Digital Minutes"
      (is (= 0 (digital-time/minutes "YOOOOOOOOOOOOOOOOOOOOOOO")))
      (is (= 59 (digital-time/minutes "ORRRRRRROYYRYYRYYRYYYYYY"))))
    (testing "Digital Hours"
      (is (= 0 (digital-time/hours "YOOOOOOOOOOOOOOOOOOOOOOO")))
      (is (= 23 (digital-time/hours "ORRRRRRROYYRYYRYYRYYYYYY"))))
    (testing "Formatting"
      (is (= "00:00:00" (digital-time/convert "YOOOOOOOOOOOOOOOOOOOOOOO")))
      (is (= "23:59:01" (digital-time/convert "ORRRRRRROYYRYYRYYRYYYYYY")))
      (is (= "16:50:00" (digital-time/convert "YRRROROOOYYRYYRYYRYOOOOO")))
      (is (= "11:37:01" (digital-time/convert "ORROOROOOYYRYYRYOOOOYYOO"))))))