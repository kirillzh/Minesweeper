package com.zhukov.minesweeper.game;

import java.util.Random;


/**
 * Created by kirill on 12/7/14.
 */
public class Board {

    private final static int BEGINNER_ROWS = 8;
    private final static int BEGINNER_COLUMNS = 8;
    private final static int BEGINNER_MINES = 10;
    private final static int INTERMEDIATE_ROWS = 16;
    private final static int INTERMEDIATE_COLUMNS = 16;
    private final static int INTERMEDIATE_MINES = 40;
    private final static int EXPERT_ROWS = 30;
    private final static int EXPERT_COLUMNS = 16;
    private final static int EXPERT_MINES = 99;
    private final static int[][] levels = {
            {BEGINNER_ROWS, BEGINNER_COLUMNS, BEGINNER_MINES},
            {INTERMEDIATE_ROWS, INTERMEDIATE_COLUMNS, INTERMEDIATE_MINES},
            {EXPERT_ROWS, EXPERT_COLUMNS, EXPERT_MINES}
    };

    private int rows;
    private int columns;
    private int mines;
    private Cell[] cells;

    public Board(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        reset();
    }

    public Board(int level) {
        this(levels[level][0], levels[level][1], levels[level][2]);
    }

    public static void main(String[] args) {
        Board board = new Board(0);
        System.out.println(board.toString());
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    private Cell addCell(int position, Cell cell) {
        cells[position] = cell;
        return cells[position];
    }


    private Cell getCell(int position) {
        return cells[position];
    }

    public Cell[] getCells() {
        return cells;
    }

    private void initiateBoard(int rows, int columns) {
        cells = new Cell[rows * columns];
    }

    private void initiateBoard() {
        initiateBoard(rows, columns);
    }

    private void empty() {
        for (int position = 0; position < cells.length; position++)
            cells[position] = new Cell(position, 0, false);
    }

    private void placeRandomBombs() {
        for (int i = 0; i < mines; i++)
            cells[(new Random()).nextInt(cells.length)].setState(9);
    }

    private void calculateStates() {
        for (Cell cell : cells)
            cell.setState(calculateState(cell.getPosition()));
    }

    private void reset() {
        initiateBoard();
        empty();
        placeRandomBombs();
        calculateStates();
    }

    private int calculateState(int position) {
        if (cells[position].getState() == 9)
            return 9;

        int x = position / columns;
        int y = position % columns;
        int minesAround = 0;
        for (int row = x - 1; row < x + 2; row++) {
            if (row < 0 || row >= rows)
                continue;
            for (int col = y - 1; col < y + 2; col++) {
                if (col < 0 || col >= columns)
                    continue;
                if (cells[(row * columns) + col].getState() == 9)
                    minesAround++;
            }
        }
        return minesAround;
    }

    public void reveal() {
        for (Cell cell : cells)
            cell.reveal();
    }

    public void openAround(Cell cell) {
        int x = cell.getPosition() / columns;
        int y = cell.getPosition() % columns;
        for (int row = x - 1; row < x + 2; row++) {
            if (row < 0 || row >= rows)
                continue;
            for (int col = y - 1; col < y + 2; col++) {
                if (col < 0 || col >= columns)
                    continue;
                cell = cells[row * columns + col];
                if (!cell.isRevealed()) {
                    if (!cell.isMine())
                        cell.reveal();
                    openAround(cell);
                }
            }
        }
    }

    protected boolean isSolved() {
        int openedCells = 0;
        for (Cell cell : cells) {
            openedCells += cell.isRevealed() ? 1 : 0;
        }
        return openedCells == (cells.length - mines);
    }


    @Override
    public String toString() {
        if (cells == null) {
            return "null";
        }
        if (cells.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(cells.length * 7);
        sb.append('[');
        sb.append(cells[0]);
        for (int i = 1; i < cells.length; i++) {
            sb.append(", ");
            sb.append(cells[i].getState());
        }
        sb.append(']');
        return sb.toString();
    }


    public void print() {
        for (Cell cell : cells) {
            if (cell.getPosition() % columns == 0) {
                System.out.println();
            }
            System.out.print(cell.getState() + " ");
        }
    }

}
