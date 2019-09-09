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
                 [org.deeplearning4j/rl4j-ale "0.9.1"]
                 [org.datavec/datavec-api "0.9.1"]
                 [com.google.code.gson/gson "2.8.5"]
                 [org.projectlombok/lombok "1.18.8"]
                 [metasoarous/oz "1.6.0-alpha5"]
                 [cheshire "5.9.0"]
                 [org.clojure/tools.logging "0.5.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [com.hypirion/clj-xchart "0.2.0"]]

  :repl-options {:init-ns rl-adapter.core}
  :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options" "-Xlint:unchecked"]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"])
