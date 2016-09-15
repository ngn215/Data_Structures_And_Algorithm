package DataStructuresAndAlgorithms;

public class Node {

	Node left; //used for tree
	Node right; //used for tree
	Node next; //used for linked list
	Node prev; //used for doubly linked list
	int data;
	int distanceFromLeft; //will be used to construct BST
	//int distanceFromCenter; //will be used to construct BST
	int level; //will be used to construct BST
	boolean leftOfParent;
	boolean root;
	
	public Node(int data)
	{
		this.data = data;
		this.root = false;
		this.leftOfParent = false;
		this.left = null;
		this.right = null;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}
