package com.lulu.product.material;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Sample {
    public static void main(String[] args) {
        String accessToken = "xxxxxxxxxxxxxxxx"; //replace this with your smartsheet api access token

        Smartsheet smartsheet = SmartsheetFactory.createDefaultClient(accessToken);

        try {
            long sheetId = 2926085504558980L;
            Sheet sheet = smartsheet.sheetResources().getSheet(
                    sheetId,                // long sheetId
                    null,                   // EnumSet<SheetInclusion> includes
                    null,                   // EnumSet<ObjectExclusion> excludes
                    null,                   // Set<Long> rowIds
                    null,                   // Set<Integer> rowNumbers
                    null,                   // Set<Long> columnIds
                    null,                   // Integer pageSize
                    null                    // Integer page
            );


            //code to output the smartsheet data as csv
            smartsheet.sheetResources().getSheetAsCSV(sheetId, new FileOutputStream("output.csv"));


            //code to iterate through smartsheet data cell by cell
            for(Row materialRow : sheet.getRows()){
                for(Cell cell :materialRow.getCells()){
                    System.out.println(cell.getDisplayValue());
                }
            }
        } catch (SmartsheetException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
