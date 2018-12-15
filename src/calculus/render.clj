(ns calculus.render
  (:require [hiccup.core :refer [html]]
            [io.pedestal.http.route :as route]))

(defn ok [& body]
  {:status 200
   :body (html [:html
                [:head [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML" :async :async}]]
                [:body (interleave body (repeat [:br]))]])
   :headers {"Content-Type" "text/html"}})

(defn redirect [route & path-params]
  {:status 303 :headers {"Location" (route/url-for route :path-params path-params)}})

(defn math [& expressions]
  (apply str `("\\(" ~@expressions "\\)")))
