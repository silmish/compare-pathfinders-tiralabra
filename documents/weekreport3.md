## Week report 3

### Version 2 of Dijkstra’s

Dijkstra's algorithm has been changed into using .map files from movingai, instead of reading pixels from images. This should make the program also faster as the the only need to generate image/pixels are to draw path. Algorithm is now working also as it should for wanted solution. Some datastructures are still needed to be refactored.

### Generated a filehandler

The filehandler reads .map files and generates a character array of it. This data can be used to read possible paths and generate vertexes. 

### Refactoring and lack of tests

As had to refactor the data structures and the algorithm, won't be generating any unit tests yet. Tests and javadoc will be included only next week due to this set back. 

### Next week

As had few setbacks in the last week due to refactoring Dijkstra's and creating new data reading logic, the coming week will be used to finalize Dijkstra. This means generating unit tests, javadoc and also adding a GUI for the program. This will be done now so that can focus purely on JPS after coming week and have everything else already done.
