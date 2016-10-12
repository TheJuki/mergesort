package program2;

import org.apache.poi.ddf.EscherColorRef;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Intercept;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

// PA-2-Group-Huang-Kirk-Parten

class Program2 {

    // Min array size
    private static final int MIN_ARRAY_SIZE = 1000;

    // The Merge Sort Table Row Class
    private static class MergeSortTableRow
    {
        // Array size (n)
        int n;

        // Unsorted list
        private Integer[] unsortedList;

        // Sorted List after a Mergesort
        private Integer[] sortedList;

        // Time spent on Mergesort
        private double timeSpent;

        public MergeSortTableRow() {}

        public MergeSortTableRow(Integer[] unsortedList) {
            this.unsortedList = unsortedList;

            if(unsortedList != null)
            {
                n = unsortedList.length;
            }
        }

        Object[] getRowAsArray()
        {
            return new Object[] {n, getNLogN(), timeSpent, getNLogNDividedByTime()};
        }

        // Value of n * log n
        public double getNLogN()
        {
            return n * Math.log(n);
        }

        // Value of (n * log n) / timeSpent
        public double getNLogNDividedByTime()
        {
            return (n * Math.log(n)) / timeSpent;
        }

        public Integer[] getUnsortedList() {
            return unsortedList;
        }

        public void setUnsortedList(Integer[] unsortedList) {
            this.unsortedList = unsortedList;
        }

        public Integer[] getSortedList() {
            return sortedList;
        }

        public void setSortedList(Integer[] sortedList) {
            this.sortedList = sortedList;
        }

        public double getTimeSpent() {
            return timeSpent;
        }

        public void setTimeSpent(double timeSpent) {
            this.timeSpent = timeSpent;
        }
    }

    // Main
    public static void main(String[] args) {

        List<MergeSortTableRow> mergeSortTableRows = new ArrayList<>();

        // Add 9 Merge Sort Table Rows and Fill all 9 Unsorted Lists
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(1)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(2)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(3)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(4)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(5)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(6)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(7)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(8)));
        mergeSortTableRows.add(new MergeSortTableRow(fillRandomInts(9)));

        // Sort and store each list
        mergeSortTableRows.get(0).setSortedList(Mergesort(mergeSortTableRows.get(0).getUnsortedList().clone()));
        mergeSortTableRows.get(1).setSortedList(Mergesort(mergeSortTableRows.get(1).getUnsortedList().clone()));
        mergeSortTableRows.get(2).setSortedList(Mergesort(mergeSortTableRows.get(2).getUnsortedList().clone()));
        mergeSortTableRows.get(3).setSortedList(Mergesort(mergeSortTableRows.get(3).getUnsortedList().clone()));
        mergeSortTableRows.get(4).setSortedList(Mergesort(mergeSortTableRows.get(4).getUnsortedList().clone()));
        mergeSortTableRows.get(5).setSortedList(Mergesort(mergeSortTableRows.get(5).getUnsortedList().clone()));
        mergeSortTableRows.get(6).setSortedList(Mergesort(mergeSortTableRows.get(6).getUnsortedList().clone()));
        mergeSortTableRows.get(7).setSortedList(Mergesort(mergeSortTableRows.get(7).getUnsortedList().clone()));
        mergeSortTableRows.get(8).setSortedList(Mergesort(mergeSortTableRows.get(8).getUnsortedList().clone()));

        // TODO GUI

        // TODO Excel

        displayList(mergeSortTableRows.get(0).getUnsortedList());
        displayList(mergeSortTableRows.get(0).getSortedList());

    }

    private static void displayList(Integer[] list)
    {
        /*
        for (int i : list)
        {
            System.out.print(i + "\n");
        }
        */

        System.out.println(Arrays.toString(list));

        JOptionPane.showMessageDialog(null, Arrays.toString(list),
                "PA-1",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static Integer[] fillRandomInts(int listNum)
    {
        Integer[] list = new Integer[MIN_ARRAY_SIZE * listNum];
                
        // Generate the random integers to add to the list
        for(int i = 0; i < MIN_ARRAY_SIZE * listNum; i++)
        {
            list[i] = ((int)(Math.random()*1000 + 1));
        }
        
        return list;
    }

    // Mergesort method
    private static Integer[] Mergesort(Integer[] list)
    {
        // Step 0 (the base case)
        if(list.length <= 1)
            return list;

        // Step 1 - Split list to two lists having n/2 items
        Integer[] list1 = Arrays.copyOfRange(list, 0, list.length / 2);
        Integer[] list2 = Arrays.copyOfRange(list, list.length / 2, list.length);

        // Step 2-1 (recursive call)
        Mergesort(list1);

        // Step 2-2 (recursive call)
        Mergesort(list2);

        // Step 3 (final output)
        return Merge(list1, list2, list);
    }

    // Merge two sorted lists
    private static Integer[] Merge(Integer[] list1, Integer[] list2, Integer[] mergedList)
    {

        int mergedListIndex = 0;
        int list1Index = 0;
        int list2Index = 0;

        while (list1Index < list1.length &&
                list2Index < list2.length)
        {
            if(list1[list1Index].compareTo(list2[list2Index]) < 0)
            {
                mergedList[mergedListIndex] = list1[list1Index];
                list1Index = list1Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
            else
            {
                mergedList[mergedListIndex] = list2[list2Index];
                list2Index = list2Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
        }

        // Copy any remaining items
        if(list1Index != list1.length)
        {
            while(mergedListIndex < mergedList.length && list1Index < list1.length)
            {
                mergedList[mergedListIndex] = list1[list1Index];
                list1Index = list1Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
        }
        else if(list2Index != list2.length)
        {
            while(mergedListIndex < mergedList.length && list2Index < list2.length)
            {
                mergedList[mergedListIndex] =  list2[list2Index];
                list2Index = list2Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
        }

        return mergedList;
    }

    // Writes the Excel file
    private static void writeExcelFile(List<MergeSortTableRow> mergeSortTableRows)
    {
        // Create new Excel workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // Create sheets
        HSSFSheet mergeSortSheet = workbook.createSheet("Merge_Sort_Results");

        // Cell style - Adds a thin border around each cell
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        Map<String, Object[]> data = new HashMap<String, Object[]>();

        // Set header rows for results sheets
        addResultsHeaderRow(style, mergeSortSheet);

        // Gather rows for Original_Euclid_Results
        for(int i = 0; i < mergeSortTableRows.size(); i++) {
            data.put(String.valueOf(i + 1), mergeSortTableRows.get(i).getRowAsArray());
        }

        // Create cells for Original_Euclid_Results
        createCells(data, style, mergeSortSheet);

        try {
            // Write Excel file
            FileOutputStream out =
                    new FileOutputStream(new File("Mergesort_Time.xls"));
            workbook.write(out);
            out.close();
            System.out.println("\n\nMergesort_Time.xls document created successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Merge Sorts finished; however, Mergesort_Time.xls creation failed: \n" + e.getMessage(),
                    "PA-1",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Create the header row for a Results sheet
    private static void addResultsHeaderRow(HSSFCellStyle style, HSSFSheet sheet)
    {
        //Set Column Widths
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellStyle(style);
        headerCell.setCellValue("Input size n for Array_i");
        headerCell = headerRow.createCell(1);
        headerCell.setCellStyle(style);
        headerCell.setCellValue("Value of n· logn");
        headerCell = headerRow.createCell(2);
        headerCell.setCellStyle(style);
        headerCell.setCellValue("Time Spent (Milliseconds)");
        headerCell = headerRow.createCell(3);
        headerCell.setCellStyle(style);
        headerCell.setCellValue("Value of (n· logn)/time");
    }

    // Create each row and cell of a data map
    private static void createCells(Map<String, Object[]> data, HSSFCellStyle style, HSSFSheet sheet)
    {
        Set<String> keyset = data.keySet();
        int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellStyle(style);
                if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
    }

}
