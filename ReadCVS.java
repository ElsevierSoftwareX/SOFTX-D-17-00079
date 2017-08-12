

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ReadCVS {

  public static void main(String[] args) {

	ReadCVS obj = new ReadCVS();
	obj.run();

  }

  public void run() {

	String csvCodes = "codes.csv";
	String csvNames = "names.csv";

	BufferedReader br = null;
	String line = "";
	List<String> codes = new ArrayList<>();
	List<String> names = new ArrayList<>();

	try {

		//output
		PrintWriter writer = new PrintWriter("output.csv", "UTF-8");

		
		//parse
		br = new BufferedReader(new FileReader(csvNames));
		while ((line = br.readLine()) != null) {
			names.add(line);			
		}
		br = new BufferedReader(new FileReader(csvCodes));

		while ((line = br.readLine()) != null) {
			codes.add(line);			
		}

		//search
		for (String name: names){
			String orignalName = new String(name);
			for (String code: codes){
				int c = StringUtils.countMatches(name,code);
				if (c > 0) 
					writer.println(code + "|" + orignalName);
			}
		}
		writer.close();



	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}