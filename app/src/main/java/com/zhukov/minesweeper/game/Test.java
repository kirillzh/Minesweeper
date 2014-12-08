package com.zhukov.minesweeper.game;

/**
 * Created by kirill on 12/8/14.
 */
public class Test {

    public static void main(String[] args) {
        Game game = new Game();

        game.init(8, 8, 10);
        game.getBoard().print();
    }

}
