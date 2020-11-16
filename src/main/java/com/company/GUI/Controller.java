package com.company.GUI;

import com.company.GameMap.Game;
import com.company.Logic.Service_BusinessLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    public Game game;
    public MainFrame view;


    public Controller(MainFrame view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Новая игра")) {
            Game game1 = new Game();
            Service_BusinessLogic logic = new Service_BusinessLogic();
            MainFrame view = new MainFrame(game1);
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
        if (cmd.equals("Exit")) {
            System.exit(0);
        }

    }
}
