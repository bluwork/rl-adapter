(ns rl-adapter.client
  "Connection to environment server"
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def url-parts
  "Constant values for making a URL for client"
  {:domain "http://127.0.0.1:4567"
   :step   "/step/"
   :reset  "/reset"
   :close  "/close"
   :hello "/hello"})

(defn- reset-link
  []
  (str (:domain url-parts) (:reset url-parts)))

(defn- step-action-link
  [action]
  (str (:domain url-parts) (:step url-parts) action))

(defn env-step
  "Post action to get the state of environment"
  [action]
  (-> action
      step-action-link
      client/get
      :body))
      ;json/read-json))

(defn env-reset
  "Post action to reset environment"
  []
  (-> (reset-link)
      client/get
      :body))
      ;json/read-json))

(defn hello
  []
  (client/get (str (:domain url-parts) (:hello url-parts))))

