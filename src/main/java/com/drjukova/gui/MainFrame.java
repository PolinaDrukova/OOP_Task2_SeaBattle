package com.drjukova.gui;


import com.drjukova.game.Game;
import com.drjukova.gameLogic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Game game;
    private Controller controller;
    private JMenuItem newGame;
    private GameLogic logic;
    private Button button = new Button("Выстрел");


    public MainFrame(Game game) {
        this.game = game;
        this.createGUI();
        this.controller = new Controller(this, game);
        this.attachController();
        this.logic = new GameLogic();
    }


    public void attachController() {
        this.newGame.addActionListener(this.controller);
    }


    private void createGUI() {
        this.setTitle("Морской бой");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.setResizable(false);
        this.setBounds(400, 300, 600, 350);
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






        panel.add(button);
        this.add(panel);
        button.setBounds(20, 200, 321, 20);
        button.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logic.shotStep(game);

                if (!(logic.isAlivePlayer(game, 0)) || !(logic.isAlivePlayer(game, 1))) {
                    if (logic.whoWin(game) == -1) {
                        String message = " Ничья ";
                        JOptionPane.showMessageDialog(null, message);
                    } else {
                        String message = " Выйграл " + logic.whoWin(game) + " игрок !";
                        JOptionPane.showMessageDialog(null, message);
                    }
                    repaint();
                }

                repaint();

            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 21);
        this.getContentPane().add(menuBar);
        JMenu mnGame = new JMenu("Игра");
        menuBar.add(mnGame);
        this.newGame = new JMenuItem("Новая игра");
        mnGame.add(this.newGame);

    }
}