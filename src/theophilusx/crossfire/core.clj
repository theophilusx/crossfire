(ns theophilusx.crossfire.core
  (:require [integrant.core :as ig]
            [theophilusx.crossfire.logging :refer [init-logging]]
            [theophilusx.crossfire.handlers]
            [theophilusx.crossfire.server]
            [theophilusx.crossfire.migration]
            [theophilusx.crossfire.db :as db]
            [taoensso.timbre :refer [debug info]])
  (:gen-class))

(init-logging)

(def system (atom nil))

(defn load-config []
  (debug "Reading config file")
  (ig/read-string (slurp "resources/config.edn")))

(defn start [config]
  (info "Starting application")
  (reset! system (ig/init config)))

(defn stop [system]
  (info "Stopping application")
  (ig/halt! @system)
  (reset! system nil))

(defn -main []
  (let [config (load-config)]
    (debug "Executing main method")
    (start config)))

(comment
  (load-config)
  (start (load-config))
  (db/test-connection (:theophilusx.crossfire.db/postgres @system))
  (stop system)
  ,)
