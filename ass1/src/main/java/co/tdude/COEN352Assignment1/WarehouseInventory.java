package co.tdude.COEN352Assignment1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class WarehouseInventory {
    public static void main(String[] args) throws IOException  {
        System.out.println("===== GLOWERS CLUB GARBAGE PARSER =====");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("tf02930030.xltm");
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        DataFormatter dataFormatter = new DataFormatter();
        //This didn't end up working so I just hack-replaced the formulas
        //FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("Inventory List");

        DLLDictionary<String,Inventory> invDict = new DLLDictionary<>();
        invDict.clear();
        String randomSku = "YE98767";
        String randomSku2 = "";
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
            if (row.getRowNum() == 9) {
                randomSku2 = SKU;
            }
            invDict.insert(SKU, item);
        }
        try {
            System.out.println(invDict.size());

            invDict.remove(randomSku);
            System.out.println(invDict.size());

            invDict.find(randomSku2);

            System.out.println(invDict.find(randomSku));
            double costTotal = 0D;

            while (invDict.size()>0) {
                Inventory e = invDict.removeAny();
                costTotal += e.getInventoryValue();
                System.out.println(e.getSku());
                if (e.isReorder()) {
                    System.out.println(e.getSku() + " is Reorder");
                }
            }

            System.out.println("Total cost: $" + costTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
