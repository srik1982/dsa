package stack;

public class SortUsingStack {

	public static void main(String[] args) {
		SortUsingStack sorter = new SortUsingStack();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(15);
		stack.push(2);
		stack.push(7);
		stack.push(9);
		stack.push(5);
		stack.push(6);
		
		Stack<Integer> sorted = sorter.sort(stack);
		System.out.println(sorted.toString());
	}
	
	public Stack<Integer> sort(Stack<Integer> unsorted){
		Stack<Integer> sorted = new Stack<Integer>();
		while(!unsorted.isEmpty()) {
			int temp = unsorted.pop();
			while(!sorted.isEmpty() && sorted.peek() > temp) {
				unsorted.push(sorted.pop());
			}
			sorted.push(temp);
		}
		return sorted;
	}

}
