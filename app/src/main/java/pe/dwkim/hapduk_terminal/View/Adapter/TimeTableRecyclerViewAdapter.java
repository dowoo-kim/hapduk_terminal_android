package pe.dwkim.hapduk_terminal.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.dwkim.hapduk_terminal.Model.Enum.RecyclerViewType;
import pe.dwkim.hapduk_terminal.R;
import pe.dwkim.hapduk_terminal.View.Item.ListItem;
import pe.dwkim.hapduk_terminal.View.ViewHolder.TimeTableRecyclerViewHolderFirstItem;
import pe.dwkim.hapduk_terminal.View.ViewHolder.TimeTableRecyclerViewHolderItem;

/**
 * Created by dwkim on 16. 5. 24..
 */
public class TimeTableRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ListItem> listItem;
    private Context context;

    public TimeTableRecyclerViewAdapter(Context context, List<ListItem> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = null;

        if(viewType == RecyclerViewType.FIRST_ITEM.ordinal()){
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_first_time_table, null);
            TimeTableRecyclerViewHolderFirstItem rcv = new TimeTableRecyclerViewHolderFirstItem(layoutView);
            return rcv;
        }
        else{
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_time_table, null);
            TimeTableRecyclerViewHolderItem rcv = new TimeTableRecyclerViewHolderItem(layoutView);
            return rcv;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TimeTableRecyclerViewHolderFirstItem){
            TimeTableRecyclerViewHolderFirstItem firstItem = (TimeTableRecyclerViewHolderFirstItem)holder;
            firstItem.firstDepartureTime.setText(listItem.get(position).getDepartureTime().toString());
            firstItem.firstStopsName.setText(listItem.get(position).getStopInfoName().toString());
        }
        else{
            TimeTableRecyclerViewHolderItem item = (TimeTableRecyclerViewHolderItem)holder;
            item.stopsName.setText(listItem.get(position).getStopInfoName());
        }
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listItem.get(position).getViewType().ordinal();
    }
}
