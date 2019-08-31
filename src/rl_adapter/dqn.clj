(ns rl-adapter.dqn)

(defn train [])

(defn take-step
  ([] (take-step :train))
  ([mode]
   (if (== mode :explore))))
     

(defn calculate-loss [batch])



(defn update [])

(defn initialize [])


(defn save-weights [])

(defn save-rewards [])

(defn save-parameters [])

(defn plot-rewards [])

