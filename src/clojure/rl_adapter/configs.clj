(ns rl-adapter.configs
  (:import (org.deeplearning4j.rl4j.learning  IHistoryProcessor$Configuration)
           (org.deeplearning4j.rl4j.learning.sync.qlearning QLearning$QLConfiguration)
           (org.deeplearning4j.rl4j.network.dqn DQNFactoryStdConv$Configuration)))

(def hpc
  "Training Memory Configuration"
  {:history-length 4
   :rescaled-width 84
   :rescaled-height 84
   :cropping-width 84
   :cropping-height 84
   :offset-x 0
   :offset-y 0
   :skip-frame 4})

(def qnc
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

(def cnc
  "DQN Convolutional Configuration"
  {:learning-rate 0.00025
   :l2 0.000
   :updater nil
   :listeners nil})

(defn j-convolutional
  []
  (DQNFactoryStdConv$Configuration.
    (:learning-rate cnc)
    (:l2 cnc)
    (:updater cnc)
    (:listeners cnc)))



(defn j-q-learning
  []
  (QLearning$QLConfiguration.
    (:seed qnc)
    (:max-epoch-step qnc)
    (:max-step qnc)
    (:exp-rep-max-size qnc)
    (:batch-size qnc)
    (:target-dqn-update-freq qnc)
    (:update-start qnc)
    (:reward-factor qnc)
    (:gamma qnc)
    (:error-clamp qnc)
    (:min-epsilon qnc)
    (:epsilon-nb-step qnc)
    (:double-dqn qnc)))



(defn j-history-processing
  []
  (IHistoryProcessor$Configuration.
    (:history-length hpc)
    (:rescaled-width hpc)
    (:rescaled-height hpc)
    (:cropping-width hpc)
    (:cropping-height hpc)
    (:offset-x hpc)
    (:offset-y hpc)
    (:skip-frame hpc)))
(j-history-processing)