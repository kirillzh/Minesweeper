package com.zhukov.minesweeper.board;

/**
 * Created by kirill on 12/7/14.
 */
public class Cell {

    private int position;
    private int state;
    private boolean opened;

    public Cell(int position, int state, boolean opened) {
        this.position = position;
        this.state = position;
        this.opened = opened;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return state + "";
    }

}
