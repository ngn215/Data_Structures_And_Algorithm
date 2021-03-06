package DataStructuresAndAlgorithms;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


public class BinarySearchTree {
	
	boolean showSteps = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree bst = new BinarySearchTree();
		
		int[] nodesList = {10, 5, 4, 15, 20, 11, 25, 18, 12, 7}; //list of nodes to be inserted to build the tree
		bst.setShowSteps(true);
		Node root = bst.buildSampleBST(bst, nodesList);
		bst.setShowSteps(false);
		
		System.out.println("----------------------------------");
		System.out.print("InOrder Traversal : ");
		bst.printInOrderBST(root);
		
		System.out.println("");
		System.out.print("InOrder Traversal (iterative) : ");
		//bst.inOrderIterative(root);
		
		System.out.println("");
		System.out.print("PreOrder Traversal : ");
		bst.printPreOrderBST(root);
		
		System.out.println("");
		System.out.print("PostOrder Traversal : ");
		bst.printPostOrderBST(root);
		
		System.out.println("");
		System.out.print("DFS : ");
		bst.DFS(root);
		
		System.out.println("");
		System.out.print("BFS : ");
		bst.BFS(root);
		
		System.out.println("");
		System.out.println("----------------------------------");
		
		int leftMostNodeDistance = bst.calculateLeftMostNodeDistance(root, 0);
		bst.assignDistanceFromLeft(root, leftMostNodeDistance, 0, 0, "root");
		
		System.out.println("");
		System.out.println("=== Print Node distances and levels : ===");
		bst.printNodeDistancesBST(root);
		
		System.out.println("");
		System.out.println("=== Print BST Structure : ===");
		bst.printBSTStructure(root);
		
		System.out.println("");
		System.out.print("=== SEARCH ===");
		
		Node searchNode = null;
		for (int i=0; i<nodesList.length; i++)
		{
			System.out.println("");
			System.out.print("Searching Node : " + nodesList[i] + " ...");
			searchNode = bst.searchNodeInBST(root, nodesList[i]);
			if (searchNode == null)
				System.out.print("NOT found");
			else
				System.out.print("found !!");
		}
		
		System.out.println("");
		System.out.println("----------------------------------");
		System.out.println("");
		
		int[] nodesDeleteList = {15, 18, 25, 20, 12}; //list of nodes to be deleted to build the tree
		
		System.out.print("=== DELETE ===");
		for (int i=0; i<nodesDeleteList.length; i++)
		{
			System.out.println("");
			System.out.print("Delete node : " + nodesDeleteList[i] + " | ");
			bst.deleteNodeFromBST(root, nodesDeleteList[i]);
			bst.printInOrderBST(root);
		}
		
		System.out.println("");
		System.out.println("----------------------------------");
		
		System.out.println("");
		System.out.print("=== SEARCH after DELETE ===");
		searchNode = null;
		for (int i=0; i<nodesList.length; i++)
		{
			System.out.println("");
			System.out.print("Searching Node : " + nodesList[i] + " ...");
			searchNode = bst.searchNodeInBST(root, nodesList[i]);
			if (searchNode == null)
				System.out.print("NOT found");
			else
				System.out.print("found !!");
		}
		
	}

	public void setShowSteps(boolean showSteps) {
		this.showSteps = showSteps;
	}
	
	public Node buildSampleBST(BinarySearchTree bst, int[] nodesList)
	{
		Node root = null; //initializing root
		
		System.out.println("=== Constructing tree ===");
		
		for (int i=0; i<nodesList.length; i++)
		{
			if (i==0)
				root = new Node(nodesList[i]); //root node
			else
				bst.insertIntoBST(root, new Node(nodesList[i]));
		}
		
		return root;
	}
	
	public Node buildSampleBST(BinarySearchTree bst)
	{
		int[] nodesList = {10, 5, 4, 15, 20, 11, 25, 18, 12, 7}; //list of nodes to be inserted to build the tree
		
		return buildSampleBST(bst, nodesList);
	}
	
	public void insertIntoBST(Node root, Node node)
	{
		if(node.data < root.data)
		{
			if (root.left != null)
				insertIntoBST(root.left, node);
			else
			{
				if (showSteps)
				{
					System.out.println("   " + root.data);
					System.out.println("  /");
					System.out.println(node.data);
					System.out.println();
				}
				root.left = node;
			}
		}
		else if (node.data > root.data)
		{
			if (root.right != null)
				insertIntoBST(root.right, node);
			else
			{
				if (showSteps)
				{
					System.out.println(root.data);
					System.out.println(" \\");
					System.out.println("   " + node.data);
					System.out.println();
				}
				root.right = node;
			}
		}
		else
		{
			System.out.println("Node with value " + node.data + " already exists in tree");
		}
			
	}
	
	public Node deleteNodeFromBST(Node root, int data)
	{		
		if (showSteps)
			System.out.println("** deleteNodeFromBST(root: " + root.data + ", data: " + data + ")");
		
		if(data < root.data)
		{
			if (root.left != null)
			{
				if (showSteps)
					System.out.println("<-- " + root.data);
				
				root.left = deleteNodeFromBST(root.left, data);
			}
			else
			{
				System.out.println("Node with value : " + data + " is not present in the tree");
			}
		}
		else if (data > root.data)
		{
			if (root.right != null)
			{
				if (showSteps)
					System.out.println(root.data + " -->");
				
				root.right = deleteNodeFromBST(root.right, data);
			}
			else
			{
				System.out.println("Node with value : " + data + " is not present in the tree");
			}
		}
		else //node is found and we need to delete it
		{
			if (showSteps)
				System.out.println("Deleting Node : " + root.data);
			
			//node has both left and right children
			if(root.left != null && root.right != null)
			{				
				//get max node from left subtree
				int maxnodedata = getMinNode(root.right);
				if (showSteps)
					System.out.println("Min node of right subtree : " + maxnodedata);
				
				root.data = maxnodedata;
				root.right = deleteNodeFromBST(root.right, maxnodedata);
				
			}
			//node has either left or right children or none
			else
			{
				//if left subtree exists then return left subtree
				if (root.left != null)
				{
					return root.left;
				}
				//return right subtree
				else
				{
					return root.right;
				}
			}

		}
		
		return root;
	}
	
	public Node searchNodeInBST(Node root, int data)
	{	
		
		//System.out.println(root.data);
		
		Node temp = null;
		
		if(root.data == data)
			return root;
		
		if (root.left != null)
			temp = searchNodeInBST(root.left, data);
		
		if (temp == null && root.right != null)
			temp = searchNodeInBST(root.right, data);
		
		if (temp != null)
			return temp;
		else
			return null;
	}
	
	public void printInOrderBST(Node node)
	{
		if (node == null)
			return;
		
		printInOrderBST(node.left);
		System.out.print(node.data + " ");
		printInOrderBST(node.right);
	}
	
	public void printPreOrderBST(Node node)
	{
		if (node == null)
			return;
		
		System.out.print(node.data + " ");
		printPreOrderBST(node.left);
		printPreOrderBST(node.right);
	}
	
	public void printPostOrderBST(Node node)
	{
		if (node == null)
			return;
		
		printPostOrderBST(node.left);
		printPostOrderBST(node.right);
		System.out.print(node.data + " ");
	}
	
	public int getMinNode(Node root)
	{
		if (root.left != null)
		{
			return getMinNode(root.left);
		}
		
		return root.data;
	}
	
	public void assignDistanceFromLeft(Node node, int distanceFromLeft, int distanceFromCenter, int level, String rootOrleftOrRight)
	{
		if (node == null)
			return;
		
		if(rootOrleftOrRight.equals("root"))
			node.root = true;
		else if(rootOrleftOrRight.equals("left"))
			node.leftOfParent = true;
		else if(rootOrleftOrRight.equals("right"))
			node.leftOfParent = false;
		
		assignDistanceFromLeft(node.left, distanceFromLeft - 1, distanceFromCenter - 1, level + 1, "left");
		
		node.distanceFromLeft = distanceFromLeft;
		//node.distanceFromCenter = distanceFromCenter;
		node.level = level;
		//System.out.println("Node : " + node.data + ", Distance : " + node.distanceFromLeft);
		
		assignDistanceFromLeft(node.right, distanceFromLeft + 1, distanceFromCenter + 1, level + 1, "right");
	}
	
	public void printNodeDistancesBST(Node node)
	{
		if (node == null)
			return;
		
		printNodeDistancesBST(node.left);
		System.out.println("Node : " + node.data 
							+ ", DistanceFromLeft : " + node.distanceFromLeft 
							//+ ", DistanceFromCenter : " + node.distanceFromCenter
							+ ", Level : " + node.level);
		printNodeDistancesBST(node.right);
	}
	
	public int calculateLeftMostNodeDistance(Node root, int distance)
	{
		if(root.left != null)
			return calculateLeftMostNodeDistance(root.left, distance + 1);
		
		return distance;
	}
	
	public void printBSTStructure(Node root)
	{
		if (root == null)
			return;
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		while (!q.isEmpty())
		{			
			int tempNodeLevel = q.peek().level; 
			Node temp;
			
			Queue<Node> nodesQueue = new LinkedList<Node>();
			while(!q.isEmpty() && q.peek().level == tempNodeLevel)
			{
				temp = q.remove();
				//System.out.println(temp.data);
				nodesQueue.add(temp);
				
				if(temp.left != null)
				{
					q.add(temp.left);
				}
				
				if(temp.right != null)
				{
					q.add(temp.right);
				}
			}
			
			if (!nodesQueue.isEmpty())
			{
				treeAtLevelGenerator(nodesQueue);
				nodesQueue.clear();
			}
			
		}
		
	}
	
	public String spaceGenerator(int currDistance, int previousDistance)
	{
		String spaceToBeAdded = " ";
		String spaceToBeReturned = "";
		
		
		if(currDistance != 0 && currDistance == previousDistance)
			return spaceToBeAdded;
		
		for(int i=previousDistance; i < currDistance; i++)
			spaceToBeReturned += spaceToBeAdded;
		
		return spaceToBeReturned;
	}
	
	public void treeAtLevelGenerator(Queue<Node> nodesQueue)
	{
		//System.out.println("treeAtLevelGenerator");
		int previousdistanceFromLeft = 0;
		String nodesString = "";
		String connectorString = "";
		
		while(!nodesQueue.isEmpty())
		{
			Node temp = nodesQueue.remove();
			//System.out.println("treeAtLevelGenerator : " + temp.data);
			
			//System.out.println("currDistance : " + temp.distanceFromLeft + " ," + "previousdistanceFromLeft : " + previousdistanceFromLeft);
			String space = spaceGenerator(temp.distanceFromLeft, previousdistanceFromLeft);
			//System.out.println("SPACE" + space + "SPACE");
			
			if(temp.root)
			{
				nodesString += space + temp.data;
			}
			else if (temp.leftOfParent)
			{
				connectorString += space + "/";
				nodesString += space + temp.data;
			}
			else if (!temp.leftOfParent)
			{
				connectorString += space + "\\";
				nodesString += space + temp.data;
			}
			
			previousdistanceFromLeft = temp.distanceFromLeft;
		}
		
		System.out.println(connectorString);
		System.out.println(nodesString);
	}

	public void DFS(Node root)
	{
		//same as preorder traversal
		
		if(root == null)
			return;
		
		System.out.print(root.data + " ");
		DFS(root.left);
		DFS(root.right);
	}
	
	public void BFS(Node root)
	{
		if (root == null)
			return;
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		while(!q.isEmpty())
		{
			Node temp = q.remove();
			System.out.print(temp.data + " ");
			
			if(temp.left != null)
				q.add(temp.left);
			
			if(temp.right != null)
				q.add(temp.right);
		}
	}

	//needs to be fixed.
	public void inOrderIterative(Node root)
	{
		if (root==null)
			return;
		
		Stack<Node> s = new Stack<Node>();
		Node curr = root;
		s.push(curr);
		System.out.print(curr.data);
		
		while(curr != null)
		{
			System.out.print(curr.left.data);
			s.push(curr.left);
			curr = curr.left;
			
			while(!s.empty() && curr == null)
			{		
				curr = s.pop();
				System.out.print(curr.data + " ");
				
				curr = curr.right;
			}
			
			if(s.isEmpty() && curr == null)
				break;
		}
		
	}
}

/*OUTPUT
=== Constructing tree ===
   10
  /
5

   5
  /
4

10
 \
   15

15
 \
   20

   15
  /
11

20
 \
   25

   20
  /
18

11
 \
   12

5
 \
   7

----------------------------------
InOrder Traversal : 4 5 7 10 11 12 15 18 20 25 
PreOrder Traversal : 10 5 4 7 15 11 12 20 18 25 
PostOrder Traversal : 4 7 5 12 11 18 25 20 15 10 
DFS : 10 5 4 7 15 11 12 20 18 25 
BFS : 10 5 15 4 7 11 20 12 18 25 
----------------------------------

=== Print Node distances and levels : ===
Node : 4, DistanceFromLeft : 0, Level : 2
Node : 5, DistanceFromLeft : 1, Level : 1
Node : 7, DistanceFromLeft : 2, Level : 2
Node : 10, DistanceFromLeft : 2, Level : 0
Node : 11, DistanceFromLeft : 2, Level : 2
Node : 12, DistanceFromLeft : 3, Level : 3
Node : 15, DistanceFromLeft : 3, Level : 1
Node : 18, DistanceFromLeft : 3, Level : 3
Node : 20, DistanceFromLeft : 4, Level : 2
Node : 25, DistanceFromLeft : 5, Level : 3

=== Print BST Structure : ===

  10
 /  \
 5  15
/  \ /  \
4  7 11  20
   \ /  \
   12 18  25

=== SEARCH ===
Searching Node : 10 ...found !!
Searching Node : 5 ...found !!
Searching Node : 4 ...found !!
Searching Node : 15 ...found !!
Searching Node : 20 ...found !!
Searching Node : 11 ...found !!
Searching Node : 25 ...found !!
Searching Node : 18 ...found !!
Searching Node : 12 ...found !!
Searching Node : 7 ...found !!
----------------------------------

=== DELETE ===
Delete node : 15 | 4 5 7 10 11 12 18 20 25 
Delete node : 18 | 4 5 7 10 11 12 20 25 
Delete node : 25 | 4 5 7 10 11 12 20 
Delete node : 20 | 4 5 7 10 11 12 
Delete node : 12 | 4 5 7 10 11 
----------------------------------

=== SEARCH after DELETE ===
Searching Node : 10 ...found !!
Searching Node : 5 ...found !!
Searching Node : 4 ...found !!
Searching Node : 15 ...NOT found
Searching Node : 20 ...NOT found
Searching Node : 11 ...found !!
Searching Node : 25 ...NOT found
Searching Node : 18 ...NOT found
Searching Node : 12 ...NOT found
Searching Node : 7 ...found !!
 */
