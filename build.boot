(set-env!
 :resource-paths #{"src" "test"}
 :dependencies '[[io.pedestal/pedestal.service "0.5.1"]
                 [io.pedestal/pedestal.route "0.5.1"]
                 [io.pedestal/pedestal.jetty "0.5.1"]
                 [org.clojure/data.json "0.2.6"]
                 [org.slf4j/slf4j-simple "1.7.21"]
                 [com.taoensso/carmine "2.19.1"]
                 [hiccup "1.0.5"]
                 [adzerk/boot-test "1.2.0"]])

(require '[adzerk.boot-test :refer :all])
