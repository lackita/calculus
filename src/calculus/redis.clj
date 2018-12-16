(ns calculus.redis
  (:refer-clojure :exclude [get set])
  (:require [taoensso.carmine :as car :refer [wcar]]))

(defonce redis-conn {:pool {} :spec {:uri "redis://localhost:6379/"}})
(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn get [key]
  (wcar* (car/get key)))

(defn sadd [key values]
  (wcar* (apply car/sadd key values)))

(defn scard [key]
  (wcar* (car/scard key)))

(defn srandmember [key]
  (wcar* (car/srandmember key)))

(defn set [key value]
  (if (set? value)
    (wcar* (sadd key value))
    (wcar* (car/set key value))))
