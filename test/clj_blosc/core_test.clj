(ns clj-blosc.core-test
  (:require [clojure.test :refer :all]
            [clj-blosc.blosc :refer :all]))

(deftest test-simple-compress-decompress
  (testing "Basic compress and decompress"
    (let [
          orig (byte-array 100)
          _ (aset-byte orig 0 100)
          _ (aset-byte orig 1 101)
          _ (aset-byte orig 2 102)
          _ (aset-byte orig 3 103)
          o (compress orig 1 9 1)
          decompressed (decompress (first o))]
    (is (= (aget orig 0) (aget (first decompressed) 0)))
    (is (= (aget orig 1) (aget (first decompressed) 1)))
    (is (= (aget orig 2) (aget (first decompressed) 2)))
    (is (= (aget orig 3) (aget (first decompressed) 3))))
    ))
