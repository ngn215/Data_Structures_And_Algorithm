
public class LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList ll = new LinkedList();
		
		int[] nodesList = {10, 5, 4, 15, 20, 11, 25, 18, 12}; //list of nodes to be inserted to build the linked list
		System.out.print("Linked list nodes : ");
		for (int i=0; i<nodesList.length; i++)
			System.out.print(nodesList[i] + " ");
		
		System.out.println("");
		int[] nodesDoublyList = {1, 2, 3, 4, 5, 6, 7, 8, 9}; //list of nodes to be inserted to build the doubly linked list
		System.out.print("Doubly Linked list nodes : ");
		for (int i=0; i<nodesDoublyList.length; i++)
			System.out.print(nodesDoublyList[i] + " ");
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Constructing linked list ===");
		Node head = ll.buildLinkedList(nodesList);

		System.out.println("");
		System.out.println("=== Printing linked list (iterative) ===");
		ll.printLinkedList(head);
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Printing linked list (recursively) ===");
		ll.printLinkedListRecursively(head);
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Constructing Doubly linked list ===");
		Node head2 = ll.buildLinkedList(nodesDoublyList);
		ll.printLinkedList(head2);		
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Finding Node : 12 in linked list ===");
		Node element = ll.findElement(head, 12);
		System.out.println("Node : " + element.data + " found");
		
		System.out.println("");
		System.out.println("=== Finding Node : 12 (recursively) in linked list ===");
		element = ll.findElementRecursively(head, 12);
		System.out.println("Node : " + element.data + " found");
		
		System.out.println("");
		System.out.println("=== Finding Node : 7 in linked list ===");
		element = ll.findElement(head, 7);
		if (element != null)
			System.out.println("Node : " + element.data + " found");
		
		System.out.println("");
		System.out.println("=== Finding Node : 7 (recursively) in linked list ===");
		element = ll.findElementRecursively(head, 7);
		if (element != null)
			System.out.println("Node : " + element.data + " found");
		
		System.out.println("");
		System.out.println("=== Reversing linked list ===");
		head = ll.reverseLinkedList(head);
		ll.printLinkedList(head);		
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Reversing linked list (recursive) ===");
		head = ll.reverseLinkedListRecursively(head);
		ll.printLinkedList(head);		
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Reversing Doubly linked list (iterative) ===");
		head2 = ll.reverseDoublyLinkedList(head2);
		ll.printLinkedList(head2);
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Reversing Doubly linked list (recursive) ===");
		head2 = ll.reverseDoublyLinkedListRecursively(head2);
		ll.printLinkedList(head2);
		
	}
	
	//Building singly linked list. returns head node.
	public Node buildLinkedList(int[] nodesList)
	{
		Node head = null;
		Node prev = null;
		
		for (int i=0; i<nodesList.length; i++)
		{
			if(i==0)
			{
				head = new Node(nodesList[i]);
				prev = head; 
			}
			else
			{
				Node curr = new Node(nodesList[i]);
				prev.next = curr;
				prev=curr;
			}
		}
		
		return head;
	}
	
	//Building singly linked list. returns head node.
	public Node buildDoublyLinkedList(int[] nodesList)
	{
		Node head = null;
		Node prev = null;
		
		for (int i=0; i<nodesList.length; i++)
		{
			if(i==0)
			{
				head = new Node(nodesList[i]);
				prev = head; 
			}
			else
			{
				Node curr = new Node(nodesList[i]);
				prev.next = curr;
				curr.prev = prev;
				prev=curr;
			}
		}
		
		return head;
	}
	
	//returns node if found (iterative method)
	public Node findElement(Node head, int data)
	{
		while(head != null)
		{
			if(head.data == data)
				return head;
			else
				head = head.next;
		}
		
		System.out.println("Node not present in linked list");
		return null;
	}
	
	//returns node if found (recursive method)
	public Node findElementRecursively(Node head, int data)
	{
		if (head == null)
		{
			System.out.println("Node not present in linked list");
			return null;
		}
		else if (head.data == data) 
			return head;
		else
			return findElementRecursively(head.next, data);
	}
	
	//reverses linked list and returns new head node (iterative method)
	public Node reverseLinkedList(Node head)
	{
		Node prev = null;
		Node curr = head;
		
		while(curr != null)
		{			
			Node next = curr.next; //backing up next node
			curr.next = prev; //setting previous node as next node of current node
			prev = curr; //make previous node as current node
			curr = next; //make next node as current node
		}
		
		return prev; //return the new head node
	}
	
	//reverses linked list and returns new head node (recursive method)
	public Node performReverseLinkedListRecursively(Node prev, Node curr)
	{
		if (curr == null)
			return prev;
		else
		{
			Node next = curr.next; //backing up next node
			curr.next = prev; //setting previous node as next node of current node
			
			//recursive call with curr (which becomes previous node for next call) and next (which becomes current node for next call)
			return performReverseLinkedListRecursively(curr, next);
		}
		
	}
	
	//driver for recursive reverse function
	public Node reverseLinkedListRecursively(Node head)
	{
		return performReverseLinkedListRecursively(null, head);
	}
	
	//reverses Doubly linked list and returns new head node (iterative method)
	public Node reverseDoublyLinkedList(Node head)
	{
		Node prev = null;
		Node curr = head;
		
		while(curr != null)
		{			
			Node next = curr.next; //backing up next node
			curr.next = prev; //setting previous node as next node of current node
			
			if (prev !=null) //ignore this for root node
				prev.prev = curr; //setting current node as previous node of previous node
			
			prev = curr; //make previous node as current node
			curr = next; //make next node as current node
		}
		
		return prev; //return the new head node
	}
	
	//reverses Doubly linked list and returns new head node (recursive method)
	public Node performReverseDoublyLinkedListRecursively(Node prev, Node curr)
	{
		if (curr == null)
			return prev;
		else
		{
			Node next = curr.next; //backing up next node
			curr.next = prev; //setting previous node as next node of current node
			
			if (prev !=null) //ignore this for root node
				prev.prev = curr; //setting current node as previous node of previous node
			
			//recursive call with current as previous node and next node as current node
			return performReverseDoublyLinkedListRecursively(curr, next);
		}
		
	}
	
	public Node reverseDoublyLinkedListRecursively(Node head)
	{
		return performReverseDoublyLinkedListRecursively(null, head);
	}
	
	//prints entire linked list (iterative method)
	public void printLinkedList(Node head)
	{
		while(head != null)
		{
			System.out.print(head.data + " ");
			head = head.next;
		}
	}
	
	//prints entire linked list (recursive method)
	public void printLinkedListRecursively(Node head)
	{
		if (head == null)
			return;
		else
		{
			System.out.print(head.data + " ");
			printLinkedListRecursively(head.next);
		}
		
	}

}

/*
OUTPUT

10 5 4 15 20 11 25 18 12 

=== Constructing linked list ===

=== Printing linked list (iterative) ===
10 5 4 15 20 11 25 18 12 

=== Printing linked list (recursively) ===
10 5 4 15 20 11 25 18 12 

=== Constructing Doubly linked list ===

=== Printing doubly linked list ===
10 5 4 15 20 11 25 18 12 

=== Finding Node : 12 in linked list ===
Node : 12 found

=== Finding Node : 25 (recursively) in linked list ===
Node : 12 found

=== Finding Node : 7 in linked list ===
Node not present in linked list

=== Finding Node : 7 (recursively) in linked list ===
Node not present in linked list

=== Reversing linked list ===

=== Printing linked list (iterative) ===
12 18 25 11 20 15 4 5 10 

=== Reversing linked list (recursive) ===

=== Printing linked list (iterative) ===
10 5 4 15 20 11 25 18 12 

 */
