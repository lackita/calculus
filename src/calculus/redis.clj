(ns calculus.redis
  (:require [taoensso.carmine :as car :refer [wcar]]))

(defonce redis-conn {:pool {} :spec {:uri "redis://localhost:6379/"}})
(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn rget [key]
  (wcar* (car/get key)))

(defn rset [key value]
  (wcar* (car/set key value)))
