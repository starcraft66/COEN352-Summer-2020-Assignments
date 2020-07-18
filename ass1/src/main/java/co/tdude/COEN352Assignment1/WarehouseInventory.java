package co.tdude.COEN352Assignment1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class WarehouseInventory {
    public static void main(String[] args) throws IOException  {
        System.out.println("===== GLOWERS CLUB =====");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("tf02930030.xltm");
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        DataFormatter dataFormatter = new DataFormatter();
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("Inventory List");
        int skip = 0;
        for (Row row: sheet) {
            if (skip < 4) {
                // skip 4 rows of crap
                skip++;
                continue;
            }
            Inventory item = new Inventory(
                    dataFormatter.formatCellValue(row.getCell(0)),
                    dataFormatter.formatCellValue(row.getCell(1)),
                    dataFormatter.formatCellValue(row.getCell(2)),
                    dataFormatter.formatCellValue(row.getCell(3)),
                    dataFormatter.formatCellValue(row.getCell(4)),
                    Integer.parseInt(dataFormatter.formatCellValue(row.getCell(5))),
                    Integer.parseInt(dataFormatter.formatCellValue(row.getCell(6))),
                    Double.parseDouble(dataFormatter.formatCellValue(row.getCell(7))),
                    false
            );
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }
    }
}
