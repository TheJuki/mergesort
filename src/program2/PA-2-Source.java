package program2;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

// PA-2-Group-Huang-Kirk-Parten

class Program2 {

    // Min array size
    private static final int MIN_ARRAY_SIZE = 1000;

    // All 9 Unsorted Lists
    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = new ArrayList<>();
    private static List<Integer> list5 = new ArrayList<>();
    private static List<Integer> list6 = new ArrayList<>();
    private static List<Integer> list7 = new ArrayList<>();
    private static List<Integer> list8 = new ArrayList<>();
    private static List<Integer> list9 = new ArrayList<>();

    // All 9 Sorted Lists
    private static List<Integer> sortedList1 = new ArrayList<>();
    private static List<Integer> sortedList2 = new ArrayList<>();
    private static List<Integer> sortedList3 = new ArrayList<>();
    private static List<Integer> sortedList4 = new ArrayList<>();
    private static List<Integer> sortedList5 = new ArrayList<>();
    private static List<Integer> sortedList6 = new ArrayList<>();
    private static List<Integer> sortedList7 = new ArrayList<>();
    private static List<Integer> sortedList8 = new ArrayList<>();
    private static List<Integer> sortedList9 = new ArrayList<>();

    // Main
    public static void main(String[] args) {

        // Fill all 9 Lists
        fillRandomInts(list1, 1);
        fillRandomInts(list2, 2);
        fillRandomInts(list3, 3);
        fillRandomInts(list4, 4);
        fillRandomInts(list5, 5);
        fillRandomInts(list6, 6);
        fillRandomInts(list7, 7);
        fillRandomInts(list8, 8);
        fillRandomInts(list9, 9);

        System.out.print("\nMergesort Algorithm\n");


        Mergesort(list1);

    }

    private static void fillRandomInts(List<Integer> list, int listNum)
    {
        // Generate the random integers to add to the list
        for(int i = 0; i < MIN_ARRAY_SIZE * listNum; i++)
        {
            list.add((int)(Math.random()*1000 + 1));
        }
    }

    // Mergesort method
    private static List<Integer> Mergesort(List<Integer> list)
    {

        // If list is null or empty then mergesort cannot continue
        if(list == null || list.size() == 1)
            return null;

        // Step 0 (the base case)
        if(list.size() == 1)
            return list;

        // Step 1 - Split list to two lists having n/2 items
        List<Integer> list1 = list.subList(0, ((list.size() / 2) ));
        List<Integer> list2 = list.subList(list.size() / 2, (list.size()));

        // Step 2-1 (recursive call)
        Mergesort(list1);

        // Step 2-2 (recursive call)
        Mergesort(list2);

        // Step 3 (final output)
        return Merge(list1, list2);
    }

    // Merge two sorted lists
    private static List<Integer> Merge(List<Integer> list1, List<Integer> list2)
    {
        // If lists are null then merge cannot continue
        if(list1 == null || list2 == null || list1.size() == 0 || list2.size() == 0)
            return null;

        // Size of the merged list
        int size = list1.size() + list2.size();

        // Merged list
        List<Integer> mergedList = new ArrayList<>();

        while(mergedList.size() < size) mergedList.add(0);

        System.out.print(size + "\nmerged ");

        System.out.print(mergedList.size() + "\n");

        int mergedListIndex = 0;
        int list1Index = 0;
        int list2Index = 0;

        while (mergedListIndex < size &&
                list1Index != list1.size() &&
                list2Index != list2.size())
        {
            if(list1.get(list1Index) < list1.get(list2Index))
            {
                mergedList.set(mergedListIndex, list1.get(list1Index));
                list1Index = list1Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
            else
            {
                mergedList.set(mergedListIndex, list2.get(list2Index));
                list2Index = list2Index + 1;
                mergedListIndex = mergedListIndex + 1;
            }
        }

        return mergedList;
    }
}
