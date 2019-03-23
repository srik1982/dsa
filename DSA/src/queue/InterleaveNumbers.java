 package queue;

public class InterleaveNumbers {

	public static void main(String[] args) {
		Queue<Integer> que = new Queue<Integer>();
		que.enqueue(1);
		que.enqueue(2);
		que.enqueue(3);
		que.enqueue(4);
		que.enqueue(5);
		que.enqueue(6);
		que.enqueue(7);
		que.enqueue(8);
		que.enqueue(9);
		que.enqueue(10);
		
		InterleaveNumbers obj = new InterleaveNumbers();
		System.out.println(obj.interleave(que));
	}
	
	public Queue<Integer> interleave(Queue<Integer> orig){
		Queue<Integer> swap = new Queue<>();
		int half = orig.size() / 2;
		
		for(int i=0;i<half;i++) {
			swap.enqueue(orig.dequeue());
		}
		for(int i=0;i<half;i++) {
			orig.enqueue(swap.dequeue());
			orig.enqueue(orig.dequeue());
		}
		return orig;
	}

}
