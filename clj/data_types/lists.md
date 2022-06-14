# PersistentLists


Clojure's PersistentLists are the simplest of Clojure's persistent collection types.
A `PersistentList` is a type of linked list where each node knows its distance from the end.
Lists are mostly used to represend code forms. They're used in code to call functions, macros etc.

## `conj` and `cons`

Both macros have seemingly the same function: add something in front of a list.
But the order of the arguments is different:

```clj
(cons 1 '(2 3))
;=> (1 2 3)

(conj '(2 3) 1)
;=> (1 2 3)
```

`conj` add elements the most efficient way (on the left side). `conj` is also homogeneous, since all the objects on the `next` chain are guaranteed to be lists,
while sequences built with `cons` only promise it will be some type of seq.

## Lists as stacks

Clojure implements the `IPersistentStack` interface, implementing the functions `peek` and `pop` to do roughly the same thing as `first` and `next`.
(See docs for more info)

## When not to use lists

* When you have to look up an item by the indedx number (use vectors instead);

* When you need a set. 
