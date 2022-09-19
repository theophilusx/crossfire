(ns theophilusx.crossfire.db
  (:require [integrant.core :as ig]
            [next.jdbc :as jdbc]))

(defmethod ig/init-key :theophilusx.crossfire.db/postgres [_ db]
  (jdbc/get-datasource db))

(defn test-connection [ds]
  (let [rslt (jdbc/execute! ds ["SELECT 'Hello' test"])]
    (if (= (:test (first rslt)) "Hello")
      true
      false)))
