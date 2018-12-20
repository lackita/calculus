(ns calculus.concept.even-function
  (:require [calculus.render :refer [ok redirect math]]
            [calculus.redis :as r]
            [calculus.date :as date]
            [io.pedestal.http.route :as route]))

(defn view [request]
  (ok "Is this an even function?"
      (math "f(x) = x^{" (-> request :path-params :exponent) "}")
      [:button {:onclick "$('.answer').slideDown();"} "Show Answer"]
      [:div.answer
       "Yes"
       [:br]
       [:form {:method "post" :action (route/url-for :rate-even)}
        [:input {:type "radio" :name "rating" :value 5} "5 - perfect response"]
        [:br]
        [:input {:type "radio" :name "rating" :value 4} "4 - correct response after hesitation"]
        [:br]
        [:input {:type "radio" :name "rating" :value 3} "3 - correct response recalled with serious difficulty"]
        [:br]
        [:input {:type "radio" :name "rating" :value 2} "2 - incorrect response; where the correct one seemed easy to recall"]
        [:br]
        [:input {:type "radio" :name "rating" :value 1} "1 - incorrect response; the correct one remembered"]
        [:br]
        [:input {:type "radio" :name "rating" :value 0} "0 - complete blackout"]
        [:br]
        [:input {:type "submit"}]]]))

(defn generate [_]
  (redirect :view-even {:exponent (* 2 (- (rand-int 10) 5))}))

(defn rate [request]
  (let [rating (read-string ((request :params) "rating"))
        e-factor (or (r/get "even-function-e-factor") 2.5)
        repetition (+ (or (r/get "even-function-repetition") 0) 1)
        interval (or (r/get "even-function-interval") 0)
        new-interval (case repetition
                       1 1
                       2 6
                       (* interval e-factor))]
    (r/set "even-function-repetition" (if (< rating 3) 0 repetition))
    (r/set "even-function-interval" new-interval)
    (r/set "even-function-e-factor"
           (max 1.3 (+ e-factor (- 0.1 (* (- 5 rating) (+ 0.08 (* (- 5 rating) 0.02)))))))
    (when (> rating 3)
      (r/srem "concepts-to-review" :generate-even)
      (r/sadd (str (.plusDays (date/today) new-interval)) :generate-even))
    (redirect :study)))
