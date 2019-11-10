Author: Tyler Ojala

This program implements Djikstra's algorithm by utilizing an adjacency matrix.
Once the graph is read in and the start node is read in also, the program iterates through all nodes and first
calculates the shortest distance to the start node from adjacent nodes then updates the shortest distances to an array
that stores shortest distances. While it is updateing the nearest nodes it also updates the current length of the path.
Both arrays store the information needed by having the index of an element represent a node so the array that holds the path
has each element hold the index of the next node in the tree while the distance array holds the distance to the start
node in each element. For each array calculation it needs to be iterated through for each node and the calculation for
each element is done for each node as well. So each array is calculated, which is O(N), an N number of times so the overall
complexity of the program is O(2N^2) which makes sense as for an adjaceny matrix implementation to find either the path
or cost it is O(N^2) but since I am finding both it would be twice as complex.

This program also assumes that the input file will be correct and does not have error handling for incorrect input files.
To run the program it is the same procedure that was given in the HW3 assignment file
first make sure that javac can be accessed by running the command set PATH=%PATH%;C:\Program Files\Java\version\bin where version is the version of java you are running.
Next run javac LinkState.java makeing sure that you are in the directory where LinkState.java is located in
Now you can run java LinkState fileIn startNode where fileIn is the filepath to the input file and startNode is the node you are calculating from
Ex. (java LinkState "C:\Users\Yler\Documents\in.txt" 3)
