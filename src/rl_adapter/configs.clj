(ns rl-adapter.configs)

(def tmc
  "Training Memory Configuration"
  {:history-length 4
   :rescaled-width 84
   :rescaled-height 110
   :croppingWidth 84
   :croppingHeight 84
   :offsetX 0
   :offsetY 0
   :skip-frame 4})

(def qlc
  "Q Learning Configuration"
  {:seed 123
   :max-epoch-step 10000
   :max-step 8E4
   :exp-rep-max-size 1000000
   :batch-size 32
   :target-dqn-update-freq 10000
   :update-start 500
   :reward-factor 0.1
   :gamma 0.99
   :error-clamp 100.00
   :min-epsilon 0.1
   :epsilon-nb-step 100000
   :double-dqn true})

(def dqn-conv-conf
  "DQN Convolutional Configuration"
  {:learning-rate 0.00025
   :l2 0.000
   :updater nil
   :listeners nil})
