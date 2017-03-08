package com.massimobono.sandroide_waiters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.massimobono.sandroide_waiters.model.ITable;

import java.util.Collection;

/**
 * Created by massi on 3/8/2017.
 */
public class TableButtonAdapter extends RecyclerView.Adapter<TableButtonAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tableName;

        public ViewHolder(TextView tableName) {
            super(tableName);
            this.tableName = tableName;
        }
    }

    private Collection<ITable> tables;

    public TableButtonAdapter(Collection<ITable> tables) {
        this.tables = tables;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create new view

        /*
            to create such layout, the following links were used:
            - https://stackoverflow.com/questions/8180887/how-to-align-linearlayout-at-the-center-of-its-parent
         */
        LayoutInflater.from(parent.getContext()).inflate(, parent, false);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
