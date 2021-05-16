# Testing document

## Unit test

Unit tests have been made really fast in the end of the project and do not cover much of the code.

Imagehandler, Main and GUI has been excluded from the tests.

![Whole project](documents/wholeProject.png)

![Data Strcutures](documents/Datastructure.png)

![JPS](documents/JPS.png)

![Dijkstra](documents/Dijkstra.png)


## Performance tests

For performance testing various maps from [Moving AI](https://movingai.com/benchmarks). Maps that were used were from Baldurs Gate 2, scaled 512x512 map size.

All the tests were repeated 5 times and runtime average have been displayed under "Time" with each algorithm.

No separate performance test logic has been built, meaning all the test were manualy done and calculated.


![Map 1](https://github.com/silmish/compare-pathfinders-tiralabra/blob/master/pathfinder/src/main/java/Images/Map1.png)

Start X: 46, Y: 366
End X: 285, Y: 456


Map 1 | Distance | Time |
|--|--|--|
JPS | 860,8 | 11 ms |
Dijkstra | 860,8 | 97 ms |


![Map 3](https://github.com/silmish/compare-pathfinders-tiralabra/blob/master/pathfinder/src/main/java/Images/Map3.png)

Start X: 101, Y: 196
End X: 167, Y: 358

Map 3 | Distance | Time |
|--|--|--|
JPS | 1178 | 9 ms |
Dijkstra | 1178 | 76 ms |

![Map 4](https://github.com/silmish/compare-pathfinders-tiralabra/blob/master/pathfinder/src/main/java/Images/Map4.png)

Start X: 349, Y: 148
End X: 503, Y: 339

Map 4 | Distance | Time |
|--|--|--|
JPS | 1362,3 | 13 ms |
Dijkstra | 1362,3 | 60 ms |

## Test observations and conclutions

No suprises that JPS was much faster than Dijkstra in all the tests. The reason for the comparison was to see how much it is possible to optimize pathfinding.

Based on the results it can be said JPS is 5 to 10 times faster in the curren project compared to Dijkstra. There seems to be not much difference on the map layout to the results, except it seems that narrow complex map caused the difference between the two algorithms to become less. 
This cant be really confirmed with this amount of testing and would require more testing to be sure that there is a correlation to the maplayout.

Even with JPS being done with the orignal solution and without any known optimizations, it is by far faster than Dijkstra's algorithm and gives a good indication that pathfinding with grid maps can be optimized and made much faster than many older pathfinding algorithms.



