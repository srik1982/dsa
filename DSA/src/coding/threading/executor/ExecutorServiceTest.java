package coding.threading.executor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
	public static void main(String args[]) {
		//Executor interface
		JobRun job = new JobRun();
		Executor ex = Executors.newFixedThreadPool(1);
		ex.execute(job);
		System.out.println("Done");
		
		//ExecutorService interface and Future
		ExecutorService exs = Executors.newCachedThreadPool();
		List<Future<Integer>> fList = new ArrayList<Future<Integer>>();
		for(int i=0;i<10;i++) {
			Job j = new Job();
			Future<Integer> f = exs.submit((Callable<Integer>)j);
			fList.add(f);
		}
		
		int completedCount = 0;
		while(completedCount!=10) {
			Iterator<Future<Integer>> iter = fList.iterator();
			while(iter.hasNext()) {
				Future<Integer> f = iter.next();
				if(f.isDone()) {
					completedCount++;
					iter.remove();
				}
			}
		}
		System.out.println("All Jobs completed successfully");
		
	}
}
