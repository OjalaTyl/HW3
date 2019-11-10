import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class LinkState {
	//This is used in the tree path to show that a node is the source
	private static final int End_Node = -1;

	// Main code opens the file and reads in the adjacency matrix, then read the start node
	public static void main(String[] args) 
	{
		File file = new File(args[0]);
		Scanner scan;
		try 
		{
			
			scan = new Scanner(file);
			int numNodes = scan.nextInt();
			double[][] adMatrix = new double[numNodes][numNodes];
			
			while (scan.hasNextLine()) {
				int nodeA = scan.nextInt();
				int nodeB = scan.nextInt();
				double cost = scan.nextDouble();
				adMatrix[nodeA][nodeB] = cost;
				adMatrix[nodeB][nodeA] = cost;			
			}
			
			int source = Integer.parseInt(args[1]);

			dijkstra(adMatrix, source);
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem make sure your file path and file contents are valid.");
			System.out.println("The exact error is as follows:");
			e.printStackTrace();
		}
	}
	
	// This is a function that implements Dijkstra's shortest path
	// algorithm for a graph represented by an adjacency matrix
	// and storing two arrays. One holds the shortest distances from
	// node n to the start. The other holds the information to the next
	// node in the path ex. node 2 to 0 with path 2>1>0 [-1,0,1]
	private static void dijkstra(double[][] adMatrix, int startVertex) 
	{
		
		int nVertices = adMatrix[0].length;

		double[] shortestDistances = new double[nVertices];
		
		
		// Used to check if a node is processed later
		boolean[] added = new boolean[nVertices];

		// Initialize all distances to max and set all nodes to unprocessed
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
		{
			shortestDistances[vertexIndex] = Double.MAX_VALUE;
			added[vertexIndex] = false;
		}

		shortestDistances[startVertex] = 0;

		// Array used to store shortest path tree
		int[] parents = new int[nVertices];
		parents[startVertex] = End_Node;

		// Loop to find 1
		for (int i = 1; i < nVertices; i++) 
		{
			
			int nearestVertex = -1;
			double shortestDistance = Double.MAX_VALUE;
			
			// Picks the minimum distance vertex from the set of vertices not yet processed.
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) 
				{
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			added[nearestVertex] = true;

			// Update distance value of the adjacent vertices of the current vertex.
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
			{
				double edgeDistance = adMatrix[nearestVertex][vertexIndex];

				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) 
				{
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}

		printSolution(startVertex, shortestDistances, parents);
	}

	// A function that prints all paths by iterating through all nodes other that the start node
	// and calling another function to print that node's shortest path.
	private static void printSolution(int startV, double[] distances, int[] nextVerteces) 
	{
		int nVertices = distances.length;

		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
		{
			if (vertexIndex != startV) 
			{
				System.out.print("Shortest path to node " + vertexIndex + " is ");
				printPath(vertexIndex, nextVerteces, vertexIndex);
				System.out.println(" with cost " + distances[vertexIndex]);
			}
		}
	}

	//A function that recursively iterates through an array of vertices 
	//to print out the shortest path between the start node and the host node
	private static void printPath(int currentVertex, int[] parents, int host) {

		//Base case to end the recursive loop
		if (currentVertex == End_Node) {
			return;
		}
		else
		
		printPath(parents[currentVertex], parents, host);
		System.out.print(currentVertex);
		if(currentVertex != host)
			System.out.print("->");
	}
}
