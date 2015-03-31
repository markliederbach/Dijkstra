package Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

public interface Dijkstra {
	
	static final AtomicLong NEXT_ID = new AtomicLong(0);
	
	
	public class Node implements Comparable<Node>{

		public String name;
		public final long id;
		private Object data;
		public ArrayList<Edge> neighbors = new ArrayList<Edge>();
		public double minDistance = Double.POSITIVE_INFINITY;
		public Node previous;
		
		public Node(String name){
			this.name = name;
			this.id = NEXT_ID.getAndIncrement();
			this.data = null;
		}
		public Node(String name, Object data){
			this.name = name;
			this.id = NEXT_ID.getAndIncrement();
			this.data = data;
		}
		public Object getObject(){
			return this.data;
		}
		public String toString(){
			return this.name;
		}
		public ArrayList<String> stringNeighbors(){
			ArrayList<String> neighbors = new ArrayList<String>();
			for(Edge e: this.neighbors){
				neighbors.add(e.toNode.toString());
			}
			return neighbors;
		}
		public int compareTo(Node arg0) {
			//Returns: 0 if equal, <0 if arg0 is greater, >0 if arg0 is lesser
			return Double.compare(this.minDistance, arg0.minDistance);
		}
		public boolean addNeighbor(Node neigh, double weight){
			if(this.neighbors != null){
			for(Edge n: this.neighbors){
				if(n.toNode.id == neigh.id){
					return false;
			}
			}
			}
				this.neighbors.add(new Edge(neigh,weight));
				return true;
			
			
		}
		
	}//End Node Class
	
	
	
	public class Edge{
		public final Node toNode;
		public final double weight;	//Distance from parent source node to toNode
		
		public Edge(Node toNode, double weight){
			this.toNode = toNode;
			this.weight = weight;
		}
	}//End Edge Class
	
	public class RunDijkstra{//Allows multiple path computations from a set source
		public final ArrayList<Node> nodes;	//ArrayList of Nodes (each contain their own ArrayList of neighbors)
		public Node source;
		
		public RunDijkstra(ArrayList<Node> nodes, Node source){
			this.nodes = nodes;
			
			//check that source is in map
			for(Node n: this.nodes){
				if(source.id == n.id)
					this.source = source;
			}
			//if not, display error
			if(this.source.id != source.id){
				System.err.println("Source node is not in the map!");
				System.exit(0);
			}
			
		}//End constructor
		
		public boolean changeSource(Node newSource){//not working
			for(Node n: this.nodes){
				if(newSource.id == n.id)
					this.source = newSource;
			}
			//if not, display error
			if(this.source.id != newSource.id){
				System.out.println("New source node is not in the map!");
				return false;
			}
			return true;
		}//End changeSource
		
		public ArrayList<Node> findPath(Node target){
			if(!this.nodes.contains(target)){
				System.out.println("Target node is not in the map!");
				return null;
			}
			
			ArrayList<Node> result = new ArrayList<Node>();
			ArrayList<Node> localList = new ArrayList<Node>();
			
			//Initialization
			//load all nodes to local list
			for(Node n: this.nodes){
				localList.add(n);
				//reset all distances to infinity
				n.minDistance = Double.POSITIVE_INFINITY;
			}

			//set source dist to 0
			source.minDistance = 0;
			
			//Loop
			while(localList.size()>0){
				//load node in localList with next minimum distance
				//and remove it from localList
				Collections.sort(localList);
				Node curr = localList.remove(0);
				
				//for all neighbors of curr
				for(Edge n: curr.neighbors){
					
					double dist = curr.minDistance + n.weight;
					if(dist < n.toNode.minDistance){
						n.toNode.minDistance = dist;
						n.toNode.previous = curr;
					}
					
				}
				if(curr.id == target.id){
					break;
				}
				
			}
			
			//We now have all information to construct the path from source to target
			Node tempTarget = target;
			while(tempTarget.previous != null){
				result.add(tempTarget);
				tempTarget = tempTarget.previous;
			}
			result.add(source);
			Collections.reverse(result);
			
			flushData();
			
			if(result.size()>1){
			return result;
			}else{
				return null;
			}
			
			
			
		}
		
		private void flushData(){
			for(Node n: this.nodes){
				n.minDistance = Double.POSITIVE_INFINITY;
				n.previous = null;
			}
		}
		
		
	}//End RunDijkstra
	
	
}//End interface

