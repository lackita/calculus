(ns calculus.redis
  (:refer-clojure :exclude [get set])
  (:require [taoensso.carmine :as car :refer [wcar]]))

(defonce redis-conn {:pool {} :spec {:uri "redis://localhost:6379/"}})
(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(defn hget [key field]
  (last (wcar* (case (car/hget "type" [key field])
                 :number (read-string (car/hget key field))))))

(defn get
  ([key] (let [[v n?] (wcar* (car/get key) (car/sismember "numbers" key))]
           (if (and (= n? 1) v) (read-string v) v)))
  ([key field] (case (wcar* (car/hget "type" [key field]))
                 :map (hget key field))))

(defn sadd [key values]
  (wcar* (apply car/sadd key (if (coll? values) values [values]))))

(defn scard [key]
  (wcar* (car/scard key)))

(defn srandmember [key]
  (wcar* (car/srandmember key)))

(defn set-all [value-map]
  (wcar* (map car/set value-map)))

(defn hset [key field value]
  (car/hset "type" key :map)
  (if (number? value) (car/hset key "numbers" field))
  (car/hset key field value))

(defn set [key value]
  (wcar* (cond (set? value) (sadd key value)
               (map? value) (map (partial hset key) value)
               (if (number? value) (car/sadd "numbers" key))
               (car/set key value))))

(defn srem [key member]
  (wcar* (car/srem key member)))

(deftype RedisMap [key])

(defn imbue [x key]
  (RedisMap. key))
