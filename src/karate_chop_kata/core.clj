(ns karate-chop-kata.core)

(defn chop-baseline
  "use built in probably already binary search"
  [int
   array-of-int]
  (.indexOf array-of-int int))

(defn chop-v1
  [int
   array-of-int]
  -1)
