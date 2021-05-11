package Graph;
import java.util.*;
import java.util.stream.*;


public class Graph{
	static class Node {
		public String name;
		public int value;
		Node(){
			value = 0;
		}
		Node(String name, int value){
			this.value = value;
			this.name = name;
		}
	}		
	public Map<Node, List<Node>>  adjacencyList;
	Graph(){
		adjacencyList = new HashMap<>();
	}
	public void addVertex(Node node){
		adjacencyList.put(node, new ArrayList<>());

	}
	
	public void addEdge(Node vertex1, Node vertex2) {
		if (!adjacencyList.containsKey(vertex1)) {
			addVertex(vertex1);
		}
		if (!adjacencyList.containsKey(vertex2)) {
			addVertex(vertex2);
		}
		List<Node> edge1 = adjacencyList.get(vertex1);
		List<Node> edge2 = adjacencyList.get(vertex2);
		if (!edge1.contains(vertex2)) edge1.add(vertex2);
		if (!edge2.contains(vertex1)) edge2.add(vertex1);
	}	


	public void removeEdge(Node vertex1, Node vertex2) {
		List<Node> list1 =  adjacencyList.get(vertex1).stream().filter(v -> v != vertex2).collect(Collectors.toList());
		List<Node> list2 = adjacencyList.get(vertex2);
		list2 = list2.stream().filter(v -> v != vertex1).collect(Collectors.toList());		
		adjacencyList.get(vertex2) = list2;
		adjacencyList.get(vertex1) = list1;
	}


	public void print(){
		for (Node key : adjacencyList.keySet() ) {
				System.out.printf("%s : ", key.name );
			for (Node elem : adjacencyList.get(key)) {
				System.out.printf(" %s ", elem.name);
			} 
			System.out.println();
		}
	}


	public static void main (String... args){
		Graph gr = new Graph();
		Node n1 = new Node("tokyo", 15);
		Node n2 = new Node("Almaty", 150);
		gr.addVertex(n1);
		gr.addVertex(n2);
		gr.addEdge(n2, new Node("Taraz", 200));
		gr.addEdge(n1, n2);
		gr.print();
	}
}
