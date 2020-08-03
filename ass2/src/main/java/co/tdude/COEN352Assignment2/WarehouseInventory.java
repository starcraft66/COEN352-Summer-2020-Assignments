package co.tdude.COEN352Assignment2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class WarehouseInventory {
    protected DLLDictionary<String,Inventory> invDict;

    public void populateDatabase() throws IOException {
        System.out.println("===== GLOWERS CLUB GARBAGE PARSER =====");
        this.invDict = new DLLDictionary<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("tf02930030.xltm");
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        DataFormatter dataFormatter = new DataFormatter();
        //This didn't end up working so I just hack-replaced the formulas
        //FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("Inventory List");

        invDict.clear();
        int skip = 0;
        for (Row row: sheet) {
            if (skip < 4) {
                // skip 4 rows of crap
                skip++;
                continue;
            }
            String SKU = dataFormatter.formatCellValue(row.getCell(1));
            Inventory item = new Inventory(
                    SKU,
                    dataFormatter.formatCellValue(row.getCell(2)),
                    dataFormatter.formatCellValue(row.getCell(3)),
                    dataFormatter.formatCellValue(row.getCell(4)),
                    dataFormatter.formatCellValue(row.getCell(5)),
                    Integer.parseInt(dataFormatter.formatCellValue(row.getCell(6))),
                    Integer.parseInt(dataFormatter.formatCellValue(row.getCell(7))),
                    Double.parseDouble(dataFormatter.formatCellValue(row.getCell(8)).substring(1)), // Strip the $
                    // POI returns NUMERIC when trying to evaluate the formula in this field and I don't feel like investigating
                    dataFormatter.formatCellValue(row.getCell(10)).equals("Reorder")
            );
            invDict.insert(SKU, item);
        }
    }

    public void clearDatabase() {
        this.invDict.clear();
    }

    public static void main(String[] args) throws IOException  {

        WarehouseInventory wi = new WarehouseInventory();
        wi.populateDatabase();


        try {
            System.out.println(Arrays.toString(wi.invDict.createDesendingIndex()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
