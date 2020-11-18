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
            MainFrame view = new MainFrame(game1);
            view.setVisible(true);
        }
        if (cmd.equals("Exit")) {
            System.exit(0);
        }

    }
}
