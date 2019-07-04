package coding.performance;

import java.util.Timer;

public class DeadlockThread1 implements Runnable {
	Object lock1, lock2;
	public DeadlockThread1(Object lock1, Object lock2) {
		// TODO Auto-generated constructor stub
		this.lock1 = lock1;
		this.lock2 = lock2;
	}
	
	@Override
	public void run() {
		synchronized(lock1) {
			System.out.println("DT1 | Aquired Lock1");
			synchronized(lock2) {
				System.out.println("DT1 | Aquired Lock2");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
