package com.zhukov.minesweeper.game;

/**
 * Created by kirill on 12/7/14.
 */
public class Game {


    public enum Action {
        OPEN, SET_FLAG, REMOVE_FLAG
    }

    // All possible states of the game
    private enum GameState {
        PLAYING, STOPPED, WON, LOST
    }

    private Board board;
    private GameState gameState = GameState.STOPPED;

    private int mines;
    private int flags;

    public Game() {

        mines = 0;
        flags = 0;

    }

    public void init(int rows, int columns, int mines) {

        board = new Board(rows, columns, mines);
        gameState = GameState.PLAYING;

        this.mines = mines;
        flags = 0;
    }

    public void action(Cell cell, Action action) {

        if (!gameHasEnded()) {

            switch (action) {

                case OPEN:
                    openCell(cell);
                    break;
                case SET_FLAG:
                    setFlag(cell);
                    break;
                case REMOVE_FLAG:
                    removeFlag(cell);
                    break;
                default:
                    break;
            }
        }

    }

    public boolean gameHasEnded() {
        return gameState != GameState.PLAYING;
    }

    private void openCell(Cell cell) {

        if (!cell.isFlagged()) {

            if (cell.isMine()) {
                cell.reveal();
                loseGame();
            } else {
                board.openAround(cell);
                if (board.isSolved()) winGame();
            }
        }
    }

    private void loseGame() {
        gameState = GameState.LOST;
        board.reveal();
    }

    private void winGame() {
        gameState = GameState.WON;
        board.reveal();
    }

    private void removeFlag(Cell cell) {
        if (cell.isFlagged()) {
            cell.setFlag(false);
            flags--;
        }
    }

    private void setFlag(Cell cell) {
        if (!cell.isRevealed()) {
            cell.setFlag(true);
            flags++;
        }
    }

    protected Board getBoard() {
        return board;
    }
}
