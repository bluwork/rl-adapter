(ns rl-adapter.environment
  (:import (org.deeplearning4j.rl4j.space ArrayObservationSpace DiscreteSpace)))

(defn steps [])
(defn get-last-obs
  "Return last observation"
  [])
(defn get-reward
  "Return reward"
  [])

(defn done?
  [] false)

(defn init-mdp
  "Reset neural net and return step, next observation and reward"
  [mdp hp])

(defn get-input
  "Return input array from mdp"
  [mdp obs])
(def obs-space
  (ArrayObservationSpace. (int-array [84 84 3])))
(def disc-space
  (DiscreteSpace. 3))


(.randomAction disc-space)
(second (.getShape obs-space))