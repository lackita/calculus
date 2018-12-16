(ns calculus.study
  (:require [calculus.render :refer [redirect ok]]
            [calculus.redis :refer [rget rset]]))

(defn generate-study [_]
  (ok (rget "last-study-date"))
  (if (= (rget "continue") "true")
    (redirect :generate-even)
    (redirect :finished)))

(defn view-finished [_]
  (rset "continue" "true")
  (ok "Finished Studying"))
