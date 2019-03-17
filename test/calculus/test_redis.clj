(ns calculus.test-redis
  (:use clojure.test)
  (:require [calculus.redis :as r]))

(deftest validate
  (testing (let [in  (r/imbue {} :test)
                 out (r/imbue {} :test)]
             (assoc in :a 2)
             (is (= (:a out) 2)))))
