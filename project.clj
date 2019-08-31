(defproject rl-adapter "0.1.0"
  :description "Adapter for DL4J - Clojure version"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.deeplearning4j/deeplearning4j-core "0.9.1"]
                 [org.nd4j/nd4j-native-platform "0.9.1"]
                 [org.deeplearning4j/rl4j-core "0.9.1"]
                 [org.datavec/datavec-api "0.9.1"]]

  :repl-options {:init-ns rl-adapter.core})
