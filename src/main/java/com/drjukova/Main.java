package com.drjukova;

import com.drjukova.gui.MainFrame;
import com.drjukova.model.Game;
import com.drjukova.services.SerialiseService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (
                Throwable var3) {
            var3.printStackTrace();
        }

        MainFrame view = new MainFrame(game);
        view.setVisible(true);
    }
}