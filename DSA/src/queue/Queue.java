package queue;

public class Queue<E> {

	public static void main(String[] args) {
		Queue<Integer> que = new Queue<Integer>();
		que.enqueue(4);
		que.enqueue(23);
		que.enqueue(8);
		que.enqueue(12);
		que.enqueue(56);
		que.enqueue(98);
		System.out.println(que);
		System.out.println("removing :"+que.dequeue());
		System.out.println("removing :"+que.dequeue());
		System.out.println("removing :"+que.dequeue());
		que.enqueue(67);
		que.enqueue(76);
		que.enqueue(81);
		que.enqueue(89);
		que.enqueue(51);
		que.enqueue(93);
		System.out.println(que);
		que.enqueue(46);
		que.enqueue(33);
	}
	
	private final int size = 10;
	private Object[] q = new Object[size];
	private int front=-1;
	private int rear=-1;
	
	public boolean isEmpty() {
		return front == -1 && rear==-1;
	}
	
	public boolean isFull() {
		return rear-front==size-1 || rear +1 == front; 
	}
	
	public void enqueue(E e) {
		if(isFull()) {
			throw new IllegalStateException("Queue is full");
		}
		rear = (rear+1) % size;
		q[rear]=e;
		if(front==-1)front=0;
	}
	
	@SuppressWarnings("unchecked")
	public E dequeue() {
		if(isEmpty()) {
			throw new IllegalStateException("Queue is Empty");
		}
		return (E) q[front++];
	}
	
	@SuppressWarnings("unchecked")
	public E front() {
		return (E)q[front];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		if(rear>front) {
			for(int i=front;i<=rear;i++) {
				sb.append(q[i]).append(" , " );
			}
		}
		else {
			for(int i=front;i<size;i++) {
				sb.append(q[i]).append(" , " );
			}
			for(int i=0;i<=rear;i++) {
				sb.append(q[i]).append(" , " );
			}
		}
		sb.append("]");
		return sb.toString();		
	}
	
	public int size() {
		int _size = (rear > front) ? (rear - front + 1) : (size - (front-rear));  
		return _size;
	}

}
