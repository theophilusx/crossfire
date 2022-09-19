(ns theophilusx.crossfire.handlers
  (:require [integrant.core :as ig]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :as rsp]
            [hiccup.core :refer [html]]))

(defn homepage [req]
  (-> (rsp/response
       (html
        {:mode :html}
        [:h1 "The Homepage"]
        [:p "This is the root or homepage of the application"]
        [:p "This handler was called with the following arguments: "]
        (handle-dump req)
        [:footer
         [:hr]
         [:small "&copy; 2022 Tim Cross"]]))
      (rsp/content-type "text/html")))

(defn not-implemented [uri]
  (-> (rsp/response
       (html {:mode :html}
             [:h1 "Not Yet Implemented"]
             [:h4 "Page " uri]
             [:p "This is a valid page with a valid URL. "
              "However, this functionality has not yet been developed."
              "Check back soon to see how things are going"]
             [:footer
              [:hr]
              [:small "&copy; 2022 Tim Cross"]]))
      (rsp/content-type "text/html")))

(defn page-not-found [{:keys [uri]}]
  (-> (rsp/not-found
       (html {:mode :html}
             [:h1 "Page Not Found"]
             [:p "The url " uri " was not found on this server."]
             [:p "This is not a legitimate URL - or at least not one currently "
              "suported by this application. THis is probably a bug or misconfiguration"]
             [:footer
              [:hr]
              [:small "&copy; 2022 Tim Cross"]]))
      (rsp/content-type "text/html")))

(defroutes app
  (GET "/" req (homepage req))
  (GET "/orders" [uri] (not-implemented uri))
  (not-found page-not-found))

(defmethod ig/init-key :theophilusx.crossfire.handlers/site-handler [_ _]
  (-> #'app
      (wrap-defaults (assoc site-defaults :proxy true))
      (wrap-reload)))
