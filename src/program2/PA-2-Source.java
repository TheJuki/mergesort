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

    // All 9 Unsorted Lists
    private static Integer[] list1;
    private static Integer[] list2;
    private static Integer[] list3;
    private static Integer[] list4;
    private static Integer[] list5;
    private static Integer[] list6;
    private static Integer[] list7;
    private static Integer[] list8;
    private static Integer[] list9;

    // All 9 Sorted Lists
    private static Integer[] sortedList1;
    private static Integer[] sortedList2;
    private static Integer[] sortedList3;
    private static Integer[] sortedList4;
    private static Integer[] sortedList5;
    private static Integer[] sortedList6;
    private static Integer[] sortedList7;
    private static Integer[] sortedList8;
    private static Integer[] sortedList9;

    // Main
    public static void main(String[] args) {

        // Fill all 9 Lists
        list1 = fillRandomInts(1);
        list2 = fillRandomInts(2);
        list3 = fillRandomInts(3);
        list4 = fillRandomInts(4);
        list5 = fillRandomInts(5);
        list6 = fillRandomInts(6);
        list7 = fillRandomInts(7);
        list8 = fillRandomInts(8);
        list9 = fillRandomInts(9);

        System.out.print("\nMergesort Algorithm\n");
        
        sortedList1 = Mergesort(list1.clone());

        displayList(list1);
        System.out.print("\nMergesort Algorithm\n");
        displayList(sortedList1);
       // System.out.print("\nMerged size: " + sortedList1.length +"\n");

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
}
