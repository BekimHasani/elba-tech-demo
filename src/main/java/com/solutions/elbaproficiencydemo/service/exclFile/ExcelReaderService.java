package com.solutions.elbaproficiencydemo.service.exclFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {
    private FileInputStream file;
    private XSSFWorkbook workbook;

    {
        file = readExcelFile();
    }

    private FileInputStream readExcelFile() {
        final FileInputStream file;
        try {
            file = new FileInputStream("/home/bekehasani/Desktop/employees.xlsx");
            workbook = new XSSFWorkbook(file);
            workbook.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public List<List<String>> xlsxFileReader(boolean reloadFile) {
        if (reloadFile) {
            readExcelFile();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<List<String>> rowValues = new ArrayList<>();

        for (int rowIndex = sheet.getFirstRowNum() + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

            Row row = sheet.getRow(rowIndex);
            List<String> cellValues = new ArrayList<>();

            for (int colIndex = row.getFirstCellNum(); colIndex < row.getLastCellNum(); colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING -> cellValues.add(cell.getStringCellValue());
                        case NUMERIC -> cellValues.add(new DataFormatter().formatCellValue(cell));
                    }
                } else {
                    cellValues.add(null);
                }
            }
            rowValues.add(cellValues);
        }
        return rowValues;
    }
}