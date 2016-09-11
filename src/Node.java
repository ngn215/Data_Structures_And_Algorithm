
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
	
	Node(int data)
	{
		this.data = data;
		this.root = false;
		this.leftOfParent = false;
	}
}
