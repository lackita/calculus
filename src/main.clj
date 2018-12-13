(ns main
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defonce server (atom nil))

(def service-map
  {::http/routes (route/expand-routes
                  #{["/study" :get (fn [_] {:status 200 :body "Finished Studying"})
                     :route-name :study-query]})
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
