package pe.dwkim.hapduk_terminal.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pe.dwkim.hapduk_terminal.Model.Destination;
import pe.dwkim.hapduk_terminal.Model.Enum.RecyclerViewType;
import pe.dwkim.hapduk_terminal.Model.Route;
import pe.dwkim.hapduk_terminal.Model.StopInfo;
import pe.dwkim.hapduk_terminal.R;
import pe.dwkim.hapduk_terminal.Service.HapdukTerminalClient;
import pe.dwkim.hapduk_terminal.View.Adapter.CustomExpandableListAdapter;
import pe.dwkim.hapduk_terminal.View.Adapter.TimeTableRecyclerViewAdapter;
import pe.dwkim.hapduk_terminal.View.Item.ListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dwkim on 16. 5. 20..
 */
public class IntercityActivity extends AppCompatActivity{
    private AlertDialog dialog;

    private GridLayoutManager gridLayoutManager;

    private HashMap<String, List<Destination>> expandableListChild = new HashMap<String, List<Destination>>();
    private List<String> expandableListGroupName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercity);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.pleaseSendManyUserComment, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void clickSelectDestinationButton(View view){
        getIntercityDestinations();
    }

    public void getIntercityDestinations(){
        Call<List<Destination>> call = HapdukTerminalClient.get().getIntercityDestinations();

        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                List<Destination> destinations = response.body();
                showClickSelectDestinationDialog(destinations);
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                showToast("서버와 통신에 실패 하였습니다.");
                Log.d("Hapduk_terminal : ", "IntercityActivity.getIntercityDestination ERROR : " + t.getMessage());
            }
        });
    }

    public void showClickSelectDestinationDialog(final List<Destination> destinations){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("도착지를 선택 해주세요");

        for(int a = 0; destinations.size() > a; a++){
            String divisionName = destinations.get(a).getDivisionName();

            if(expandableListGroupName.size() == 0){
                expandableListGroupName.add(divisionName);
            }

            for(int b = 0; expandableListGroupName.size() > b; b++){
                if(!expandableListGroupName.contains(divisionName)){
                    expandableListGroupName.add(divisionName);
                }
            }
        }

        for(int a = 0; expandableListGroupName.size() > a; a++){
            List<Destination> childList = new ArrayList<Destination>();

            for(int b = 0; destinations.size() > b; b++){
                if(expandableListGroupName.get(a).equals(destinations.get(b).getDivisionName())){
                    childList.add(destinations.get(b));
                }
            }

            expandableListChild.put(expandableListGroupName.get(a), childList);
        }

        ExpandableListView expandableListView = new ExpandableListView(this);
        expandableListView.setGroupIndicator(ResourcesCompat.getDrawable(
                getResources(), R.drawable.expandable_list_view_group_indicator_selector, null));
        CustomExpandableListAdapter myAdapter = new CustomExpandableListAdapter(this, expandableListGroupName, expandableListChild);

        expandableListView.setAdapter(myAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Destination selectedDestination = expandableListChild.get(expandableListGroupName.get(groupPosition)).get(childPosition);

                getRoute(selectedDestination.getDestinationId());

                return true;
            }
        });

        builder.setView(expandableListView);
        dialog = builder.create();
        dialog.show();
    }

    private void getRoute(int destinationId){
        Call<List<StopInfo>> call = HapdukTerminalClient.get().getRoute(destinationId);

        call.enqueue(new Callback<List<StopInfo>>() {
            @Override
            public void onResponse(Call<List<StopInfo>> call, Response<List<StopInfo>> response) {
                List<StopInfo> stopInfoList = response.body();
                setRecyclerView(stopInfoList);
            }

            @Override
            public void onFailure(Call<List<StopInfo>> call, Throwable t) {
                showToast("서버와 통신에 실패 하였습니다.");
                Log.d("Hapduk_terminal : ", "IntercityActivity.getRoute ERROR : " + t.getMessage());
            }
        });

        if(dialog != null){
            dialog.dismiss();
        }
    }

    private void setRecyclerView(List<StopInfo> stopInfoList){
        List<ListItem> rowListItem = new ArrayList<ListItem>();

        for(StopInfo stopInfo : stopInfoList){
            rowListItem.add(new ListItem(
                    stopInfo.getId(),
                    stopInfo.getRouteId(),
                    stopInfo.getDeparture_time(),
                    stopInfo.getName(),
                    stopInfo.getSequence()));
        }

        gridLayoutManager = new GridLayoutManager(IntercityActivity.this, 1);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.time_table_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        TimeTableRecyclerViewAdapter recyclerViewAdapter = new TimeTableRecyclerViewAdapter(IntercityActivity.this, rowListItem);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
