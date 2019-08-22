(ns rl-adapter.mdp)

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
