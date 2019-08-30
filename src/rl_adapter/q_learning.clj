(ns rl-adapter.q-learning
  (:require [clojure.tools.logging :as log]
            [rl-adapter [environment :as mdp]
                        [configs :as conf]]))

(def step-counter (atom 0))

(defn upd-target-net
  "Update target network"
  []
  (log/info "Update target network"))

;; Completed
(defn train-epoch
  "Returns"
  []
  (let [obs (mdp/get-last-obs)
        reward (atom mdp/get-reward)
        step (atom 0);; need step from mdp
        start-q (atom 0.0) ;; Double.NaN
        mean-q (atom 0.0)
        num-q (atom 0.0)
        scores (atom [])
        stat-entry {}]
    (while ( and (< step (:max-epoch-step conf/qlc))
                 (not (mdp/done?)))
      (if
        (== (mod @step-counter (:target-dqn-update-freq conf/qlc)) 0)
        (upd-target-net))
      (let [step-r (train-step obs)]
        (if (Double/isNan (:max-q step-r))
          (do (swap! num-q inc)
              (swap! mean-q + (:max-q step-r))
              (if (Double/isNaN @start-q)
                (swap! start-q + (:max-q step-r)))))
        (if (!= (:score step-r) 0)
          (swap! scores conj (:score step-r)))
        (swap! reward + (:reward (:step-reply step-r)))
        (update-obs (:observation (:step-reply step-r))))
      (swap! step-counter inc)
      (swap! step inc))
    (swap! mean-q (fn [x] (/ x (+ @num-q 0.001))))
    (merge stat-entry {:step-counter @step-counter
                       :epoch-counter @epoch-counter
                       :reward @reward
                       :episode-length @step
                       :scores @scores
                       :epsilon epsilon
                       :start-q start-q
                       :mean-q mean-q})))
    

(defn update-obs [obs])

(defn input [obs])

(defn skip-frame?
  []
  (== (mod @step-counter (:skip-frame conf/tmc)) 0))

;; Code in progress...
(defn train-step
  "Return map with max-q, score and step reply"
  [obs]
  (let [action 0 ;; Integer
        input (input obs)
        observation 1
        reward 0.0
        max-q 0.0
        score 0.0]
    {:max-q max-q
     :score score
     :step-reply {:reward reward
                  :observation observation}}))





(defn postEpoch)
(defn preEpoch)