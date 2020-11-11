package com.company.GUI;


import com.company.GameMap.Game;
import com.company.Logic.Service_BusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameView extends JFrame {
    private static final long serialVersionUID = 1L;
    private Game game;
    private GameController controller;
    private JMenuItem newGame;
    private JMenuItem exit;
    private Service_BusinessLogic logic;
    private JButton button = new JButton();

    public GameView(Game game) {
        this.game = game;
        this.createGUI();
        this.controller = new GameController(this, game);
        this.attachController();
    }


    public void attachController() {
        this.newGame.addActionListener(this.controller);
        this.exit.addActionListener(this.controller);
    }

    private void createGUI() {
        this.setTitle("SeaBattle");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.setResizable(false);
        this.setBounds(400, 300, 600, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout((LayoutManager) null);

        Field panelPlayerA = new Field(this.game, 0);
        panelPlayerA.setBounds(20, 31, 151, 151);
        this.getContentPane().add(panelPlayerA);

        Field panelPlayerB = new Field(this.game, 1);
        panelPlayerB.setBounds(190, 31, 151, 151);
        this.getContentPane().add(panelPlayerB);

        ScoreField panelScore1 = new ScoreField(this.game, 0);
        panelScore1.setBounds(370, 31, 90, 151);
        panelScore1.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(panelScore1);

        ScoreField panelScore2 = new ScoreField(this.game, 1);
        panelScore2.setBounds(470, 31, 90, 151);
        panelScore2.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(panelScore2);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        this.getContentPane().add(menuBar);
        JMenu mnGame = new JMenu("Game");
        menuBar.add(mnGame);
        this.newGame = new JMenuItem("New game");
        mnGame.add(this.newGame);
        this.exit = new JMenuItem("Exit");
        mnGame.add(this.exit);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        button.doClick();
    }
}