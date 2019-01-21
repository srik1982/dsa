package sorting.external;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.PostConstruct;

public class SortedInputStream {
	boolean empty = false;
	Queue<Integer> sortedInput = new LinkedList<>();
	int start = 0;
	int fetchSize;
	int max;
	String fileName;
	boolean fetchNoMore = false;
	
	public SortedInputStream(String fileName, int fetchSize, int max) {
		this.fetchSize = fetchSize;
		this.fileName = fileName;
		this.max = max;
	}
	
	public int next() {
		if(sortedInput.isEmpty() && !fetchNoMore) {
			empty = true;
			fetchMore();
		}
		int retVal = sortedInput.poll();
		return retVal;
	}
	
	public int peek() {
		return sortedInput.peek();
	}
	
	@PostConstruct
	private void fetchMore() {
		if(start+fetchSize == max) {
			fetchNoMore = true;
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			for(int i=0;i<start;i++) {
				try {
					reader.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int max = start + fetchSize;
			for(int i=start;i< max;i++) {
				try {
					int num = reader.read();
					sortedInput.offer(num);
					empty = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			start = max;
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		return !fetchNoMore;
	}

}
