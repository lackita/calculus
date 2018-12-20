(ns server
  (:require [calculus.concept.even-function :as even]
            [calculus.study :refer [generate-study view-finished]]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :refer [body-params]]))

(defonce server (atom nil))

(def routes
  (route/expand-routes
   #{["/study" :get generate-study :route-name :study]
     ["/study/even-function" :get even/generate :route-name :generate-even]
     ["/study/even-function/:exponent" :get even/view :route-name :view-even]
     ["/study/even-function/:exponent" :post [(body-params) even/rate] :route-name :rate-even]
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
