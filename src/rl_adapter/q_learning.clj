(ns rl-adapter.q-learning
  (:require [clojure.tools.logging :as log]
            [rl-adapter [mdp :as mdp]
                        [configs :as conf]]))

(def step-counter (ref 0))

(defn upd-target-net
  "Update target network"
  []
  (log/info "Update target network"))

(defn train-epoch
  []
  (let [obs (mdp/get-last-obs)
        reward (mdp/get-reward)

        step 0;; need step from mdp
        start-q 0 ;; Double.NaN
        mean-q 0
        num-q 0
        scores []]
    (while ( and (< step (:max-epoch-step conf/qlc))
                 (not (mdp/done?)))
      (if
        (== (mod @step-counter (:target-dqn-update-freq conf/qlc)) 0)
        (upd-target-net)))))
      ;; To be continued ...






(defn trainStep
  [obs])

