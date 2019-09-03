(ns rl-adapter.exp-replay)
;;
(def conf
  ":max-capacity - value of max transitions "
  {:initial-size 10000
   :max-capacity 50000})

(def replay-memory
  "Buffer for experience replay. Contains previous transitions."
  (atom []))

;; To return, or not to return duplicates, that is the question.
(defn random-element
  "Return random element from memory."
  []
  (rand-nth @replay-memory))

(defn sample-batch
  "Return array of transition maps, size of array is batch-size."
  ([] (sample-batch 32))
  ([batch-size]
   (loop [batch [] size batch-size]
     (if (< size 1)
       batch
       (recur (conj batch (random-element)) (dec size))))))

(defn in-cap
  "Return ratio between current memory size and initial capacity size."
  []
  (double (/ (count @replay-memory) (:initial-size conf))))

(defn cap
  "Return ratio between current  and maximal memory size.
  Inputs - atom vector and his max capacity."
  [atom-queue max-capacity]
  (double (/ (count (deref atom-queue)) max-capacity)))

(defn append-queue
  [atom-queue data max-cap]
  (when (nil? (some #(= % data) (deref atom-queue)))
    (when (>= (cap atom-queue max-cap) 1) (swap! atom-queue subvec 1))
    (swap! atom-queue conj data)))

(defn- append
  "Append map with transition data:
   {:s-0 state :a action :r reward :d done :s-1 next-state}
   to replay memory. Same transition data is rejected.
   If max capacity is reached first element is removed."
  [replay-memory transition]
  (append-queue replay-memory transition (:max-capacity conf)))

(defn append-memory
  [transition]
  (append replay-memory transition))

(count @replay-memory)




