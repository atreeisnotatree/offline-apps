package SuiviNdC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateSuiviNDC {
	private static int counter=0;
	
	public static void writeFreshData(String outputFilePath, CSVReader reader) throws IOException {
		
		FileInputStream in = new FileInputStream(outputFilePath);

		XSSFWorkbook wb = new XSSFWorkbook(in);
	    XSSFSheet sheet = wb.getSheetAt(1);
		
	    for (int i=0; i<11;i++) {
	    	XSSFRow row = sheet.getRow(counter+1);
	    	//write LP
	    	XSSFCell cellLP = row.getCell(2);
	    	cellLP.setCellValue(reader.getLP());
	    	//write calibration Name
	    	XSSFCell cellCalibName = row.getCell(6);
	    	cellCalibName.setCellValue(reader.getcalibName());
//	    	//write score and COCA names
//	    	XSSFCell cellItem = row.getCell(3);
//	    	cellItem.setCellValue(reader.getScoreandCOCAValues().get(i)[0]);
	    	//write score and COCA values
	    	XSSFCell cellScoreAndCOCAValues = row.getCell(4);
	    	//convert String do a double for excel graphs to work
	    	double ScoreOrCoCAValue = Double.parseDouble(reader.getScoreandCOCAValues().get(i)[0]);
	    	cellScoreAndCOCAValues.setCellValue(ScoreOrCoCAValue);
	    	counter++;
	    	//write total labels number
	    	XSSFCell totalLabelsNumber = row.getCell(5);
	    	totalLabelsNumber.setCellValue(reader.getTotalLabelsNumber());
	    }
	    
	    
	    in.close();
	    
		FileOutputStream out = new FileOutputStream(outputFilePath);
		
		wb.write(out);
		wb.close();
		
		out.close();
	}
}
