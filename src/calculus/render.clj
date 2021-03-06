(ns calculus.render
  (:require [hiccup.core :refer [html]]
            [io.pedestal.http.route :as route]))

(defn ok [& body]
  {:status 200
   :body (html [:html
                [:head
                 [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML" :async :async}]
                 [:script {:src "https://code.jquery.com/jquery-3.3.1.min.js"}]
                 [:style ".answer { display: none; }"]]
                [:body (interleave body (repeat [:br]))]])
   :headers {"Content-Type" "text/html"}})

(defn redirect
  ([route] {:status 303 :headers {"Location" (route/url-for route)}})
  ([route path-params] {:status 303 :headers {"Location" (apply route/url-for route :path-params path-params)}}))

(defn math [& expressions]
  (apply str `("\\(" ~@expressions "\\)")))
