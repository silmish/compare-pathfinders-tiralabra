# Project specification

Goal for the project is to create a comparison between two different pathfinding algorithms in a 2D array map. The algorithms that will be used are Dijkstra’s and JPS (Jump Point Search), to find the fastest route between two locations in the map.

Reason behind this is my own interest in game development. Currently still in the very beginning of the learning curve and would like to understand better various pathing and moving algorithms that are used in 2D games. 

The project will use 2D grid map examples from Movingai, the example maps will be from Baldursgate 2.

## Data structures

Dijkstra’s algorithm time complexity goal is O(ElogV). This can be achieved by using adjacency list and priority queue. Due to the nature of the course it is not quite clear if it will be possible to reach this as data structures are required to generate by ourselves. Worst case Dijkstra’s will work in O(V^2) time complexity if the adjacency matrix is required to to be used. This will be specified later when the program is finalized.

Jump Point Search can be very fast on time complexity if optimized well, but heavy on space complexity, and by this it is hard to say what the goal is. Currently I do not have a clear understanding of the time nor space complexities of this algorithm. This information will be specified later when the program is finalized.

## Program output and input

The program will use 2D grid maps from movingai as input. Ás the maps are black and white colored pictures the program will find paths within the white pixels to move to the wanted endpoint. The output will give the path that the algorithm chose drawn on the map and give the amount of steps or used weight to move for comparison data. This will be more specified when the program is finalized.

## Other information

Degree program: Bachelor’s in computer science (CS)

Project language: English
