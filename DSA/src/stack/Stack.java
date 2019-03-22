package stack;

import java.util.ArrayList;

public class Stack<E extends Object> {
	private final int size = 10;
	private Object[] stack = new Object[size];
	private int top = -1;
	
	public void push(E element) throws IllegalStateException {
		if(top == size-1) {
			throw new IllegalStateException("Stack Full");
		}
		stack[++top] = element;
	}
	
	public E pop() {
		if(top == -1) {
			throw new IllegalStateException("Stack Empty");
		}
		return (E)stack[top--];
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == size-1;
	}
	
	public E peek() {
		if(top == -1) {
			throw new IllegalStateException("Stack Empty");
		}
		return (E)stack[top]; 
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for(int i=0;i<=top;i++) {
			sb.append(stack[i].toString()).append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
