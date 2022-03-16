package automation.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

public class DataHelper {

    public static Map<Integer, List<String>> readFile(String path){
        Map<Integer, List<String>> data = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            // getting first sheet
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(i).add(cell.getRichStringCellValue().getString());
                            break;
                        case BOOLEAN:
                            data.get(i).add(cell.getBooleanCellValue() + "");
                            break;
                        default:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i).add(cell.getNumericCellValue() + "");
                            }
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static Object[][] formatData(Map<Integer, List<String >> infoData ){
        Object[][] d = new Object[infoData.values().size()-1][2];
        int i, j = 0;
        for(i = 0; i<infoData.values().size()-1; i++){
            for( String s : infoData.get(i+1)){
                d[i][j] = s;
                j++;
            }
            j=0;
        }
        return d;
    }
}
