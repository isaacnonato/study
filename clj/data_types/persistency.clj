;; non-persistant data structure 
;; into-array makes a java/js style array from a vector
(def ds (into-array [:john :isaac :matthew]))

(aset ds 1 :luke) ;; aset sets de value into an array slot

(seq ds) ;; any historical version was obliterated.

;; using persistent data structures
(def arr [:john :isaac :matthew])

(def replaced_arr (replace {:john :luke} arr))
(println replaced_arr) ;; [:luke :isaac :matthew]
;; original unchanged
