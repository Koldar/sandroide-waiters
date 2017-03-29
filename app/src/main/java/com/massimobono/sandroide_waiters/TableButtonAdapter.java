package com.massimobono.sandroide_waiters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.massimobono.sandroide_waiters.model.ButtonStateManager;
import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.Model;
import com.massimobono.sandroide_waiters.model.TableState;

/**
 * Created by massi on 3/8/2017.
 */
public class TableButtonAdapter extends RecyclerView.Adapter<TableButtonAdapter.ViewHolder> {

    private static final String TAG = TableButtonAdapter.class.getSimpleName();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView primaryText;
        public TextView secondayText;
        public ButtonStateManager buttonImageManager;

        public ViewHolder(View v) {
            super(v);
            this.image = (ImageView) v.findViewById(R.id.buzz_image_onoff);
            this.primaryText = (TextView) v.findViewById(R.id.primaryText);
            this.secondayText = (TextView) v.findViewById(R.id.secondaryText);
            this.buttonImageManager = null;
        }

        public void setImage(final int resourceId) {
            this.itemView.post(new Runnable() {
                @Override
                public void run() {
                    image.setImageResource(resourceId);
                }
            });
        }
    }

    /**
     * Represent the model used by the adapter to fetch the items in the recycle view
     */
    private Model model;

    public TableButtonAdapter(Model model) {
        this.model = model;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
            to create such layout, the following links were used:
            - https://stackoverflow.com/questions/8180887/how-to-align-linearlayout-at-the-center-of-its-parent
            - https://stackoverflow.com/questions/13264794/font-size-of-textview-in-android-application-changes-on-changing-font-size-from
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_button_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ITable table = this.model.getDao().getTable(position);

        Log.d(TAG, String.format("binding on %d", position));
        holder.primaryText.setText(String.format(
                holder.primaryText.getContext().getString(R.string.table_primary_text),
                table.getId()
        ));

        holder.secondayText.setText(table.getName());
        holder.buttonImageManager = new ButtonStateManager(table, holder);
        table.addTableListener(holder.buttonImageManager);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.setStatus(TableState.RESOLVED);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int)this.model.getDao().getTableNumber();
    }
}
