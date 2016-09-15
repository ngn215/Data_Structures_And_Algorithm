//Exercise : Print extreme nodes of each level of Binary Tree in alternate order

package Exercises;

import java.util.LinkedList;
import java.util.Queue;

import DataStructuresAndAlgorithms.BinarySearchTree;
import DataStructuresAndAlgorithms.Node;

public class PrintExtremeNodesAlternate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree bst = new BinarySearchTree();
		bst.setShowSteps(false);
		Node root = bst.buildSampleBST(bst);
		
		bst.printInOrderBST(root);
		
		System.out.println("");
		System.out.println("");
		System.out.println("== PRINTING EXTREME NODES ==");
		PrintExtremeNodesAlternate pea = new PrintExtremeNodesAlternate();
		pea.printExtremeNodesInAlternateFashion(root);
	}
	
	public void printExtremeNodesInAlternateFashion(Node root)
	{		
		if (root == null)
			return;
		
		Queue<Node> q = new LinkedList<Node>();
		System.out.println(root.getData()); //printing root
		
		//adding left and right child of root
		if(root.getLeft() != null)
			q.add(root.getLeft());
		
		if(root.getRight() != null)
			q.add(root.getRight());
		
		boolean leftRight = true;
		while(!q.isEmpty())
		{
			Node temp;
			int countOfNodes = q.size();
			
			//print left extreme
			if (leftRight)
			{
				temp = q.remove();
				System.out.println(temp.getData()); //printing left extreme data
				
				for(int i=0; i<countOfNodes - 1 && !q.isEmpty(); i++)
				{
					temp = q.remove();
					
					if(temp.getLeft() != null)
						q.add(temp.getLeft());
					
					if(temp.getRight() != null)
						q.add(temp.getRight());
				}
				
			}//print right extreme
			else
			{	
				for(int i=0; i<countOfNodes - 1 && !q.isEmpty(); i++)
				{
					temp = q.remove();
					
					if(temp.getLeft() != null)
						q.add(temp.getLeft());
					
					if(temp.getRight() != null)
						q.add(temp.getRight());
				}
				
				temp = q.remove();
				System.out.println(temp.getData()); //printing right extreme data
			}
			
			leftRight = !leftRight;
		}
	}

}

/* OUTPUT
=== Constructing tree ===
4 5 7 10 11 12 15 18 20 25 

== PRINTING EXTREME NODES ==
10
5
20
12

*/