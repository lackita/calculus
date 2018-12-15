(ns main
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [taoensso.carmine :as car :refer (wcar)]
            [hiccup.core :refer (html)]))

(defonce server (atom nil))
(def redis-conn {:pool {} :spec {:uri "redis://localhost:6379/"}})
(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn ok [body]
  {:status 200
   :body (html [:html
                [:head [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML" :async :async}]]
                [:body body]])
   :headers {"Content-Type" "text/html"}})

(defn view-even-function [_]
  (wcar* (car/set "continue" "false"))
  (ok [:b "even problem"]))

(defn view-finished [_]
  (wcar* (car/set "continue" "true"))
  (ok "Finished Studying"))

(defn redirect [route]
  {:status 303 :headers {"Location" (route/url-for route)}})

(defn study-redirect [_]
  (if (= (wcar* (car/get "continue")) "true")
    (redirect :even-function)
    (redirect :finished)))

(def routes
  (route/expand-routes
   #{["/study" :get study-redirect :route-name :study-query]
     ["/study/even-function" :get view-even-function :route-name :even-function]
     ["/study/finished" :get view-finished :route-name :finished]}))

(def service-map
  {::http/routes routes
   ::http/type :jetty
   ::http/port 8890})

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc service-map
                              ::http/join? false)))))

(defn stop-dev []
  (http/stop @server))

(defn restart []
  (stop-dev)
  (start-dev))
