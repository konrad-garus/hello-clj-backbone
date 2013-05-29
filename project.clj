(defproject hello-clj-backbone "0.2.0-SNAPSHOT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.1.8"]
                 [hiccup "1.0.3"]
                 [jayq "2.3.0"]]
  :plugins [[lein-cljsbuild "0.3.2"]]

  :cljsbuild
  {:builds
   [{:source-paths ["src-cljs"]
     :compiler {:pretty-print true
                :output-to "resources/public/js/cljs.js"
                :optimizations :simple}}]}

  :main hello-clojurescript.core)
