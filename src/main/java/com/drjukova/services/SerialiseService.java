package com.drjukova.services;

import com.drjukova.model.Cell;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SerialiseService {
    Gson gson = new Gson();

    public void serialise(Cell[][] cells, int n) {
        gson.toJson(cells);
        Cell[][] newCells = new Cell[cells.length][cells.length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                newCells[i][j] = cells[i][j];
            }
        }
        try {
            FileWriter fw = new FileWriter("Cells" + n +".json");
            gson.toJson(newCells, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cell[][] deserialize(int n) {
        Gson gson = new Gson();
        Cell[][] cell = null;
        try {
            FileReader fr = new FileReader("Cells" + n +".json");
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) {
                sb.append((char) c);
            }
            String  json = sb.toString();
            cell = gson.fromJson(json, Cell[][].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cell;
    }
}
