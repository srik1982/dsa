package stack;

public class LargestRectangleUnderHistogram {

	public static void main(String[] args) {
		System.out.println("Max area = "+getMaxAreaUnderHistogram(new int[] {6, 2, 5, 4, 5, 1, 6}));

	}
	
	/*
	 * O(n) since push and pop to stack happens only once.
	 */
	public static int getMaxAreaUnderHistogram(int[] hist) {
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		int i=0;
		while(i<hist.length) {
			if(stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
				stack.push(i++);
			}else {
				int index = stack.pop();
				int area = hist[index] * (stack.isEmpty() ? 1 : (i-stack.peek()-1));
				if(area>max) {
					max = area;
				}
			}
		}
		while(!stack.isEmpty()) {
			int index = stack.pop();
			int area = hist[index] * (stack.isEmpty() ? 1 : (i-stack.peek()-1));
			if(area>max) {
				max = area;
			}
		}
		return max;
	}

}
