package coding.threading.executor;

public class JobRun implements Runnable {

	@Override
	public void run() {
		int rand = (int) Math.ceil(Math.random() * 1000);
		try {			
			System.out.format("2. Sleeping for %d milliseconds", rand);
			Thread.sleep(rand);
			System.out.println("2. Woken up from sleep");
		}catch(InterruptedException e) {
			
		}
	}

}
