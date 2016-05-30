package pe.dwkim.hapduk_terminal.View.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pe.dwkim.hapduk_terminal.R;

/**
 * Created by dwkim on 16. 5. 24..
 */
public class TimeTableRecyclerViewHolderItem extends RecyclerView.ViewHolder{
    public TextView departureTime;
    public TextView stopsName;

    public TimeTableRecyclerViewHolderItem(View itemView) {
        super(itemView);
        departureTime = (TextView)itemView.findViewById(R.id.text_departure_time);
        stopsName = (TextView) itemView.findViewById(R.id.text_stops_name);
    }
}