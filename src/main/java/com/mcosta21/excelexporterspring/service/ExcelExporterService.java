package com.mcosta21.excelexporterspring.service;

import com.mcosta21.excelexporterspring.model.ExportableField;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelExporterService {

    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Setter
    private int fontSizeHeader = 13;

    @Setter
    private int fontSizeBody = 12;

    public ExcelExporterService(){
        workbook = new XSSFWorkbook();
    }

    public void export(HttpServletResponse response, String filename, Object data) throws IOException, IllegalAccessException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + filename + ".xlsx";
        response.setHeader(headerKey, headerValue);

        var items = (ArrayList<Object>) data;
        Class<?> className = items.get(0).getClass();

        writeHeaderLine(className, filename);
        writeDataLines(className, items);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else {
            cell.setCellValue(value == null ? "" : value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(Class<?> className, List<Object> items) throws IllegalAccessException {

        List<Field> fields = getFields(className);

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(this.fontSizeBody);
        style.setFont(font);

        for (Object object : items) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            for(Field field : fields){
                createCell(row, columnCount++, field.get(object), style);
            }
        }
    }

    private void writeHeaderLine(Class<?> className, String filename) {
        sheet = workbook.createSheet(filename);

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(this.fontSizeHeader);
        style.setFont(font);

        List<Field> fields = getFields(className);

        for(var i = 0; i < fields.size(); i++){
            var field = fields.get(i);
            var annotation = field.getAnnotation(ExportableField.class);
            createCell(row, i, annotation == null || annotation.name().isBlank() ? field.getName() : annotation.name(),
                    style);
        }
    }

    private List<Field> getFields(Class<?> className) {
        List<Field> fields = new ArrayList<Field>();
        ReflectionUtils.doWithFields(className, field -> {
            field.setAccessible(true);
            var annotation = field.getAnnotation(ExportableField.class);
            if(annotation != null) {
                fields.add(field);
            }
        });
        return fields;
    }

}
