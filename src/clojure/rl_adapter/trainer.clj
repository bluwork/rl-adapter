(ns rl-adapter.trainer
  (:require [rl-adapter.configs :as confs]
            [rl-adapter.environment :as mdp])
  (:import (org.deeplearning4j.rl4j.util DataManager)
           (net.ltslab.ai LocalMDP LocalDQN)
           (org.deeplearning4j.rl4j.network.dqn DQN)))

(defn start
    []
    (let [mdp (LocalMDP.)
          hp-settings (confs/j-history-processing)
          ql-settings (confs/j-q-learning)
          qnet-settings (confs/j-convolutional)
          data-manager (DataManager.)
          agent (LocalDQN. mdp qnet-settings hp-settings ql-settings data-manager)]
     (do
       (.train agent)
       (println "Training finished")
       (.save (.getPolicy agent) confs/model-loc)
       (.close mdp))))

(defn load-and-play
  [model-loc]
  (let [mdp (LocalMDP.)
        dqn (DQN/load model-loc)
        hp-settings (confs/j-history-processing)
        ql-settings (confs/j-q-learning)
        data-manager (DataManager.)
        trained (LocalDQN. mdp dqn hp-settings ql-settings data-manager)]
    (.play (.getPolicy trained) mdp)))

(start)
;(load-and-play confs/model-loc)

