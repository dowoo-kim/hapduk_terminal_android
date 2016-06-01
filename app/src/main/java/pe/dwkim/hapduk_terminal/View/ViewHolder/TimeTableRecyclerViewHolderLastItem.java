package pe.dwkim.hapduk_terminal.View.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pe.dwkim.hapduk_terminal.R;

/**
 * Created by dwkim on 16. 5. 24..
 */
public class TimeTableRecyclerViewHolderLastItem extends RecyclerView.ViewHolder{
    public TextView lastDepartureTime;
    public TextView lastStopsName;

    public TimeTableRecyclerViewHolderLastItem(View itemView) {
        super(itemView);
        lastDepartureTime = (TextView)itemView.findViewById(R.id.text_last_departure_time);
        lastStopsName = (TextView) itemView.findViewById(R.id.text_last_stops_name);
    }
}