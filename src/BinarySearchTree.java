
public class BinarySearchTree {
	
	boolean showSteps = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree bst = new BinarySearchTree();
		
		int[] nodesList = {10, 5, 4, 15, 20, 11, 25, 18, 12}; //list of nodes to be inserted to build the tree
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

----------------------------------
InOrder Traversal : 4 5 10 11 12 15 18 20 25 
PreOrder Traversal : 10 5 4 15 11 12 20 18 25 
PostOrder Traversal : 4 5 12 11 18 25 20 15 10 
----------------------------------

==== Delete node : 15 ====

InOrder Traversal : 4 5 10 11 12 18 20 25 

==== Delete node : 18 ====

InOrder Traversal : 4 5 10 11 12 20 25 

==== Delete node : 25 ====

InOrder Traversal : 4 5 10 11 12 20 

==== Delete node : 20 ====

InOrder Traversal : 4 5 10 11 12 

==== Delete node : 12 ====

InOrder Traversal : 4 5 10 11 
 */
