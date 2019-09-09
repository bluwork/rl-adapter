(ns rl-adapter.configs
  (:import (org.deeplearning4j.rl4j.learning IHistoryProcessor$Configuration)
           (org.deeplearning4j.rl4j.learning.sync.qlearning QLearning$QLConfiguration)
           (org.deeplearning4j.rl4j.network.dqn DQNFactoryStdConv$Configuration)
           (org.deeplearning4j.rl4j.learning.async.a3c.discrete A3CDiscrete$A3CConfiguration A3CDiscrete)
           (org.deeplearning4j.rl4j.network.ac ActorCriticFactoryCompGraphStdConv$Configuration ActorCriticFactoryCompGraphStdConv)))
           ;(org.nd4j.linalg.learning.config Adam)))

(def hpc
  "Training Memory Configuration"
  {:history-length  4
   :rescaled-width  84
   :rescaled-height 84
   :cropping-width  84
   :cropping-height 84
   :offset-x        0
   :offset-y        0
   :skip-frame      4})

(def qnc
  "Q Learning Configuration"
  {:seed                   123
   :max-epoch-step         10000
   :max-step               8000000
   :exp-rep-max-size       1000000
   :batch-size             32
   :target-dqn-update-freq 50000
   :update-start           500
   :reward-factor          0.1
   :gamma                  0.99
   :error-clamp            100.00
   :min-epsilon            0.1
   :epsilon-nb-step        100000
   :double-dqn             true})

;(def ale-ac3 {
;
;              :seed                 123
;              :max-epoch-step       10000
;              :max-step             8000000
;              :num-of-threads       8
;              :t-max                32
;              :num-step-noop-warmup 500
;              :reward-scaling       0.1
;              :gamma                0.99
;              :td-error-clipping    10.0})
;
;
;(def ac3-net {
;              :l2 0.000
;              :learning-rate     0.00025
;              :listeners                null
;              :use-LSTM                 false})

(def cnc
  "DQN Convolutional Configuration"
  {:learning-rate 0.00025
   :l2            0.000
   :updater       nil
   :listeners     nil})

(def model-loc "./breakoutGdx.model")

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

;(defn j-ac3-learning
;  []
;  (A3CDiscrete$A3CConfiguration.
;    (:seed ale-ac3)
;    (:max-epoch-step ale-ac3)
;    (:max-step ale-ac3)
;    (:num-of-threads ale-ac3)
;    (:t-max ale-ac3)
;    (:num-step-noop-warmup ale-ac3)
;    (:reward-factor ale-ac3)
;    (:gamma ale-ac3)
;    (:td-error-clipping ale-ac3)))
;(defn j-ac3-net
;  []
;  (ActorCriticFactoryCompGraphStdConv$Configuration.
;    (:l2 ac3-net)
;    (Adam. (:learning-rate ac3-net))
;    (:listeners ac3-net)
;    (:use-LSTM ac3-net)))



