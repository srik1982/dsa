package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=AXjmTQ8LEoI
 * Trie.java in TusharPrograms/suffixprefix
 * @author srikanthrao
 *
 */
public class Trie {

	public static void main(String[] args) {
		Trie obj = new Trie();
		obj.insertIterative("srikanth".toCharArray());
		obj.insertRecursive("srinidhi".toCharArray());
		
		System.out.println("Searching for srinidhi : "+obj.searchIterative("srinidhi".toCharArray()));
		System.out.println("Searching for srinix : "+obj.searchPrefix("srinix".toCharArray()));
		
		obj.delete("srikanth".toCharArray());
		System.out.println("Searching for srikanth : "+obj.searchIterative("srikanth".toCharArray()));
		System.out.println("Searching for srinidhi : "+obj.searchRecursive("srinidhi".toCharArray()));
	}
	
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insertIterative(char[] text) {
		TrieNode current = root;
		for(int i=0;i<text.length;i++) {
			TrieNode node = current.children.get(text[i]);
			if(node == null) {
				node = new TrieNode();
				current.children.put(text[i], node);
				current.wordEnd = false;
			}
			current = node;
		}
		current.wordEnd=true;
	}
	
	public void insertRecursive(char[] text) {
		insertRecursive(text,0,root);
	}
	public void insertRecursive(char[] text, int index, TrieNode current) {
		if(index < text.length) {
			TrieNode node = current.children.get(text[index]);
			if(node == null) {
				node = new TrieNode();
				current.children.put(text[index], node);
				current.wordEnd = false;
			}
			insertRecursive(text,index+1,node);
		}else if(index == text.length) {
			current.wordEnd = true;
		}
	}
	
	public boolean searchPrefix(char[] text) {
		TrieNode current = root;
		boolean found = true;
		int i=0;
		for(;i<text.length;i++) {
			TrieNode node = current.children.get(text[i]);
			if(node == null) {
				found = false;
				break;
			}
			current = node;
		}
		if(found && i == text.length) {
			found = true;
		}
		return found;
	}
	
	public boolean searchIterative(char[] text) {
		TrieNode current = root;
		boolean found = true;
		int i=0;
		for(;i<text.length;i++) {
			TrieNode node = current.children.get(text[i]);
			if(node == null) {
				found = false;
				break;
			}
			current = node;
		}
		if(found && i == text.length) {
			if(current.wordEnd == false) {
				found = false;
			}
		}
		return found;
	}
	
	public boolean searchRecursive(char[] text) {
		return searchRecursive(text,0,root);
	}

	private boolean searchRecursive(char[] text, int index, TrieNode current) {
		if(index < text.length) {
			TrieNode node = current.children.get(text[index]);
			if(node == null) {
				return false;
			}
			return searchRecursive(text,index+1,node);
			
		}else if(index == text.length) {
			return current.wordEnd;
		}
		return false;
	}
	
	public void delete(char[] text) {
		delete(text,0,root);
	}

	private boolean delete(char[] text, int index, TrieNode current) {
		if(index < text.length) {
			TrieNode node = current.children.get(text[index]);
			if(node==null) {
				return false;
			}else {
				boolean deleteChild = delete(text,index+1,node);
				if(deleteChild) {
					current.children.remove(text[index]);
				}
				return current.children.size() == 0;
			}
		}else if(index == text.length) {
			return current.wordEnd || current.children.size()==0;
		}else {
			return false;
		}
	}

}

class TrieNode{
	Map<Character,TrieNode> children = new HashMap<>();
	boolean wordEnd = true;
}
