(ns dev
  (:require [scicloj.clay.v2.api :as clay]))

(defn build []
  (clay/make!
   {:format              [:quarto :html]
    :book                {:title "CS5200: Background & Expectations"}
    :subdirs-to-sync     ["notebooks"]
    :source-path         ["src/index.clj"
                          "notebooks/common_ints.ipynb"
                          "src/common_ints.clj"]
    :base-target-path    "docs"
    :clean-up-target-dir true}))

(comment
  (build))
