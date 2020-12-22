package com.drjukova.gui;


import com.drjukova.model.Cell;
import com.drjukova.model.CellState;
import com.drjukova.model.Game;
import com.drjukova.services.GameService;
import com.drjukova.services.SerialiseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private Game game;
    private Controller controller;
    private JMenuItem newGame;
    private GameService logic;
    private Button button = new Button("Выстрел");
    private Button button2 = new Button("Сохранить игру");
    private Button button3 = new Button("Восстановить игру");
    private SerialiseService serialiseService = new SerialiseService();


    public MainFrame(Game game) {
        this.game = game;
        this.createGUI();
        this.controller = new Controller(this, game);
        this.attachController();
        this.logic = new GameService();
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
        panelPlayerA.setBounds(129, 31, 151, 151);
        this.getContentPane().add(panelPlayerA);

        Field panelPlayerB = new Field(this.game, 1);
        panelPlayerB.setBounds(300, 31, 151, 151);
        this.getContentPane().add(panelPlayerB);

   /*     ScoreField panelScore1 = new ScoreField(this.game, 0);
        panelScore1.setBounds(370, 31, 90, 151);
        panelScore1.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(panelScore1);

        ScoreField panelScore2 = new ScoreField(this.game, 1);
        panelScore2.setBounds(470, 31, 90, 151);
        panelScore2.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(panelScore2);

    */


        panel.add(button);
        this.add(panel);
        button.setBounds(129, 200, 321, 20);
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

        panel.add(button2);
        this.add(panel);
        button2.setBounds(129, 250, 140, 20);
        button2.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (game != null) {
                    Cell[][] newCells = game.getPlayerList().get(0).
                            getBattleField().getCells();


                    serialiseService.serialise(newCells, 0);

                    newCells = game.getPlayerList().get(1).
                            getBattleField().getCells();
                    serialiseService.serialise(newCells, 1);
                }
                repaint();
            }
        });

        panel.add(button3);
        this.add(panel);
        button3.setBounds(311, 251, 140, 20);
        button3.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(button3);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.getPlayerList().get(0).
                        getBattleField().setBattlefieldCells(serialiseService.deserialize(0));
                game.getPlayerList().get(1).
                        getBattleField().setBattlefieldCells(serialiseService.deserialize(1));

                Cell[][] cFirstPlayer = game.getPlayerList().get(0).
                        getBattleField().getCells();
                Cell[][] cSecondPlayer = game.getPlayerList().get(1).
                        getBattleField().getCells();

                ArrayList<Cell> cellsOfFirstPlayer = new ArrayList<>();
                ArrayList<Cell> cellsOfSecondPlayer = new ArrayList<>();
                for (int i = 0; i < cFirstPlayer.length; i++) {
                    for (int j = 0; j < cFirstPlayer.length; j++) {
                        if (cFirstPlayer[i][j].getState() != CellState.injured && cFirstPlayer[i][j].getState() != CellState.missed) {
                            cellsOfFirstPlayer.add(cFirstPlayer[i][j]);
                        }
                        if (cSecondPlayer[i][j].getState() != CellState.injured && cSecondPlayer[i][j].getState() != CellState.missed) {
                            cellsOfSecondPlayer.add(cSecondPlayer[i][j]);
                        }
                    }
                }
                game.getPlayerList().get(0).
                        getBattleField().setShotPoints(cellsOfFirstPlayer);
                game.getPlayerList().get(1).
                        getBattleField().setShotPoints(cellsOfSecondPlayer);

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