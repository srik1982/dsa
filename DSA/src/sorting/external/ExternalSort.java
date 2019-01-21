package sorting.external;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import sorting.QuickSort;

public class ExternalSort {

	public static void main(String[] args) {
		ExternalSort extSorter = new ExternalSort();
		extSorter.sort("src/sorting/external/input.txt");
	}
	
	class ExtSortNode implements Comparable{
		int num;
		SortedInputStream sortedStream;
		ExtSortNode(int num, SortedInputStream sortedStream){
			this.num = num;
			this.sortedStream = sortedStream;
		}
		@Override
		public int compareTo(Object o) {
			ExtSortNode node = (ExtSortNode) o;
			return ((Integer)num).compareTo(node.num);
		}
	}
	
	/**
	 * I have attempted to first sort and write 1000 entries to file.
	 * Later I am reading 100 from each of the 10 files => Total 1000.
	 * But there is an obvious flaw. I wrote numbers in input file from 10,000 to 1.
	 * So when I write to 10 output files numbered 0 to 9, each contains sorted numbers from
	 * (1,1000), (1001, 2000), (2001,3000) and so.. 
	 * Now taking 100 entries from each of them is stupid tthing to do.
	 * @param inputFile
	 */
	public void sort(String inputFile) {
		QuickSort qs = new QuickSort();
		
		for(int i=0;i<10;i++) {
			int a[] = read1000(i*10, inputFile);
			qs.sort(a);
			writePage(a, i);
		}

		List<SortedInputStream> sortedNumberStreams = new ArrayList<SortedInputStream>();
		for(int i=0;i<10;i++) {
			String file = new String("src/sorting/external/output"+i+".txt");
			sortedNumberStreams.add(new SortedInputStream(file,1,10));
		}
		
		//initialize pq;
		PriorityQueue<ExtSortNode> pq = new PriorityQueue<ExtSortNode>();
		for(SortedInputStream stream : sortedNumberStreams)
			pq.offer(new ExtSortNode(stream.next(),stream));
		
		SortedOutputStream sos = new SortedOutputStream("src/sorting/external/output.txt", 1);
		while(!pq.isEmpty()) {
			ExtSortNode node = pq.poll();
			sos.write(node.num);
			SortedInputStream sis = node.sortedStream;
			try {
				pq.offer(new ExtSortNode(sis.next(),sis));
			}catch(NullPointerException e) {
				//do nothing
			}
		}
		
	}
	
	public int[] read1000(int start, String file) {
		int []a  = new int[10];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int max = start + 10;
			for(int i=0;i<start;i++) {
				try {
					int num = reader.read();
					//read and ignore elements starting up to start.
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for(int i=start, j=0;i< max;i++) {
				try {
					int num = reader.read();
					a[j++] = num;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Read 1000 is returning size "+a.length);
//		System.out.println(Arrays.toString(a));
		return a;
	}
	
	public void writePage(int []a, int pageNum) {
//		System.out.println("Outputting to page num + "+pageNum);
//		System.out.println("Size of array = "+a.length);
//		System.out.println("Array : "+Arrays.toString(a));
		String outputFile = new String("src/sorting/external/output"+pageNum+".txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)); 
			for(int x : a) {
				writer.write(x);
				System.out.println();
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void writeOutput(int []a) {
//		try {
//			FileWriter writer = new FileWriter(new File("src/sorting/output.txt"));
//			BufferedWriter bWriter = new BufferedWriter(writer);
//			for(int x : a) {
//				bWriter.write(x);
//			}
//			bWriter.flush();
//			bWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
}
