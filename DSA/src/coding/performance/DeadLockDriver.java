package coding.performance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockDriver {

	public static void main(String[] args) {
		
		String x = new String("Lock1");
		String y = new String("Lock2");
		
		DeadlockThread1 dt1 = new DeadlockThread1(x,y);
		DeadlockThread2 dt2 = new DeadlockThread2(x,y);
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.submit(dt1);
		es.submit(dt2);

	}

}
