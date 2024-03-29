package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EntryManager {

    public ArrayList<String[]> headers = new ArrayList<>();

    public Entry getPastEntry(int i) {
        //Deserializing the entry arrayList
        try {
            String fileSer = "data/entry_data.xml";
            FileInputStream file = new FileInputStream(fileSer);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            ArrayList<Entry> arrayList = (ArrayList<Entry>)in.readObject();
            Entry pastEntry = arrayList.get(i);

            in.close();
            file.close();

            return pastEntry;
        }
        catch(IOException ex) {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return null;
    }

    //heart rate 0; cadence 1; distance 2; speed 3; altitude 4; temp 5; time 6
    public Entry makeNewEntry(String fileName) {
        DataHandler dataHandler = new DataHandler();
        dataHandler.decodeFile(fileName);

        //pass entry to GoalManager
        GoalManager goalManager = new GoalManager();
        goalManager.updateGoals(dataHandler.getData());

        Entry entry = new Entry(dataHandler.getDate(), dataHandler.getData());

        //delete file that DataHandler uses
        File tempFile = new File("data/text.txt");
        tempFile.delete();

        String fileSer = "data/entry_data.xml";

        //This is deserializing and serializing the file with entry objects when making a new entry
        try {
            FileInputStream file = new FileInputStream(fileSer);
            ObjectInputStream in = new ObjectInputStream(file);

            ArrayList<Entry> arrayList = (ArrayList<Entry>) in.readObject();

            in.close();
            file.close();

            //SERIALIZATION (to serialize the array list with new entry)
            try {
                FileOutputStream file2 = new FileOutputStream(fileSer);
                ObjectOutputStream out = new ObjectOutputStream(file2);

                arrayList.add(entry);
                // Method for serialization of object
                out.writeObject(arrayList);

                out.close();
                file.close();

                System.out.println("Object has been serialized");

            } catch (IOException ex) {
                System.out.println("Serialization failed in new entry: " + ex);
            }

        } catch (IOException ex) {
            System.out.println("exception in make new entry: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        deserializeHeaders();

        String[] miniHeadersArray = new String[3];
        miniHeadersArray[0] = entry.getDate();
        miniHeadersArray[1] = entry.getDistanceTravelled();
        miniHeadersArray[2] = entry.getTimeElapsed();

        this.headers.add(miniHeadersArray);

        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("data/past_entry_headers.xml");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(headers);

            out.close();
            file.close();

            System.out.println("past entry headers have been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        return entry;
    }

    public ArrayList<String[]> getHeaders() {
        return headers;
    }

    public void deserializeHeaders() {
        String stringArraysFile = "data/past_entry_headers.xml";

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(stringArraysFile);
            ObjectInputStream in = new ObjectInputStream(file);

            ArrayList<String[]> headers = (ArrayList<String[]>) in.readObject();
            this.headers = headers;

            in.close();
            file.close();
            System.out.println("headers have been deserialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught in deserialize headers: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

}
