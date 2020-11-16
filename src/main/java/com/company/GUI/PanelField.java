package com.company.GUI;

import com.company.GameMap.Game;

import javax.swing.*;
import java.awt.*;

public class PanelField extends JPanel {
    private static final long serialVersionUID = 1L;
    public Game game;


    public PanelField(Game game) {
        this.game = game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // рисуем элементы
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                paintElement(g, i, j);
            }
        }
    }

    /**
     * рисование элементов кораблей
     */
    public void paintElement(Graphics g, int i, int j) {

    }



}
