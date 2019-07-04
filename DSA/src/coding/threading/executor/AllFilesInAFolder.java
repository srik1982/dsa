package coding.threading.executor;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class AllFilesInAFolder {

	public static void main(String[] args) {
		FolderProcessor fp = new FolderProcessor("D:\\eclipse-jee\\eclipse\\configuration");
		ForkJoinPool threadPool = new ForkJoinPool();
		threadPool.execute(fp);
		threadPool.shutdown();
		List<String> files = fp.join();
		files.forEach(file -> System.out.println(file));
	}

}
