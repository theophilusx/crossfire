(ns user
  (:require [integrant.core :as ig]
            [integrant-repl :as ig-repl]
            [theophilusx.crossfire.core :refer [load-config]]))

(ig-repl/set-prep! (fn []
                     (let [config (load-config)]
                       (ig/load-namespaces config)
                       (ig/prep config))))
