(ns karate-chop-kata.core)

(defn chop-baseline
  "use built in probably already binary search"
  [int
   vector]
  (.indexOf vector int))

(defn chop-v1
  "Given an int, and a sorted vector of ints, returns the index or -1"
  ([int vector]
   (chop-v1 int vector 0))
  ([int vector index]
   (let [len (count vector)
         middle (quot len 2)]
     (cond
       ;; nothing here
       (empty? vector) -1

       ;; found the value
       (= int (nth vector middle))
       (+ index middle)

       ;; is the number less then the value of the middle index
       (and (< int (nth vector middle))
            (>= middle 0))
       (recur int (subvec vector 0 middle) index)

       ;; is the number greater than the value of the middle index
       (and (> int (nth vector middle))
            (<= (inc middle) len))
       (recur int (subvec vector (inc middle) len) (inc middle))))))

(defn chop-v2
  "Given an int, and a sorted vector of ints, returns the index or -1
   if nothing is found. Uses keep-indexed."
  [int vector]
  (or (first (keep-indexed #(when (= int %2) %1) vector)) -1))

(defn chop-v3
  "Given an int, and a sorted vector of ints, returns the index or -1
   if nothing is found."
  [int vector]
  (loop [start 0
         end (count vector)]
    (let [middle (quot (+ start end) 2)
          middle-value (get vector middle -1)]
      (cond (> start end) -1
            (< int middle-value) (recur start (dec middle))
            (> int middle-value) (recur (inc middle) end)
            (= int middle-value) middle))))
