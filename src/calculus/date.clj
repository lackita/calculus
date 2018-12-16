(ns calculus.date)

(defn today []
  (java.time.LocalDate/now))

(defn drange [from to]
  (take-while #(<= (compare % to) 0) (iterate #(.plusDays % 1) from)))

(defn past? [d]
  (< (compare d (today)) 0))
