(ns rl-adapter.trainer
  (:require [rl-adapter.configs :as confs]
            [rl-adapter.environment :as mdp])
  (:import (org.deeplearning4j.rl4j.learning.sync.qlearning.discrete QLearningDiscreteConv)
           (org.deeplearning4j.rl4j.util DataManager)
           (net.ltslab.ai LocalMDP)))

(defn start
    []
    (let [mdp (LocalMDP.)
          hp-settings (confs/j-history-processing)
          ql-settings (confs/j-q-learning)
          qnet-settings (confs/j-convolutional)
          data-manager (DataManager.)
          agent (QLearningDiscreteConv. mdp qnet-settings hp-settings ql-settings data-manager)]
     (do
       (.train agent)
       (.save ("breakoutGDX.model"))
       (.close mdp))))


(start)
