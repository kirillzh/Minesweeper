package com.zhukov.minesweeper.game;

/**
 * Created by kirill on 12/7/14.
 */
public class Cell {

    private int position;
    private int state;
    private boolean revealed;
    private boolean flagged;

    public Cell(int p, int s, boolean r) {
        this.position = p;
        this.state = s;
        this.revealed = r;
    }

    public int getPosition() {
        return position;
    }

    public int getState() {
        return state;
    }

    public void setState(int s) {
        state = s;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlag(boolean f) {
        flagged = f;
    }

    public boolean isMine() {
        return state == 9;
    }
}
