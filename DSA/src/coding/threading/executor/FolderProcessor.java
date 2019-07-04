package coding.threading.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class FolderProcessor extends RecursiveTask<List<String>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folderName;
	
	FolderProcessor(String folderName){
		this.folderName = folderName;
	}

	@Override
	protected List<String> compute() {
		File file = new File(folderName);
		if(file.isDirectory()) {
			List<FolderProcessor> subDirs = new ArrayList<FolderProcessor>();
			List<String> files = new ArrayList<String>();
			if(file.listFiles() !=null) {
				for(File f : file.listFiles()) {
					if(f.isDirectory()) {
						FolderProcessor subTask = new FolderProcessor(f.getAbsolutePath());
						subTask.fork();
						subDirs.add(subTask);
					}else {
						files.add(f.getAbsolutePath());
					}
				}
				
				
				if(subDirs.size() > 0) {
					for(FolderProcessor fp : subDirs) {
						files.addAll(fp.join());
					}
				}
			}
			return files;
		}else {
			List<String> files = new ArrayList<String>();
			files.add(folderName);
			return files;
		}
	}

}
