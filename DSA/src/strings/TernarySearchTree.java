package strings;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=rs3Ypn7ullY  -- very good
 * com.interview.suffixprefix.TernaryTree.
 * @author srikanthrao
 *
 */
public class TernarySearchTree {

	public static void main(String[] args) {
		TernarySearchTree tst = new TernarySearchTree();
		tst.insert("srikanth ashwathanarayana rao".toCharArray());
		tst.insert("cat".toCharArray());
		tst.insert("catch".toCharArray());
		tst.insert("wasted".toCharArray());
		tst.insert("distasteful".toCharArray());
		tst.insert("xylophone".toCharArray());
		tst.insert("srinidhi".toCharArray());
		tst.insert("apple".toCharArray());
		tst.insert("application".toCharArray());
		tst.insert("basal".toCharArray());
		tst.insert("baseball".toCharArray());
		tst.insert("zebra".toCharArray());
		tst.insert("ember".toCharArray());
		tst.insert("eloquent".toCharArray());
		tst.insert("ferment".toCharArray());
		
//		System.out.println("root "+tst.root.data);
		tst.printByDfs(tst.root);
//		
//		System.out.println("Searching for cats : "+tst.search("cats".toCharArray()));
		
//		System.out.println("Longest String length = "+tst.longestString());
//		System.out.println("Prefix Searching for cat : "+tst.searchPrefix("cat".toCharArray()));
		
		tst.delete("cat".toCharArray());
		System.out.println("\n\n");
		tst.printByDfs(tst.root);
	}
	
	class Node{
		char data;
		Node left;
		Node right;
		Node eq;
		boolean wordEnd;
		
		public String toString() {
			return data+" , leaf = "+wordEnd;
		}
	}
	
	Node root = null;
	
	public void insert(char[] text) {
		root = insert(text,0,root);
	}

	/**
	 * Ok how do we write this code? little complicated, see the video.
	 * 1. The left and right are not really children, they are at equal level as the root node.
	 * 2. So, when you move left/right, you skip the current node by not incrementing index
	 * 3. If the left node/ right node/ eq node is null, then we create a new node and start the string from there
	 * 4. Otherwise we navigate till the point matching 'eq' nodes exist and then create a left/right node.
	 * 5. What about new string that extends the existing one? Example cat already exists, we want to add catfish. In this case 't' in cat is a 'leaf' and we extend by adding 'fish' as eq nodes
	 * so 'h' in catfish is a leaf.
	 * 6. Lets rename leaf as word end.
	 * 
	 * @param text
	 * @param index
	 * @param current
	 * @return
	 */
	private Node insert(char[] text, int index, Node current) {
		if(index == text.length) {
			return null;
		}
		
		if(current == null) {//1. Either root is null or 2. we are creating a new string and the node for the char doesn't exist
			current = new Node();
			current.data = text[index];
			current.eq = insert(text,index+1,null);
			current.wordEnd = current.eq == null;
		}else if(text[index] < current.data) {
			current.left = insert(text,index,current.left);
		}else if(text[index] > current.data) {
			current.right = insert(text,index,current.right);
		}else {
			current.eq = insert(text,index+1,current.eq);
		}
		return current;
	}
	
	public int longestString() {
		return lengthOf(root);
	}
	
	private int lengthOf(Node node) {
		if(node == null) {
			return 0;
		}
		return Math.max(lengthOf(node.left), Math.max(lengthOf(node.right), lengthOf(node.eq)+1));
	}
	
	public boolean search(char[] text) {
		return search(text,0,root,false);
	}
	
	public boolean searchPrefix(char[] text) {
		return search(text,0,root,true);
	}
	
	
	private boolean search(char[] text, int index, Node current, boolean prefixSearch) {
		if(text.length == index && current==null) {
			return true;
		}else if(current==null) {
			return false;
		}else if(text.length-1 == index && current.data == text[index]) {
			return prefixSearch? true : current.wordEnd;
		}
		
		if(current.data == text[index]) {
			return search(text,index+1,current.eq,prefixSearch);
		}else if(text[index] < current.data) {
			return search(text,index,current.left,prefixSearch);
		}else {
			return search(text,index,current.right,prefixSearch);
		}
	}
	
	/**
	 * There might be better print implementations.
	 * The idea is, left and right are parallel to eq. these 3 are 3 branches and not really to be treated as children
	 * When we go left or right, we don't want to add the current letter to the word.
	 * but before we go to the eq, we add the letter to the word, because the word is represented by the vertical eq branch.
	 * And since this is recursive, we remove the char once we returns from the printByDfs() method, because it might have some branch on the right
	 * and the right branch should not consider the current char
	 * 
	 * For example Srikanth and Srinidhi are two string which are added.
	 * 'n' is the right child of 'k' . so, when we return from printByDfs(node.eq); for srikanth we need to remove that last char 'k' and proceed to the right branch 'n'.
	 */
	Deque<Character> word = new LinkedList<>();
	public void printByDfs(Node node) {
		if(node == null) {
			return;
		}
		
		if(node.wordEnd) {
			word.offer(node.data);
			printQueue(word);
			word.pollLast();
		}
		
		printByDfs(node.left);
		
		word.offer(node.data);
		printByDfs(node.eq);
		word.pollLast();
		
		printByDfs(node.right);
	}

	private void printQueue(Queue<Character> word2) {
		Iterator<Character> iter = word2.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next());
		}
		System.out.println("");
	}
	
	public void delete(char[] text) {
		delete(text,0,root);
	}

	private Node delete(char[] text, int index, Node current) {
		if(current == null) {
			return null;
		}
		if(index != text.length -1) {
			if(text[index] == current.data) {
				current.eq = delete(text,index+1,current.eq);
			}else if(text[index] < current.data) {
				current.left = delete(text,index,current.left);
			}else {
				current.right = delete(text,index,current.right);
			}
		}
		
		if(isDeletable(current)) {
			return null;
		}else if(current.wordEnd){
			current.wordEnd = false;
			return current;
		}else {
			return current;
		}
	}
	
	private boolean isDeletable(Node node) {
		return node.left == null && node.right == null && node.eq == null;
	}
	

}
