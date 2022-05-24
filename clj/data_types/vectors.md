# Vectors

* Store zero or more values __sequentially__, indexed by number 
* Immutable and persistent
* Make efficient use of processor resources

## Creating vectors

Vectors use a square-bracket syntax. But the keyword `vec` is commonly used to create vectors
```clj
(vec (range 10))
;=> [0 1 2 3 4 5 6 7 8 9 ]
```
To insert several values into an existing vector, the keyword `into` is used:
```clj
(let [my-vector [:a :b :c]]
  (into my-vector (range 10)))
;=> [:a :b :c 0 1 2 3 4 5 6 7 8 9]
```

It is also possible to store primitive chars using `vector-of`, which can take any of the values `:int`, `:long`, `:float`, `:double`, `:byte`, `:short` and `:boolean`:

```clj
(into (vector-of :int) [Math/PI 2 1.3])
;=> [3 2 1]
(into (vector-of :char) [100 101 102])
;=> [\d \e \f]
(into (vector-of :int) [1 2 623876371267813267326786327863])
; java.lang.IllegalArgumentException: Value out of range for int:
-8359803716404783817
```

## Large vectors 

The performance differences between vectors and lists hardly matter when both are small, but as they get larger, each become each slower at operations the other can perform efficiently. Vectors are efficient to three things when compared to lists:

* Adding or removing things from the right end of the collection
* Accessing or changing items in the interior of the collection by numeric index
* Walking in reverse order

Any item in a vector can be acessed by the numeric index from 0 to `(count (vector))` in constant time. This is possible using the function `nth` or `get`, treating the vector like a map; or by invoking the vector as a function, examples below:

```clj
(def a-to-j (vec (map char (range 65 75))))

a-to-j
;=>[\A \B \C \D \E \F \G \H \I \J]
```
All of these do the same work, and each returns `\E`
```clj
(nth a-to-j 4)
;=> E

(get a-to-j 4)
;=> \E

(a-to-j 4)
;=> \E
```

|                              |             nth           |     get     | vector as a function |
|------------------------------|---------------------------|-------------|----------------------|
|if the vector is nil          |Returns `nil`              |Returns `nil`|Throws an exception   |
|if the index is out of range  |Throws exception by default|Returns `nil`|Throws an exception   |
|supports a "not found" arg    |            Yes            |      Yes    |         No           |

Since vectors are indedxed, they can be efficiently walke in either direction, left to right or right to left. The `seq` and `rseq` functions return sequences that do exactly that:
```clj
(seq a-to-j)
;=> (\A \B \C \D \E \F \G \H \I \J)

(rseq a-to-j)
;=> (\J \I \H \G \F \E \D \C \B \A)
```

## assoc-in and update-in

Any item in a vector can be changed using the `assoc` function:

```clj
(assoc a-to-j 4 "no longer E")
;=> [\A \B \C \D "no longer E" \F \G \H \I \J]
```

This function only works for indices that already exist in the vector or are one step past the end, in this case the returned vector is one item larger than the input vector.

The functions `assoc-in` and `update-in` are used for nested structures of vectors and/or maps:

```clj
(def matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

(get-in matrix [1 2])
;=> 6

(assoc-in matrix [1 2] 'x)
;=> [[1 2 3] [4 5 x] [7 8 9]]

(update-in matrix [1 2] * 100)
;=> [[1 2 3] [4 5 600] [7 8 9]]
```
