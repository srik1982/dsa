package sorting.external;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInput {

	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter(new File("src/sorting/external/input.txt"));
			BufferedWriter bWriter = new BufferedWriter(writer);
			Random rand = new Random();
			for(int i=100;i>0;i--) {
				bWriter.write(i);
			}
			bWriter.flush();
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
