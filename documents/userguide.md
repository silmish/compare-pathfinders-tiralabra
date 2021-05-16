# User guide

## Installation

Download the [release](https://github.com/silmish/compare-pathfinders-tiralabra/releases/tag/v1.0) or clone the code:

```
git clone git@github.com:silmish/compare-pathfinders-tiralabra.git
```

## Initial start

Go to the project folder in terminal and run:

```
gradle clean build
```

This initiates all the reports and checks that tests are successfull.

## Run the program

To run the program use command in terminal:

```
gradle run
```

This starts the program if you are in the right folder.

## Jacoco test report

You can generate a new test report by running the command:

```
gradle test jacocoTestReport
```

The report can be found in pathfinder/build/reports/jacoco/test/html/index.html

## How to use the program

Run the program with the instructions above.

Choose one of the algorithms by clicking the check box.

After this choose either start or end check box and click on the white are on the map.

After this press start and the shortest path determined by the algorithm you chose will be drawn.

You can choose same points and swap algorithms to compare paths easily.

When the map get cluttered or want clear view then press clear and everything gets back to original state. 
