(defproject berlin-clock "0.1.0-SNAPSHOT"
  :description "Berlin Clock Kata"
  :url "http://agilekatas.co.uk/katas/BerlinClock-Kata"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot berlin-clock.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-auto "0.1.2"]])
