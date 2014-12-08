package com.zhukov.minesweeper.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zhukov.minesweeper.R;
import com.zhukov.minesweeper.activity.BoardActivity;

public class MenuListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public MenuListFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.menu_items, android.R.layout.simple_list_item_1);
        setListAdapter(arrayAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), BoardActivity.class));
            case 1:
                startActivity(new Intent(getActivity(), BoardActivity.class));
            case 2:
        }
    }
}