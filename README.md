Script Playground
=================

Welcome to the RPTools MapTool scripting "playground".
This project is used for testing out/playing with new ideas for the dice roller (and eventually) the new scripting framework

To get started you will need Java 11+ installed
1. Clone / Download the repository
2. ./gradlew run 
(.\gradlew run on windows)



Usage
-----
Enter the text you want to test into the bottom text area, Dice Rolls between [[ ]], eg [[ 4d6 ]].

See https://github.com/RPTools/dice/wiki for details on dice rolls.



Warning
-------
There are still many issues -- hey you have to start somewhere -
The formatting of multiple rolls e.g. [[ 1d6 ; 2d4]] is wrong and its not forgiving at all on syntax errors.
But it is just a start and will be improved