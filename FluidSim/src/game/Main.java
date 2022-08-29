package game;

import engine.GameEngine;

public class Main {

    public static void main(String[] args){
        try {
            GameEngine gameEng = new GameEngine("Fluid Simulation", 500, 500, 100);
            gameEng.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
