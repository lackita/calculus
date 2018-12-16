(ns calculus.concept.even-function
  (:require [calculus.render :refer [ok redirect math]]))

(defn view-even-function [request]
  (ok "Is this an even function?"
      (math "f(x) = x^{" (-> request :path-params :exponent) "}")))

(defn generate-even-function [_]
  (redirect :even-function {:exponent (* 2 (- (rand-int 10) 5))}))
