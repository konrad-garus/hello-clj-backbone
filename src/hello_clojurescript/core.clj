(ns hello-clojurescript.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.middleware.reload :as reload]
            [hiccup.core :as h]
            [hiccup.page :as hp]))

(defn render-body [] 
  (hp/html5 
    [:head]
    [:body
     [:button#clickable-event "Click to trigger an alert from basic Backbone event"]
     [:button#clickable-color "Click to change background color"]
     (hp/include-js 
       "http://code.jquery.com/jquery-1.8.2.min.js"
       "http://underscorejs.org/underscore.js"
       "http://backbonejs.org/backbone.js"
       "js/cljs.js")
     ]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (render-body)})

(def app 
  (-> handler
    (resources/wrap-resource "public")
    (reload/wrap-reload)))

(defn -main [& args]
  (jetty/run-jetty app {:port 3000}))
