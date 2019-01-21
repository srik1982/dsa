package kth_smallest;

import bst.balanced.AVLNode;
import bst.balanced.AVLTree;

/**
 * Use a Balanced Binary Search Tree, for example AVL tree to insert first k elements in klogk.
 * After that, for each of (n-k) elements
 * 1. If its smaller than the largest element in tree, then remove largest in logk, add new one in log k
 * 2. So (n-k) log k operations. In the end we will have k smallest elements in AVL Tree, the right most node of the right subtree is the kth largest.
 * 
 * We are going to use an existing AVL Tree implementation for this. Writing an entire AVL tree class for this is time consuming and also this can be covered in the trees chapter.
 * @author srikanthrao
 *
 */
public class TreeSelect {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		int a[] = {99,91,81,2,6,3,1,44,31,23,65,89,87,42,56,19,25,77,75,68,55,49};
		int k = 7;
		
		for(int i=0;i<k;i++) {
			tree.root = tree.insert(tree.root, a[i]);
		}
		
		int max = tree.getMax();
		for(int i=k;i<a.length;i++) {
			if(a[i] < max) {
				tree.deleteNode(tree.root, max);
				tree.insert(tree.root, a[i]);
				max = tree.getMax();
			}
		}
		
		tree.preOrder(tree.root);
		// Some small problem when we do preorder traversal
		//But elements seems to be fine.
		// Need to revisit AVL Tree when we study about trees

	}

}
