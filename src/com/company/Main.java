package com.company;
import com.garmin.fit.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    static UserInterface ui;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {ui = new UserInterface();});
        DataHandler dataHandler = new DataHandler();

    }

}
