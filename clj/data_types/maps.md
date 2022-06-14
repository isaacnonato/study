# Maps

## Hash maps

Hash maps provide an unsorted key/value associative structure. 
They can be created using the `hash-map` function.

```clj
(hash-map :a 1, :b 2, :c 3, :d 4, :e 5)
;=> {:a 1, :c 3, :b 2, :d 4, :e 5}
```
Keys can be of any type and each key can be of a differing type:

```clj
(let [m {:a 1, 1 :b, [1 2 3] "4 5 6"}]
  [(get m :a) (get m [1 2 3])])
;=> [1 "4 5 6"]
```

Providing a map to the `seq` function returns a sequence of map entries:

```clj
(seq {:a 1, :b 2})
;=> ([:a 1] [:b 2])
```

`apply` can be used to create a hash map given the key/value pairs are used
in a sequence consecutively:
```clj
(apply hash-map [:a 1 :b 2])
;=> {:a 1, :b 2}
```

`zipmap` can be used to create a hash map from two sequences, the first contains
the keys and the second contains the values:
```clj
(zipmap [:a :b] [1 2])
;=> {:a 1, :b 2}
```

Maps are, by default, unsorted, so `sorted-map` and `sorted-map-by` can sort them:
```clj
(sorted-map "bac" 2 "abc" 9)
;=> {"abc" 9, "bac" 3}
```

`sorted-map-by` uses a comparator to sort maps:

```clj
(sorted-map-by > 1 "a", 2 "b", 3 "c")
;=> {3 "c", 2 "b", 1 "a"}
```
## Array Maps 

When insertion order is important, array maps should be used, it ensures that the 
content is sorted:

```clj
(seq (hash-map :a 1, :b 2, :c 3))
;=> ([:c 3] [:b 2] [:a 1])

(seq (array-map :a 1, :b 2, :c 3))
;=> ([:a 1] [:b 2] [:c 3])
```
