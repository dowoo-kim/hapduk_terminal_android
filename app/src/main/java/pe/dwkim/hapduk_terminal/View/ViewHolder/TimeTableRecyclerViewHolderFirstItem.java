package pe.dwkim.hapduk_terminal.View.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pe.dwkim.hapduk_terminal.R;

/**
 * Created by dwkim on 16. 5. 25..
 */
public class TimeTableRecyclerViewHolderFirstItem extends RecyclerView.ViewHolder{
    public TextView firstDepartureTime;
    public TextView firstStopsName;

    public TimeTableRecyclerViewHolderFirstItem(View itemView) {
        super(itemView);
        firstDepartureTime = (TextView)itemView.findViewById(R.id.text_first_departure_time);
        firstStopsName = (TextView) itemView.findViewById(R.id.text_first_stops_name);
    }
}
