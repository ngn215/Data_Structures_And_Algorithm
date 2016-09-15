//
//** Exercise : Print nodes at k distance from root **
//

package Exercises;

import DataStructuresAndAlgorithms.BinarySearchTree;
import DataStructuresAndAlgorithms.Node;

public class PrintNodesAtDistanceK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree bst = new BinarySearchTree();
		bst.setShowSteps(false);
		Node root = bst.buildSampleBST(bst);
		
		bst.printInOrderBST(root);
		
		System.out.println("");
		System.out.println("");
		System.out.println("== PRINTING NODES AT DISTANCE 2 ==");
		PrintNodesAtDistanceK pnk = new PrintNodesAtDistanceK();
		pnk.printNodesAtDistanceKFromRoot(root, 2);
		
		System.out.println("");
		System.out.println("");
		System.out.println("== PRINTING NODES AT DISTANCE 3 ==");
		pnk.printNodesAtDistanceKFromRoot(root, 3);
		
	}
	
	public void printNodesAtDistanceKFromRoot(Node root, int distance)
	{
		if (root == null)
			return;
		
		if(distance == 0)
			System.out.print(root.getData() + " ");
		
		printNodesAtDistanceKFromRoot(root.getLeft(), distance-1);
		printNodesAtDistanceKFromRoot(root.getRight(), distance-1);
			
	}

}

/* OUTPUT
=== Constructing tree ===
4 5 7 10 11 12 15 18 20 25 

== PRINTING NODES AT DISTANCE 2 ==
4 7 11 20 

== PRINTING NODES AT DISTANCE 3 ==
12 18 25 
*/
