package coding.threading.executor;

import java.util.concurrent.Callable;

public class Job implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int rand =  (int) Math.ceil(Math.random() * 1000);
		try {			
			System.out.format("1. Sleeping for %d milliseconds\n", rand);
			Thread.sleep(rand);
			System.out.println("1. Woken up from sleep");
		}catch(InterruptedException e) {
			
		}
		return (Integer)rand;
	}

}
