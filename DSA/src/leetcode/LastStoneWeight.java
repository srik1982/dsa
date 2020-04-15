package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * We have a collection of stones, each stone has a positive integer weight.

Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

    If x == y, both stones are totally destroyed;
    If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.

At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 */
public class LastStoneWeight {

	/*
	 * O(n2logn)
	 */
	public int solution1(int[] stones) {
        if(stones.length>=1){
            int startIndex = 0, endIndex = stones.length-1;
            while(endIndex > startIndex){//n
                Arrays.sort(stones,startIndex,endIndex+1); //nlogn    
                int w1 = stones[endIndex];
                int w2 = stones[endIndex-1];
                int diff = Math.abs(w1-w2);
                stones[endIndex-1] = diff;
                endIndex--;
            }   
            return stones[startIndex];
            
        }else if(stones.length == 1){
            return stones[0];
        }
        else{
            return 0;
        }        
    }
	
	/**
	 * n log n -- in reality this takes more time.
	 * @param stones
	 * @return
	 */
	public int solution2(int[] stones) {
		if(stones.length>=1){
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			for(int stone : stones) { //n logn
				pq.add(stone); //logn
			}
			while(pq.size()>1) { //nlogn
				int w1 = pq.poll();//logn
				int w2 = pq.poll();//logn
				pq.add(Math.abs(w1-w2));//logn
			}
			return pq.poll();
            
        }else if(stones.length == 1){
            return stones[0];
        }
        else{
            return 0;
        }
		
	}
	
	public static void main(String args[]) {
		LastStoneWeight obj = new LastStoneWeight();
		int[] stones = new int[] {2,7,4,1,8,1,8,4,6,7,12,3,5,6,9,8,12,13,65,14,19,23,24,56,2,88,61,28,36,75,86,83};
		long t1 = System.nanoTime();
		int s1 = obj.solution1(stones);
		long t2 = System.nanoTime();
		System.out.println(s1);
		System.out.println("Time : "+(t2-t1));
		
		t1 = System.nanoTime();
		int s2 = obj.solution2(stones);
		t2 = System.nanoTime();
		System.out.println(s2);
		System.out.println("Time : "+(t2-t1));
		

	}
}
