package com.zhukov.minesweeper.game;

import com.zhukov.minesweeper.R;

/**
 * Created by kirill on 12/7/14.
 */
public class Cell {


    private static int[] drawableCells = {
            R.drawable.empty_cell,
            R.drawable.num1,
            R.drawable.num2,
            R.drawable.num3,
            R.drawable.num4,
            R.drawable.num5,
            R.drawable.num6,
            R.drawable.num7,
            R.drawable.num8,
            R.drawable.bomb
    };

    private int position;
    private int state;
    private boolean revealed;
    private boolean flagged;
    private int drawable;

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
