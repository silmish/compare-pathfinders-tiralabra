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


