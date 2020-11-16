package com.company;

import com.company.GUI.MainFrame;
import com.company.GameMap.Game;
import com.company.Logic.Service_BusinessLogic;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Service_BusinessLogic logic = new Service_BusinessLogic();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (
                Throwable var3) {
            var3.printStackTrace();
        }

        MainFrame view = new MainFrame(game);

        view.setVisible(true);


        if (!(logic.isAlivePlayer(game, 0)) || !(logic.isAlivePlayer(game, 0))) {
            if (logic.whoWin(game) == -1) {
                String message = " Ничья ";
                JOptionPane.showMessageDialog(null, message);
            } else {
                String message = " Выйграл " + logic.whoWin(game) + " игрок";
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }
}
