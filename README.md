# flowers_sweeper



## Installation
To run FlowerSweeper, ensure you have Java installed on your system. You can compile and run the program directly using any IDE or from the command line with javac and java.

1. Clone or download the repository.
2. Navigate to the project directory.

## Compilation
```
javac *.java
```
## Running the program
```
java Menu.Main

```
## Usage
Gameplay

In FlowerSweeper, you have a grid filled with hidden "flowers" (mines) that you must avoid. Your task is to clear the grid without triggering a flower.

Left Click: Reveals the number of adjacent flowers. If there are no adjacent flowers, it will automatically reveal surrounding cells.

Right Click: Flags a cell to mark it as potentially containing a flower.
Objective: Flag all the flowers and uncover all safe spots to win the game.

## Features
* A fully interactive Swing-based interface.
* Timer and score tracking to challenge yourself.
* Flagging system to help keep track of possible flower locations.
* Random flower distribution to ensure unique game experiences each time.