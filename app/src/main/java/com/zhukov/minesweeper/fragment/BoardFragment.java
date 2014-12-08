package com.zhukov.minesweeper.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.zhukov.minesweeper.R;
import com.zhukov.minesweeper.adapter.BoardAdapter;
import com.zhukov.minesweeper.game.Board;
import com.zhukov.minesweeper.game.Cell;
import com.zhukov.minesweeper.game.Game;


public class BoardFragment extends Fragment {

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
    Game game = new Game();
    Board board = new Board(10, 10, 10);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridView gridView = (GridView) this.getActivity().findViewById(R.id.board_grid_view);
        gridView.setAdapter(new BoardAdapter(getActivity().getApplicationContext(), board));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);

        GridView boardGridView = (GridView) view.findViewById(R.id.board_grid_view);
        boardGridView.setAdapter(new BoardAdapter(boardGridView.getContext(), game.getBoard())); // uses the view to get the context instead of getActivity().
        boardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Cell cell = (Cell) parent.getAdapter().getItem(position);
                clickCell(cell, Game.Action.OPEN);
            }
        });

        boardGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                Cell cell = (Cell) parent.getAdapter().getItem(position);

                if (cell.isFlagged()) {
                    clickCell(cell, Game.Action.REMOVE_FLAG);
                } else {
                    clickCell(cell, Game.Action.SET_FLAG);
                }

                return true;
            }
        });
        getActivity().invalidateOptionsMenu();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (game.gameHasEnded()) {
            startNewGame();
        } else {
            attachGameToGridView();
            refreshGameBoard();
        }
    }

    public void startNewGame() {
        if (isAdded()) {
            game.init(10, 10, 10);

            attachGameToGridView();
            refreshGameBoard();
        }
    }

    private void attachGameToGridView() {
        View view = getView();
        GridView boardGridView = (GridView) view.findViewById(R.id.board_grid_view);
        Board board = game.getBoard();
        Cell[] cells = board.getCells();
        int cellsAmount = cells.length;
        boardGridView.setNumColumns(cellsAmount);
        boardGridView.setAdapter(new BoardAdapter(getActivity().getApplicationContext(), board));
    }

    private void refreshGameBoard() {
        View view = getView();
        GridView boardGridView = (GridView) view.findViewById(R.id.board_grid_view);
        int childrenAmount = boardGridView.getChildCount();
        int firstPos = boardGridView.getFirstVisiblePosition();

        boardGridView.invalidateViews();
        for (Cell cell : game.getBoard().getCells()) {
            ImageView imageView = (ImageView) boardGridView.getChildAt(cell.getPosition());
            imageView.setImageResource(drawableCells[cell.getState()]);
        }
    }

    private void clickCell(Cell cell, Game.Action action) {

        if (!game.gameHasEnded()) {
            game.action(cell, action);

            // Checked again to see if players move ended the game
            if (game.gameHasEnded()) {
                if (game.lost()) createPlayerLostDialog();
                if (game.won()) createPlayerWonDialog();
            }
        }
        refreshGameBoard();
    }

    private void createPlayerWonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You Won").setTitle("Game Over");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createPlayerLostDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You Lost").setTitle("Game Over");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
