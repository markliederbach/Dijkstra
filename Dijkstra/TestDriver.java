package Dijkstra;

import java.util.ArrayList;

public class TestDriver implements Dijkstra{
	
	/*
	 * Basic steps to using Dijkstra interface:
	 * 		1. Create your nodes
	 * 		2. Create Edges between nodes by adding neighbor and weight to a node
	 * 			a. If undirected, you need to create edges from BOTH sides. Else it's only one way.
	 * 		3. Add Edges to 
	 * 		4. Create RunDijkstra object
	 * 		5. Assuming you've set source to desired node, call findPath method to return ArrayList of path (in order)
	 * 		6. Source can be changed at any time.
	 * 		
	 * 
	 * 
	 */

	
	//Good example on webpage: http://improve.dk/generic-dijkstras-algorithm/
	
	//Example Run
	public static void main(String[] arg){
	//Testing dijkstra's algorithm interface
	//Create Nodes
	Node U = new Node("U");
	Node X = new Node("X");
	Node W = new Node("W");
	Node V = new Node("V");
	Node Y = new Node("Y");
	Node Z = new Node("Z");
	
	//Create edges
	
	//Multiple ways of adding neighbors
	//1. Manual define each edge
	U.singleEdge(X, 5);
	X.singleEdge(U, 5);
	W.singleEdge(V, 3);
	V.singleEdge(W, 3);
	
	//2. Create two edges at once, each is equally weighted
	U.uniformEdge(W, 3);
	U.uniformEdge(V, 7);
	X.uniformEdge(Y, 7);
	X.uniformEdge(Z, 9);
	W.uniformEdge(Y, 8);
	V.uniformEdge(Y, 4);
	Y.uniformEdge(Z, 2);
	
	//3. Create two edges between nodes that are not equal
	X.nonUniformEdge(W, 4, 4); //first weight: X to W; second weight: W to X
	
	
	
	
	

	
	
	
	

	
	
	
	
	//Now that all nodes are defined and have relationships to other nodes, make an ArrayList
	ArrayList<Node> map = new ArrayList<Node>();
	map.add(U);
	map.add(X);
	map.add(W);
	map.add(V);
	map.add(Y);
	map.add(Z);
	
	//We will use Node U as our initial source
	
	//Initialize RunDijkstra class
	RunDijkstra test = new RunDijkstra(map,U);
	
	//Find path from U to target: Z
	
	ArrayList<Node> result = test.findPath(Z);
	
	//Print result
	printNodeArrayList(result);
	
	
	
	//Change source to Z
	test.changeSource(map.get(5)); //Another way of changing source
	
	//Find route from Z to target: W
	printNodeArrayList(test.findPath(W));
	
	
	//Change to source NOT in map
	Node newNode = new Node("M");
	
	//Attempt to find path from M (will not run)
	test.findPath(newNode);
	
	//Attempt to change source to M (even though it's not in the map)
	System.out.println(test.changeSource(newNode));//returns false
	
	
	
	
	//How to update a Node's Object data through Dijkstra
	//	This can be useful if you want to append more data to a map node
	String str = "Hello, you!";
	U.updateObject(str);
	//U now contains this string and will retain it.
	System.out.println(U.getObject());
	
	
	
	
	}//End main

	public static void printNodeArrayList(ArrayList<Node> in){
		if(in != null){
		for(Node n: in){
			System.out.print(n.toString() + " ");
		}
		System.out.println();
		}else{
			System.out.println("No route found...");
		}
	}
	
	
}
