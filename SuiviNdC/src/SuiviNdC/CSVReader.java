package SuiviNdC;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {
	String filePath;
	private String LP;
	private String calibName;
	ArrayList<String[]> scoreandCOCAVAlues = new ArrayList<>();
	public double totalLabelsNumber;
	
	public CSVReader(String inputFilePath) {
		filePath = inputFilePath;
		String splitBy = "\\\\";
		String[] pathComponents = inputFilePath.split(splitBy);

		String fileName = pathComponents[pathComponents.length-1];
		LP = fileName.substring(0, 4);

		calibName = fileName.substring(5, fileName.length()-4);

		
	}

	public String getLP() {
		
		return LP;
	}
	
	public String getcalibName() {
		return calibName;
	}

	public double getTotalLabelsNumber(){
		BufferedReader br = null;
        String line;
        ArrayList<String> allLines = new ArrayList<>();
        String cvsSplitBy = ";";
        int rowNumber = 2;
        
        try { 

            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
            	
  
                String[] scoreValue = line.split(cvsSplitBy);
                //select just first 2 columns from each row
                String value = scoreValue[1];
                allLines.add(value);

            }
            totalLabelsNumber = Double.parseDouble(allLines.get(rowNumber));

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
        
		return totalLabelsNumber;
		
	}
	public ArrayList<String[]> getScoreandCOCAValues(){
		BufferedReader br = null;
        String line;
        ArrayList<String[]> allLines = new ArrayList<>();
        String cvsSplitBy = ";";
        int[] scoreAndCOCAlines = {14,15,16,17,18,19,8,9,10,11,12};
		
        try { 

            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
            	
  
                String[] scoreValue = line.split(cvsSplitBy);
                //select just first 2 columns from the row
                String[] scoreValueFinal = Arrays.copyOfRange(scoreValue, 1, 2);
                allLines.add(scoreValueFinal);

            }

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
        
        for (int item:scoreAndCOCAlines) {
        	scoreandCOCAVAlues.add(allLines.get(item));
		
	
        }
		return scoreandCOCAVAlues;
        
	}
}
