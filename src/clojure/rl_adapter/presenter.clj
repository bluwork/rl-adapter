(ns rl-adapter.presenter
  (:require [clojure.tools.logging :as log])
  (:import  (net.ltslab.ai RealtimeChart)))


(def epochsChart (RealtimeChart. "RL-adapter - Rewards" "Epochs" "Cumulative Reward" "Reward" 500))
(def stepChart (RealtimeChart. "RL-adapter - Step Score" "Step" "Max Action" "Action" 40000))

(defn show-step
  ""
  [current-steps max-action step-score]
  (.updateChart stepChart current-steps max-action))
  ;(log/info "Step" current-steps "Action" max-action  "Score" step-score))
  ;{:step current-steps :max-action max-action :step-score step-score})

(defn show-epoch-data
  [current-epoch epoch-reward]
  (.updateChart epochsChart current-epoch epoch-reward))


