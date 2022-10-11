package com.company;

import com.garmin.fit.FitDecoder;
import com.garmin.fit.FitMessages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DataHandler {

    public DataHandler() {

        String stringPath = "/Users/sofpo/Desktop/cs_IA/Afternoon_Ride_1.fit";
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
