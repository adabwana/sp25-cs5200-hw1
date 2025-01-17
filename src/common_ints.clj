(ns common-ints)

; # Clojure: Common Integers

; This implementation addresses the intersection of sequences where element frequency matters. Standard set operations discard duplicates, but many applications require preserving the count of repeated elements. When intersecting `[1 1 2]` with `[1 1 1 2]`, we maintain two `1`s since that's their shared frequency.

; The implementation leverages `Clojure`'s *functional programming* capabilities through the `->>` threading macro. It processes element frequencies using `frequencies` for counting and `group-by` for organizing elements. The core logic uses `mapcat` with `repeat` to generate the correct number of repeated elements based on minimum frequencies between sequences.

; For handling multiple sequences, it employs `reduce` to cascade the two-sequence intersection operation. This mirrors the mathematical definition of n-way intersection while maintaining the frequency preservation property across all inputs.

(defn intersection
  "Return an array that is the intersection of the input arrays"
  ([s1] (vec s1))
  ([s1 s2]
   (let [freq2 (frequencies s2)]
     (->> s1
          (filter #(contains? freq2 %))
          (group-by identity)
          (mapcat (fn [[k v]]
                   (repeat (min (count v) (freq2 k)) k)))
          vec)))
  ([s1 s2 & sets]
   (reduce intersection (intersection s1 s2) sets)))

; Examples demonstrating frequency-preserving intersection:

(intersection [1 1 2 3] [1 2 3 4])
(intersection [1 2 3] [2 3 4] [3 4 5])
(intersection [1 1 2] [1 1 1 2])
(intersection [1 1 2 3] [1 2 3] [1 3])
