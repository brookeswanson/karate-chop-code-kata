(ns karate-chop-kata.core-test
  (:require [clojure.test :refer :all]
            [karate-chop-kata.core :as subject]))

(defn create-test-case
  "creates a map representing the test case"
  [case]
  {:test-case-number (nth case 3)
   :num (second case)
   :vector (nth case 2)
   :expected-result (first case)})

(def none-found -1)
(def empty-vector [])
(def sm-vector [1])
(def md-vector [1 3 5])
(def lg-vector [1 3 5 7])

(def cases  
  (map create-test-case
       [[none-found 3 empty-vector 1]
        [none-found 3 sm-vector 2]
        [0 1 sm-vector 3]
        [0 1 md-vector 4]
        [1 3 md-vector 5]
        [2 5 md-vector 6]
        [none-found 0 md-vector 7]
        [none-found 2 md-vector 8]
        [none-found 4 md-vector 9]
        [none-found 6 md-vector 10]
        [0 1 lg-vector 11]
        [1 3 lg-vector 12]
        [2 5 lg-vector 13]
        [3 7 lg-vector 14]
        [none-found 0 lg-vector 15]
        [none-found 2 lg-vector 16]
        [none-found 4 lg-vector 17]
        [none-found 6 lg-vector 18]
        [none-found 8 lg-vector 19]]))

(defn call-given-chop-method
  [chop-fn
   test-case] 
  (let [actual (chop-fn (:num test-case) (:vector test-case))]
    (if (not (= actual (:expected-result test-case)))
      (assoc test-case
             :actual-result actual))))

(defn test-cases
  [chop-fn]
  (keep (partial call-given-chop-method chop-fn) cases))

(deftest chop-test 
  (testing ".indexOf method works as expected."
    (let [run-cases (test-cases subject/chop-baseline)]
      (is (= [] run-cases))
      (is (= 0 (count run-cases)))))

  (testing "chop-recursion method works as expected"
    (let [run-cases (test-cases subject/chop-v1)]
      (is (= [] run-cases))
      (is (= 0 (count run-cases)))))

  (testing "chop-keep-indexed method works as expected"
    (let [run-cases (test-cases subject/chop-v2)]
      (is (= [] run-cases))
      (is (= 0 (count run-cases))))))
