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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pe.dwkim.hapduk_terminal.Model.Destination;
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
    private boolean isListEmpty;

    private List<String> expandableListGroupName = new ArrayList<String>();
    private List<Destination> destinations = null;
    private HashMap<String, List<Destination>> expandableListChild = new HashMap<String, List<Destination>>();

    private TextView destinationTextView;
    private TextView requiredTextView;
    private TextView childFeeTextView;
    private TextView teenagerFeeTextView;
    private TextView adultFeeTextView;

    private AlertDialog dialog;

    private GridLayoutManager gridLayoutManager;

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

        destinationTextView = (TextView)findViewById(R.id.text_destination);

        setIsListEmpty(true);
        initDestinationList();
    }

    private void initDestinationList(){
        getIntercityDestinations();
    }

    public void getIntercityDestinations(){
        Call<List<Destination>> call = HapdukTerminalClient.get().getIntercityDestinations();

        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                destinations = response.body();
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                showToast(getString(R.string.failed_to_server_communication));
                Log.d("Hapduk_terminal : ", "IntercityActivity.getIntercityDestination ERROR : " + t.getMessage());
            }
        });
    }

    public void clickSelectDestinationButton(View view){
        if(destinations != null && destinations.size() > 0) {
            showSelectDestinationDialog();
        }
    }

    public void showSelectDestinationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("도착지를 선택 해주세요");

        if (expandableListGroupName.size() == 0) {
                expandableListGroupName.add(destinations.get(0).getDivisionName());
        }

        for (int a = 0; destinations.size() > a; a++) {
            String divisionName = destinations.get(a).getDivisionName();

            for (int b = 0; expandableListGroupName.size() > b; b++) {
                if (!expandableListGroupName.contains(divisionName)) {
                    expandableListGroupName.add(divisionName);
                }
            }
        }

        for (int a = 0; expandableListGroupName.size() > a; a++) {
            List<Destination> childList = new ArrayList<Destination>();

            for (int b = 0; destinations.size() > b; b++) {
                if (expandableListGroupName.get(a).equals(destinations.get(b).getDivisionName())) {
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
                if (expandableListGroupName.size() > 0
                        && expandableListGroupName.size() >= groupPosition) {
                    List<Destination> tmpDestinationList = expandableListChild.get(expandableListGroupName.get(groupPosition));

                    if (tmpDestinationList.size() > 0
                            && tmpDestinationList.size() >= childPosition) {
                        if (tmpDestinationList.get(childPosition) != null) {
                            getRoute(tmpDestinationList.get(childPosition).getDestinationId());
                        }
                    }
                }

                return false;
            }
        });

        builder.setView(expandableListView);
        dialog = builder.create();
        dialog.show();
    }

    private void getRoute(final int destinationId){
        Call<List<StopInfo>> call = HapdukTerminalClient.get().getRoute(destinationId);

        call.enqueue(new Callback<List<StopInfo>>() {
            @Override
            public void onResponse(Call<List<StopInfo>> call, Response<List<StopInfo>> response) {
                List<StopInfo> stopInfoList = response.body();
                if (stopInfoList != null && stopInfoList.size() > 0) {
                    setIsListEmpty(false);

                    setRecyclerView(stopInfoList);
                    setDestinationInfo(stopInfoList, destinationId);
                } else {
                    setIsListEmpty(true);
                }
            }

            @Override
            public void onFailure(Call<List<StopInfo>> call, Throwable t) {
                showToast(getString(R.string.failed_to_server_communication));
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
                    stopInfo.getSequence(),
                    stopInfo.getIs_last_item()));
        }

        gridLayoutManager = new GridLayoutManager(IntercityActivity.this, 1);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.time_table_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        TimeTableRecyclerViewAdapter recyclerViewAdapter = new TimeTableRecyclerViewAdapter(IntercityActivity.this, rowListItem);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setDestinationInfo(List<StopInfo> stopInfoList, int destinationId){
        StopInfo destinationInfo = null;

        for(int i=0; stopInfoList.size() > i; i++){
            if(stopInfoList.get(i).getId() == destinationId){
                destinationInfo = stopInfoList.get(i);
            }
        }

        if(destinationInfo != null) {
            requiredTextView = (TextView)findViewById(R.id.text_required);
            requiredTextView.setText(destinationInfo.getRequired());

            childFeeTextView = (TextView)findViewById(R.id.text_child);
            childFeeTextView.setText(destinationInfo.getChildString());

            teenagerFeeTextView = (TextView)findViewById(R.id.text_teenager);
            teenagerFeeTextView.setText(destinationInfo.getTeenagerString());

            adultFeeTextView = (TextView)findViewById(R.id.text_adult);
            adultFeeTextView.setText(destinationInfo.getAdultString());

            destinationTextView.setText(destinationInfo.getName());
        }
        else{
            setIsListEmpty(true);
        }
    }

    private void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void setIsListEmpty(boolean isListEmpty) {
        RelativeLayout destinationInfoArea = (RelativeLayout) findViewById(R.id.destination_info_area);
        View verticalLine2 = (View) findViewById(R.id.vertical_line2);
        LinearLayout recyclerViewHeader = (LinearLayout) findViewById(R.id.recyclerView_header);
        View verticalLine3 = (View) findViewById(R.id.vertical_line3);
        RecyclerView timeTableRecyclerView = (RecyclerView) findViewById(R.id.time_table_recyclerView);
        RelativeLayout listEmptyLayout = (RelativeLayout) findViewById(R.id.list_empty_layout);

        if(isListEmpty) {
            destinationInfoArea.setVisibility(View.GONE);
            verticalLine2.setVisibility(View.GONE);
            recyclerViewHeader .setVisibility(View.GONE);
            verticalLine3.setVisibility(View.GONE);
            timeTableRecyclerView.setVisibility(View.GONE);

            listEmptyLayout.setVisibility(View.VISIBLE);

            destinationTextView.setText("");

            this.isListEmpty = isListEmpty;
        }
        else{
            destinationInfoArea.setVisibility(View.VISIBLE);
            verticalLine2.setVisibility(View.VISIBLE);
            recyclerViewHeader .setVisibility(View.VISIBLE);
            verticalLine3.setVisibility(View.VISIBLE);
            timeTableRecyclerView.setVisibility(View.VISIBLE);

            listEmptyLayout.setVisibility(View.GONE);

            this.isListEmpty = isListEmpty;
        }
    }
}
