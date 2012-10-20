(ns hello-clojurescript
  (:use [jayq.core :only [$]])
  (:require [jayq.core :as jq]))

; ALERT ON CLICK
; Rewrite of http://backbonejs.org/#Events
(def o {})

(.extend js/_ o Backbone.Events)

(.on o "alert" 
  (fn [msg] (js/alert msg)))

(jq/bind ($ "#clickable-event") :click 
      (fn [e] (.trigger o "alert" "Hello Backbone!")))

; MODEL WITH COLOR CHOOSER
; Inspired by http://backbonejs.org/#Model but without sidebar

(def MyModel 
  (.extend Backbone.Model
    (js-obj 
      "promptColor"
      (fn [] 
        (let [css-color (js/prompt "Please enter a CSS color:")]
          (this-as this
                   (.set this (js-obj "color" css-color))))))))

(def my-model (MyModel.))
 
(.on my-model "change:color"
  (fn [model color]
    (jq/css ($ "body") {:background color})))

(jq/bind ($ "#clickable-color") :click 
         (fn [e] (.promptColor my-model)))
