## Week report 3

### Version 2 of Dijkstra’s

Changed Dijkstra into using .map files containing ASCII characters, instead of image pixels. This should make the program more efficient as there are few less image drawings needed. The algorithm also changed a bit, and now utilizes predecessor, visited and distance arrays, instead of lists. Algorithm now works as it should and is able to give out the path information also.

### GUI

The program now has a GUI, where you can choose which algorithm you want to use and set start/end points. After pressing the start button it shows distance between the two points and also draws the path on the map layout.


### Refactoring and lack of tests

The code requires a bit of refactoring still, this will be dealt with during the coming week partially and rest later on. As I had to rebuild the algorithm and wanted to focus on getting the graphical part to work, the refactoring and unit testing will be added later on.


### Next week

Next week will be mainly focusing on JPS and getting this initiated. Will partially use my time on refactoring and cleaning the Dijkstra/GUI code.

