package com.zhukov.minesweeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.zhukov.minesweeper.R;
import com.zhukov.minesweeper.game.Board;
import com.zhukov.minesweeper.game.Cell;


/**
 * Created by kirill on 12/7/14.
 */
public class BoardAdapter extends BaseAdapter {
    private Context mContext;
    private Board board;
    private Cell[] cells;


    // references to our images
    private Integer[] cellsImages = {
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


    public BoardAdapter(Context applicationContext, Board board) {
        mContext = applicationContext;
        this.board = board;
        cells = board.getCells();
    }

    public int getCount() {
        return cells.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.drawable.empty_cell);
        return imageView;
    }

}
