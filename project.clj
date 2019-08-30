(defproject rl-adapter "0.1.0"
  :description "Adapter for DL4J - Clojure version"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.logging "0.5.0"]
                 [org.nd4j/nd4j-native-platform "1.0.0-beta4"]
                 [org.nd4j/nd4j-common "1.0.0-beta4"]
                 [org.nd4j/nd4j-buffer "1.0.0-beta4"]
                 [org.nd4j/nd4j-backend-impls "1.0.0-beta4" :extension "pom"]
                 [org.nd4j/nd4j-context "1.0.0-beta4"]
                 [org.deeplearning4j/rl4j-core "1.0.0-beta4"]
                 [org.bytedeco/ale-platform "0.6.0-1.5"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "0.2.6"]]

  :repl-options {:init-ns rl-adapter.core})
