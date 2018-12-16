(ns calculus.study
  (:require [calculus.render :refer [redirect ok]]
            [calculus.redis :as r]
            [calculus.date :refer [today drange past?]]))

(defn generate-study [_]
  (when-not (r/get "last-study-date")
    (r/set "concepts-to-review" #{:generate-even})
    (r/set "last-study-date" (today)))

  (let [last (r/get "last-study-date")]
    (when (past? (today))
      (map #(r/sadd "concepts-to-review" (r/get %)) (drange last (today)))
      (r/set "last-study-date" (today))))

  (if (= (r/scard "concepts-to-review") 0)
    (redirect :finished)
    (redirect (keyword (r/srandmember "concepts-to-review")))))

(defn view-finished [_]
  (r/set "continue" "true")
  (ok "Finished Studying"))
