package common_util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
    private XSSFWorkbook workBook;
    private XSSFSheet sheet;

    public ExcelUtils(String filePath, String sheetName){
        try {
            workBook = new XSSFWorkbook(filePath);
            sheet = workBook.getSheet(sheetName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object getCellData(int rowNum, int colNum){
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
    }

    public int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }

    public int getRowCellCount(int rowNum){
       return sheet.getRow(rowNum).getPhysicalNumberOfCells();
    }




}
