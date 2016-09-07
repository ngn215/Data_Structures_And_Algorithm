
public class BinarySearchTree {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree bst = new BinarySearchTree();
		
		Node root = new Node(10);
		bst.insertIntoBST(root, new Node(5));
		bst.insertIntoBST(root, new Node(4));
		bst.insertIntoBST(root, new Node(15));
		
		System.out.print("InOrder Traversal : ");
		bst.printInOrderBST(root);
		
		System.out.println("");
		System.out.print("PreOrder Traversal : ");
		bst.printPreOrderBST(root);
		
		System.out.println("");
		System.out.print("PostOrder Traversal : ");
		bst.printPostOrderBST(root);
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
}
