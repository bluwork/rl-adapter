(ns rl-adapter.client-test
  (:require [clojure.test :refer :all]
            [rl-adapter.client :refer :all]))
(deftest hello-test
  (testing "Hello Server")
 (is (:body (hello)) "Hello World"))