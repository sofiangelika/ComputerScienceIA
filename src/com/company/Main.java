package com.company;
import com.garmin.fit.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        UserInterface userInterface = new UserInterface();
        userInterface.mainWindow();

        String stringPath = "/Users/sofpo/Desktop/cs_IA/Lunch_Ride_2.fit";
        try {
            FileInputStream input = new FileInputStream(stringPath);
            FitDecoder fitDecoder = new FitDecoder();
            FitMessages fitMessages = fitDecoder.decode(input);
            System.out.println("FileId Messages: " + fitMessages.getFileIdMesgs().size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
