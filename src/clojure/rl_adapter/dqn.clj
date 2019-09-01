(ns rl-adapter.dqn
  (:require [rl-adapter.exp-replay :as exp-replay]
            [rl-adapter.environment :as env]
            [rl-adapter.nnet :as nnet]))

(def training-rewards (atom []))
(def training-loss (atom []))
(def update-loss (atom []))
(def mean-training-rewards (atom []))
(def rewards (atom 0))
(def step-count (atom 0))
(def s-0 (atom []))
(def state-buffer (atom []))
(def next-state-buffer (atom []))
(def window 100)


(defn init
  [])

(defn action
  [mode epsilon]
  (if (= mode :explore)
    (env/sample-action)

    (do (swap! step-count inc)
        (swap! s-0 conj state-buffer)
        (nnet/action s-0 epsilon))))

(defn take-step
  ([epsilon] (take-step :train epsilon))
  ([mode epsilon]
   (let [action (action :explore 0.05)
         step (env/step action)
         new-state (env/preprocess-ready-obs (:lastFrame step))
         reward (:reward step)
         done (:done step)]
     (swap! rewards + reward)
     (exp-replay/append-queue state-buffer @s-0 4)
     (exp-replay/append-queue next-state-buffer new-state 4)
     (exp-replay/append-memory {:s-0 @state-buffer :a action :r reward :done done :s-1 @next-state-buffer})
     (reset! s-0 new-state)
     done)))

(defn update []
  (let [batch (exp-replay/sample-batch)
        loss (calculate-loss batch)]))

(defn average
  [numbers]
  (/ (reduce + numbers) (count numbers)))

(defn train
  []
  (let [epsilon 0.05
        gamma 0.99
        max-episodes nil
        net-sync-freq 5000
        update-freq 4
        ep (atom 0)
        fps-buffer (atom [])
        save-freq 100]
    (while (< (exp-replay/in-cap) 1)
      (if (take-step :explore 0.05)
        (swap! s-0 (comp env/preprocess-ready-obs (:lastFrame env/reset)))))
    (while (training)
     (do
       (reset! s-0 (env/preprocess-ready-obs (env-reset)))
       (reset! rewards [])
       (let [done (atom false)]
         (while (false? done)
           (do
             (reset! done (take-step :train epsilon))
             (when (= 0(mod @step-count update-freq))
               (update))
             (when (= (mod @step-count net-sync-freq) 0)
               :tnn-load-state-dict-from-nnet)
             (when @done
               (do (swap! ep inc)
                   (swap! training-rewards conj rewards)
                   (let [mean-rewards (average (take 100 (reverse @training-rewards)))]))))))))))





(defn calculate-loss [batch])

(defn initialize [])

(defn save-weights [])

(defn save-rewards [])

(defn save-parameters [])

(defn plot-rewards [])

