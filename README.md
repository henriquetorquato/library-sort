# LibrarySort

This project is intended as a answer to a problem created by my self for my Algorithm's class.

## Problem

A public library got many used book's as donation from the community, but the book's were delivered to the library out of order. The library places the books on shelfs, ordered by Category. And inside each shelf the books are ordered by alphabetical order.

To automate the task of ordering all the books, a program is needed.

## Solution

A multi part algorithm will be used.

### Part 1

The first part of it will use a `QuickSort` implementation, witch is intended for itens with some similarities. In this case the common simitarity is the book's `Category` (book subject).

This will place the book's in order to be added to a selected shelf.

### Part 2

Every created shelf gives origin to a diferent thread, each thread will apply a `MergeSort` implementation, witch performs better over random places objects. The book's will be ordered by name in alphabetical order.

## References

- Visual reference for the sorting algorithm's can be found [here](https://www.youtube.com/watch?v=ZZuD6iUe3Pc).
- Reference for Java `MergeSort` implementation can be found [here](https://www.baeldung.com/java-merge-sort).
- Reference for Java `QuickSort` implementation can be found [here](https://www.geeksforgeeks.org/java-program-for-quicksort/).

## TO-DO

- [x] Implement Author and Category generator
- [x] Implement book generator
- [X] Implement `QuickSort` for the books
- [x] Implement `MergeSort` for the books inside the shelf's
- [x] Implement multithreading for the multiple shelf sorting
- [x] Implement multithreading inside the `QuickSort` partitions
    - When sorting a large amount of books, it is taking a while.
- [x] Create serializer for the shelfs
- [ ] Add more word's for book name variation
