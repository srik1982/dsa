package coding.threading.legacy;

public class ProducerConsumerDriver {
	
	
	public static void main(String args[]) throws InterruptedException {
		SharedResource sr = new SharedResource();
		Producer p = new Producer(sr);
		Consumer c = new Consumer(sr);
		
		Thread tc = new Thread(c);
		Thread tp = new Thread(p);
		tc.start();
		tp.start();
//		tc.join();
//		tp.join();
//		System.out.println("Here 2");
	}
}
