(ns rl-adapter.environment
  (:require [rl-adapter.client :as client])
  (:import (ch.qos.logback.core.helpers ThrowableToStringArray)))

(def action-space [0 1 2])
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

(defn step
  [action]
  (client/env-step action))

(defn reset
  []
  (client/env-reset))

(defn sample-action
  ([] (sample-action [0 1 2]))
  ([action-space](rand-nth action-space)))

(defn grayscale
  [obs])

(defn normalize
  [obs]
  (map #(/ % 255.0) obs))

(defn resize
  [obs])

(defn preprocess-obs
  [observation]
  (-> observation
      grayscale
      resize
      normalize))

(defn preprocess-ready-obs
  [observation]
  (normalize observation))

(sample-action)

(defprotocol ToArray
  (toArray [raw-bytes]))
(defrecord GameScreen [screen]
  ToArray
  (toArray [frame] (double-array (map #(/ (bit-and % 0xFF ) 255.0) frame))))


(defrecord MDP [observationSpace discreteSpace])

