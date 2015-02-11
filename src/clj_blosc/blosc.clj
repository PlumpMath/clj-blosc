(ns clj-blosc.blosc
  (:import [cljblosc Blosc]))


(defn blosc []
  (let [b (Blosc.)]
    (alter-var-root #'blosc (fn [x] (fn [] b)))
    b))

(defn compress
  "Compress with Blosc"
  ([to-compress typesize clevel doshuffle]
   (let [b (blosc)
         sz (* typesize (count to-compress))
         threads 4
         ob (byte-array (+ sz 16 (* 4 threads) ))]
     (println "input size: " sz)
     (println "output size: " (count ob))
     (println "threads: " threads)
     (println "to-compress:" to-compress)
     (println "to-compress (type): " (type to-compress))
     (let [out-sz (.blosc_compress b clevel doshuffle typesize sz to-compress ob (count ob))]
       [ob out-sz])))
  )

(defn decompress  [to-decompress]
  "Decompress Blosc data"
  (println "Decompress")
  (let [b (blosc)
        threads 0
        ob (byte-array 1000000)
        out-sz (.blosc_decompress b to-decompress ob (count ob) threads)]
    [ob out-sz])
  )
