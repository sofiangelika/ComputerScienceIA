package com.company;

import java.io.*;
import java.util.Arrays;

public class DataHandler {

    public static void main(String[] args) {
        //heart rate 0; cadence 1; distance 2; speed 3; altitude 4; temp 5; time 6
        String stringPath = "/Users/sofpo/Desktop/cs_IA/the_one_that_has_it_all.fit";
        decodeFile(stringPath);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(Arrays.deepToString(getData(stringPath))); //delete this at some point


    }


    static void decodeFile(String fileName) {
        //put fit file contents in text file

        PrintStream fileStream = null;
        try {
            System.out.println();
            fileStream = new PrintStream("data/text.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(fileStream);
        AccessFile accessFile = new AccessFile(fileName);

    }

    static int getRecordCount(String fileName) {

        //read created text data file
        int size = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("data/text.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Record:")) {
                    size++; //getting the amount of records in fit file
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    static double[][] getData(String fileName) {
        int recordCount = getRecordCount(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader("data/text.txt"))) {
            double[][] data = new double[7][recordCount]; //create 2d array for fit file data
            int rows = 0;
            String line2;
            while ((line2 = reader.readLine()) != null) {
                if (line2.contains("Record:")) {
                    for (int i = 0; i < 7; i++) {
                        data[i][rows] = Double.parseDouble(reader.readLine());
                    }
                    rows++;
                }
            }
            File file = new File("data/text.txt");
            file.delete();

            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
