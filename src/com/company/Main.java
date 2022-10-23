package com.company;
import com.garmin.fit.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    static UserInterface ui;
    static Graph graph;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {ui = new UserInterface();});
        SwingUtilities.invokeLater(() -> {graph = new Graph();});
        DataHandler dataHandler = new DataHandler();


    }

}
