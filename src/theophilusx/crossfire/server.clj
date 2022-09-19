(ns theophilusx.crossfire.server
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :refer [run-jetty]]))

(defmethod ig/init-key :theophilusx.crossfire.server/jetty [_ {:keys [handler] :as opts}]
  (run-jetty handler (dissoc opts :handler)))

(defmethod ig/halt-key! :theophilusx.crossfire.server/jetty [_ server]
  (.stop server))
