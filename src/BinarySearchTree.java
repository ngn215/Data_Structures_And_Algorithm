import java.util.Queue;
import java.util.LinkedList;


public class BinarySearchTree {
	
	boolean showSteps = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree bst = new BinarySearchTree();
		
		int[] nodesList = {10, 5, 4, 15, 20, 11, 25, 18, 12, 7}; //list of nodes to be inserted to build the tree
		Node root = null; //initializing root
		
		System.out.println("Constructing tree");
		
		for (int i=0; i<nodesList.length; i++)
		{
			if (i==0)
				root = new Node(nodesList[i]); //root node
			else
				bst.insertIntoBST(root, new Node(nodesList[i]));
		}
		
		System.out.println("----------------------------------");
		System.out.print("InOrder Traversal : ");
		bst.printInOrderBST(root);
		
		System.out.println("");
		System.out.print("PreOrder Traversal : ");
		bst.printPreOrderBST(root);
		
		System.out.println("");
		System.out.print("PostOrder Traversal : ");
		bst.printPostOrderBST(root);
		
		int leftMostNodeDistance = bst.calculateLeftMostNodeDistance(root, 0);
		bst.assignDistanceFromLeft(root, leftMostNodeDistance, 0, 0, "root");
		
		System.out.println("");
		System.out.println("");
		System.out.println("=== Print Node distances and levels : ===");
		bst.printNodeDistancesBST(root);
		
		System.out.println("");
		System.out.println("Print BST Structure : ");
		bst.printBSTStructure(root);
		
		
		System.out.println("");
		System.out.println("----------------------------------");
		
		int[] nodesDeleteList = {15, 18, 25, 20, 12}; //list of nodes to be deleted to build the tree
		
		for (int i=0; i<nodesDeleteList.length; i++)
		{
			System.out.println("");
			System.out.println("==== Delete node : " + nodesDeleteList[i] + " ====");
			bst.deleteNodeFromBST(root, nodesDeleteList[i]);
			
			System.out.println("");
			System.out.print("InOrder Traversal : ");
			bst.printInOrderBST(root);
			System.out.println("");
		}
	}

	public void insertIntoBST(Node root, Node node)
	{
		if(node.data < root.data)
		{
			if (root.left != null)
				insertIntoBST(root.left, node);
			else
			{
				System.out.println("   " + root.data);
				System.out.println("  /");
				System.out.println(node.data);
				System.out.println();
				root.left = node;
			}
		}
		else if (node.data > root.data)
		{
			if (root.right != null)
				insertIntoBST(root.right, node);
			else
			{
				System.out.println(root.data);
				System.out.println(" \\");
				System.out.println("   " + node.data);
				System.out.println();
				root.right = node;
			}
		}
		else
		{
			System.out.println("Node with value " + node.data + " already exists in tree");
		}
			
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
	
	public int getMinNode(Node root)
	{
		if (root.left != null)
		{
			return getMinNode(root.left);
		}
		
		return root.data;
	}
}

/*OUTPUT
Constructing tree
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

Print BST Structure : 

  10
 /  \
 5  15
/  \ /  \
4  7 11  20
   \ /  \
   12 18  25

----------------------------------

==== Delete node : 15 ====

InOrder Traversal : 4 5 7 10 11 12 18 20 25 

==== Delete node : 18 ====

InOrder Traversal : 4 5 7 10 11 12 20 25 

==== Delete node : 25 ====

InOrder Traversal : 4 5 7 10 11 12 20 

==== Delete node : 20 ====

InOrder Traversal : 4 5 7 10 11 12 

==== Delete node : 12 ====

InOrder Traversal : 4 5 7 10 11 

 */
