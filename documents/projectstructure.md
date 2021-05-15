# Project Structure

![Project Structure](documents/projectstructure.png)

Project structure is quite simple. Most classes have their own package, only ImageHandler package includes multiple classes.

Due to the lack of time, the project structure is lacking proper use of abstract classes and interfaces.

The algorithms are directly used by the GUI. The algorithms have their own class variables, and do not share them through a abstract class.

## Time and Space complexities

### MinHeap

Minimum heap solution has time complexity of O(log n).

### Dijkstra

Dijkstra is using a direct adjecent check and priorityqueue, this gives Dijkstra the time complexity close to O(ElogV) and space complexity of O(V).

### JPS

The time complexity of JPS in worst case is similar to other algorithms that use similar heurestics as A*. Time complexity being O(b^d) and space complexity being the same.
Even if the project did not include A* it can be said that JPS is a more optimized version of A*, making it more efficiant in both time and space complexity.

## Possible improvements

JPS algorithm has many different ways how to optimize the search. As the project grid is rather small it might not give much improvements regarding runtime, but it could be useful if the project is expanded later on.
