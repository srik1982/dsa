package greedy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanEncoding {
	public static void main(String args[]) {
		String text = "itz a lovely day today. Huffman coding algorithm which is an example for greedy algorithm is just what i wanted to move on from being totally stuck."
				+ "//Use string instead of string buffer. because string buffer holds the value. String on the other hand is immutable\n" + 
				"	//So when you go back to the previous frame, you have the old value ( stripped off the appended value)";
		HuffmanEncoding obj = new HuffmanEncoding();
		Map<Character,String> codesMap = obj.encode(text);
		obj.decode(codesMap.values(), obj.root);
	}
	PQNode root = null;
	class PQNode implements Comparable{
		Integer frequency;
		char data;
		PQNode left,right;
		
		public PQNode(Integer frequency, char data){
			this.frequency = frequency;
			this.data = data;
		}
		
		@Override
		public int compareTo(Object o) {
			PQNode other = (PQNode) o;
			return frequency.compareTo(other.frequency);
		}
		
		public boolean isLeaf() {
			return left==null && right==null;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
	}

	public Map<Character,String> encode(String text) {
		Map<Character,Integer> frequency = new HashMap<>();
		char [] charArray = text.toCharArray();
		for(char c : charArray) {
			if(frequency.containsKey(c)) {
				frequency.put(c, frequency.get(c)+1);
			}else {
				frequency.put(c, 1);
			}
		}
		
		PriorityQueue<PQNode> pq = new PriorityQueue<>();
		for(Map.Entry<Character, Integer> entry : frequency.entrySet()) {
			pq.offer(new PQNode(entry.getValue(),entry.getKey()));
		}
		
		root = constructTree(pq);
		Map<Character, String> code = new HashMap<>();
		findCodes(root,code);
		
		int ct = 0;
		for(Map.Entry<Character, String> entry : code.entrySet()) {
			System.out.println(entry.getKey() +" : "+entry.getValue());
			ct += entry.getValue().length();
		}
		System.out.println("encoded bits size = "+ct);
		System.out.println("Bits saved = "+(root.frequency * 8 - ct));
		
		return code;
	}
	
	private void findCodes(PQNode root, Map<Character, String> code) {
		findCodes(null,root,code,"");
	}
	
	//Use string instead of string buffer. because string buffer holds the value. String on the other hand is immutable
	//So when you go back to the previous frame, you have the old value ( stripped off the appended value)
	private void findCodes(PQNode parent, PQNode current, Map<Character, String> code, String str) {
		if(current.isLeaf()) {
			if(parent!=null && parent.left == current) {
				str = str+"0";
			}else {
				str = str+"1";
			}
			code.put(current.data, str);
		}else {
			if(parent!=null && parent.left == current) {
				str = str+"0";
			}else if(parent!=null && parent.right == current){
				str = str+"1";
			}
			findCodes(current,current.left,code,str);
			findCodes(current,current.right,code,str);
		}
		
	}

	private PQNode constructTree(PriorityQueue<PQNode> pq) { 
		while(pq.size()>1) {
			PQNode left = pq.poll();
			PQNode right = pq.poll();
			
			PQNode sumNode = new PQNode(left.frequency+right.frequency, (char)0);
			sumNode.left = left;
			sumNode.right = right;
			pq.offer(sumNode);
		}
		
		return pq.poll();
	}
	
	public void decode(Collection<String> codes, PQNode root) {
		for(String code : codes) {
			PQNode current = root;
			for(int i=0;i<code.length();i++) {
				if(code.charAt(i) == '0') {
					current = current.left;
				}else {
					current = current.right;
				}
			}
			System.out.println(code +" : "+current.data);
		}
	}
}
