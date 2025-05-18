package co.edu.unbosque.util;

import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelExporter {

    // Escribe los datos de un JTable a un archivo Excel (.xls), añadiendo al final si el archivo ya existe.
    public static void writeDataToExcel(String filePath, JTable table) {
        if (!filePath.toLowerCase().endsWith(".xls")) {
            System.err.println("Error: La extensión del archivo debe ser .xls");
            return;
        }

        File file = new File(filePath);
        WritableWorkbook workbook = null;
        Workbook existingWorkbook = null;
        try {
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("UTF-8");

            if (file.exists()) {
                existingWorkbook = Workbook.getWorkbook(file);
                workbook = Workbook.createWorkbook(file, existingWorkbook, wbSettings);
            } else {
                workbook = Workbook.createWorkbook(file, wbSettings);
            }

            WritableSheet sheet = null;
            if (file.exists() && existingWorkbook.getNumberOfSheets() > 0) {
                sheet = workbook.getSheet(0);
            } else {
                sheet = workbook.createSheet("Hoja 1", 0);
                TableModel model = table.getModel();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Label label = new Label(i, 0, model.getColumnName(i));
                    sheet.addCell(label);
                }
            }

            TableModel model = table.getModel();
            int startRow = sheet.getRows();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    String cellValue = (value != null) ? value.toString() : "";
                    Label label = new Label(j, startRow + i, cellValue);
                    sheet.addCell(label);
                }
            }

            workbook.write();
            System.out.println("Archivo Excel actualizado en: " + filePath);

        } catch (IOException e) {
            System.err.println("Error de IO: " + e.getMessage());
            e.printStackTrace();
        } catch (WriteException e) {
            System.err.println("Error de escritura: " + e.getMessage());
            e.printStackTrace();
        } catch (BiffException e) {
            System.err.println("Error de Biff (problema al leer el archivo existente): " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (existingWorkbook != null) {
                    existingWorkbook.close();
                }
            } catch (IOException | WriteException e) {
                System.err.println("Error al cerrar el libro de trabajo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}