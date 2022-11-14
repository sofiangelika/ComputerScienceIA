package com.company;

import java.io.*;

public class DataHandler {

     public String getDate() {

        String date = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("data/text.txt"))) {
            date = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

     void decodeFile(String fileName) {
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
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

    }

     int getRecordCount() {
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

     public double[][] getData() {
        int recordCount = getRecordCount();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/text.txt"))) {
            double[][] data = new double[7][recordCount]; //create 2d array for fit file data
            int rows = 0;
            String line2;
            double starting_time = 0.0;
            while ((line2 = reader.readLine()) != null) {
                if (line2.contains("Record:")) {
                    for (int i = 0; i < 6; i++) {
                        data[i][rows] = Double.parseDouble(reader.readLine());
                    }
                    if (starting_time == 0.0) {
                        starting_time = Double.parseDouble(reader.readLine());
                        data[6][rows] = 0;
                    }
                    else {
                        data[6][rows] = Double.parseDouble(reader.readLine()) - starting_time;
                    }
                    rows++;
                }
            }


            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
