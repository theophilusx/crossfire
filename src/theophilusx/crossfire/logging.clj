(ns theophilusx.crossfire.logging
  (:require [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.core :as appenders]))

(defn init-logging []
  (timbre/merge-config!
   {:appenders {:spit (appenders/spit-appender {:fname "crossfire.log"})}}))


;; (timbre/merge-config! {:appenders {:spit {:enabled? false}}} ; To disable
;; (timbre/merge-config! {:appenders {:spit nil}}               ; To remove entirely
