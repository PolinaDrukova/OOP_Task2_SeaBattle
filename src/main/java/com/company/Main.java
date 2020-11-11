package com.company;

import com.company.GUI.GameView;
import com.company.GameMap.Game;
import com.company.Logic.Service_BusinessLogic;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Service_BusinessLogic logic = new Service_BusinessLogic();
        logic.newGame(game);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (
                Throwable var3) {
            var3.printStackTrace();
        }

        GameView view = new GameView(game);

        view.setVisible(true);

        if (logic.whoWin(game) == -1) {
            String message = " Ничья ";
            JOptionPane.showMessageDialog(null, message);
        } else {
            String message = " Выйграл " + logic.whoWin(game) + " игрок";
            JOptionPane.showMessageDialog(null, message);
        }

    }
}
