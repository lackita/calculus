(ns server
  (:require [calculus.concept.even-function :refer [generate-even-function view-even-function]]
            [calculus.study :refer [generate-study view-finished]]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defonce server (atom nil))

(def routes
  (route/expand-routes
   #{["/study" :get generate-study :route-name :study-query]
     ["/study/even-function" :get generate-even-function :route-name :generate-even]
     ["/study/even-function/:exponent" :get view-even-function :route-name :even-function]
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
