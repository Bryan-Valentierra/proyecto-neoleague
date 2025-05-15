package co.edu.unbosque.util;

import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelExporterJExcel {

    public static void writeDataToExcel(String filePath, JTable table) {
        File file = new File(filePath);
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Hoja 1", 0);
            TableModel model = table.getModel();

            // Escribir los encabezados de las columnas
            for (int i = 0; i < model.getColumnCount(); i++) {
                Label label = new Label(i, 0, model.getColumnName(i));
                sheet.addCell(label);
            }

            // Escribir los datos de la tabla
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    String cellValue = (value != null) ? value.toString() : ""; // Manejar valores nulos
                    Label label = new Label(j, i + 1, cellValue); // j es la columna, i+1 es la fila (los encabezados ocupan la fila 0)
                    sheet.addCell(label);
                }
            }
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
