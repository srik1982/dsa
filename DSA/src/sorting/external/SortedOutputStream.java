package sorting.external;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class SortedOutputStream {
	String fileName;
	int bufferSize;
	Queue<Integer> buffer = new LinkedList<>();
	
	SortedOutputStream(String fileName, int bufferSize1){
		this.fileName = fileName;
		bufferSize = bufferSize1;
	}
	
	public void write(int sortedVal) {
		buffer.offer(sortedVal);
		if(buffer.size()>bufferSize) {
			writeToFile();
		}
	}

	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(fileName),true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			for(int x : buffer) {
				bWriter.write(x);
			}
			bWriter.flush();
			bWriter.close();
//			System.out.println("Writing to output file >> "+ buffer.toString());
			buffer.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
}
