(ns calculus.redis
  (:refer-clojure :exclude [get set])
  (:require [taoensso.carmine :as car :refer [wcar]]))

(defonce redis-conn {:pool {} :spec {:uri "redis://localhost:6379/"}})
(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn get [key]
  (let [[v n?] (wcar* (car/get key) (car/sismember "numbers" key))]
    (if (and (= n? 1) v) (read-string v) v)))

(defn sadd [key values]
  (wcar* (apply car/sadd key (if (coll? values) values [values]))))

(defn scard [key]
  (wcar* (car/scard key)))

(defn srandmember [key]
  (wcar* (car/srandmember key)))

(defn set [key value]
  (if (set? value)
    (wcar* (sadd key value))
    (wcar* (if (number? value) (car/sadd "numbers" key))
           (car/set key value))))

(defn srem [key member]
  (wcar* (car/srem key member)))
