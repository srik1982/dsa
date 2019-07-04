package coding.performance;

public class DeadlockThread2 implements Runnable {
	Object lock1, lock2;
	public DeadlockThread2(Object lock1, Object lock2) {
		// TODO Auto-generated constructor stub
		this.lock1 = lock1;
		this.lock2 = lock2;
	}
	
	@Override
	public void run() {
		synchronized(lock2) {
			System.out.println("DT2 | Aquired Lock2");
			synchronized(lock1) {
				System.out.println("DT2 | Aquired Lock1");
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
