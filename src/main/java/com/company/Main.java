package com.company;

import com.company.GUI.MainFrame;
import com.company.GameMap.Game;
import com.company.Logic.Service_BusinessLogic;

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
