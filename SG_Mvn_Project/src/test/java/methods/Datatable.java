package methods;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import driver.DriverScript;

public class Datatable extends DriverScript{
	/************************************************************
	 * Method Name		: readDataFromExcel
	 * purpose			: to read the values from the excel file
	 * Parameters		: String strFilePath, String sheetName
	 * Return Type		: Map<String, String>
	 **************************************************************/
	public Map<String, String> readDataFromExcel(String strFilePath, String sheetName, String logicalName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		Map<String, String> objData = null;
		int rowNum = 0;
		String strKey = null;
		String strValue = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			objData = new HashMap<String, String>();
			fin = new FileInputStream(strFilePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "The given sheet '"+sheetName+"' doesnot exist");
				return null;
			}
			
			
			//Find the rowNum for the given logical name
			int rows = sh.getPhysicalNumberOfRows();
			for(int r=0; r<rows; r++)
			{
				row1 = sh.getRow(r);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().equalsIgnoreCase(logicalName)) {
					rowNum = r;
					break;
				}
			}
			
			
			if(rowNum > 0) {
				row1 = sh.getRow(0);
				row2 = sh.getRow(rowNum);
				
				for(int c=0; c<row1.getPhysicalNumberOfCells(); c++) {
					cell1 = row1.getCell(c);
					strKey = cell1.getStringCellValue();
					
					cell2 = row2.getCell(c);
					
					if(cell2==null || cell2.getCellType()==CellType.BLANK) {
						strValue = "";
					}else if(cell2.getCellType()==CellType.BOOLEAN) {
						strValue = String.valueOf(cell2.getBooleanCellValue());
					}else if(cell2.getCellType()==CellType.STRING) {
						strValue = cell2.getStringCellValue();
					}else if(cell2.getCellType()==CellType.NUMERIC) {
						if(DateUtil.isCellDateFormatted(cell2)) {
							double dt = cell2.getNumericCellValue();
							Calendar cal = Calendar.getInstance();
							cal.setTime(DateUtil.getJavaDate(dt));
							
							//If day is < 10, then prefix with Zero
							if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
								sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
							}else {
								sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							
							//If month is < 10, then prefix with Zero
							if((cal.get(Calendar.MONTH)+1) < 10) {
								sMonth = "0" + (cal.get(Calendar.MONTH)+1);
							}else {
								sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							
							sYear = String.valueOf(cal.get(Calendar.YEAR));
							
							strValue = sDay + "/" + sMonth + "/" + sYear;
						}else {
							strValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					objData.put(strKey, strValue);
				}
				
				return objData;
			}else {
				reports.writeResult(null, "Fail", "Failed to find the given logical name '"+logicalName+"'");
				return null;
			}
			
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception in 'readDataFromExcel()' method. "+ e);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception in 'readDataFromExcel()' method. "+ e);
				return null;
			}
		}
	}
}
