(ns lambdaisland.cucumber.gherkin-test
  (:require [clojure.test :refer :all]
            [lambdaisland.cucumber.gherkin :refer :all]
            [clojure.java.io :as io]
            [lambdaisland.cucumber.jvm :as jvm]))

(deftest gherkin->edn-test
  (is (= (read-string (slurp (io/file "resources/lambdaisland/gherkin/test_feature.edn")))
         (gherkin->edn (jvm/parse "resources/lambdaisland/gherkin/test_feature.feature")))))

(deftest round-trip-test
  (let [x (-> "resources/lambdaisland/gherkin/test_feature.feature"
              jvm/parse
              gherkin->edn)
        y (-> x edn->gherkin gherkin->edn)]
    (is (= x y))))
