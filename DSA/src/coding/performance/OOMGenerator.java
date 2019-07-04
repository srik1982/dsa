package coding.performance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OOMGenerator {

	public static void main(String[] args) throws Exception {
		OOMGenerator memoryTest = new OOMGenerator();
		memoryTest.generateOOM();
	}
	
	BigDecimal bd = new BigDecimal(123987911231231112312371.1897399);
	
	public void generateOOM() throws Exception {
		List<BigDecimal[]> list = new ArrayList<BigDecimal[]>(100000); 
		while(true) {
			BigDecimal[] arr = new BigDecimal[1000];
			for(int i=0;i<1000;i++) {
				arr[i] = bd;
			}
			list.add(arr);
		}
	}
	
}
