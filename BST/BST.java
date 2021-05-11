import java.util.*;
public class BST {
	class Node{
		int value;
		Node left;
		Node right;
		Node(){
			left = right = null;
		}
		Node (int value) {
			this.value = value;	
		}
	}
	
	Node root; 
	BST(){
		this.root = null;
	}
	

	private void insertIterative(int value){
		if (root == null) {
			root = new Node(value);
			return;
		}
		Node current = root;
		while (true) {
			if (value < current.value) {
				if (current.left ==null){
					current.left = new Node(value);
					return;
				} else current = current.left;
			} else if (value > current.value) {
				if (current.right == null){
					current.right = new Node(value);
					return;
				} else current = current.right;
			} else { 
				return;
			}
		}
	}
	
	private Node addRecursive(Node current, int value) {
		if (current == null) {
			current = new Node(value);
			return current;
		}
		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value)  { 
			current.right = addRecursive(current.right, value);
		} 
		return root;
	}
	
	public void insertRecursive(int value) {
		root = addRecursive(root, value);
	}
	public void print(){
		preOrder(root);	
	}
	public void preOrder(Node current){
		if (current == null ) return; 
 		System.out.println(current.value);
		preOrder(current.left);
		preOrder(current.right);
	}
	

	public List<Integer>  BFS(){
		if (root == null) return null;
		List<Integer> data = new ArrayList<>();
		LinkedList<Node> queue = new LinkedList<>();
		queue.addFirst(root);
		while (queue.size() != 0) {
			Node tmp = queue.removeFirst();
			data.add(tmp.value);
			if (tmp.left != null) {
				queue.addLast(tmp.left);
			}
			if (tmp.right != null) {
				queue.addLast(tmp.right);
			}
		}
		return data;
	}
	
	public void printBFS(){
		List<Integer> data = BFS();
		for (Integer number : data ) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
	public static void main(String... args){
		BST tree = new BST();
		
		//Generate random numbers 
		Random random = new Random();
		for (int i  = 0; i < 20; i++) {
			int number = random.nextInt(100);
			tree.insertIterative(number);
		}
		tree.print();	
		tree.printBFS();
	}
}
