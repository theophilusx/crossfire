(ns theophilusx.crossfire.migration
  (:require [integrant.core :as ig]
            [migratus.core :as migratus]
            [next.jdbc :as jdbc]
            [taoensso.timbre :refer [debug info]])
  (:import [java.sql Connection SQLException]))

(defmethod ig/init-key :theophilusx.crossfire.migration/devel [_ config]
  (let [c (assoc-in config [:db :connection] (jdbc/get-connection (:data-source config)))]
    (debug (str "Config: " c))
    (migratus/migrate c)
    config))

(defmethod ig/halt-key! :theophilusx.crossfire.migration/devel [_ config]
  (let [c (assoc-in config [:db :connection] (jdbc/get-connection (:data-source config)))]
    (migratus/rollback c)))
