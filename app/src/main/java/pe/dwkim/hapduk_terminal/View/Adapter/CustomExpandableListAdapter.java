package pe.dwkim.hapduk_terminal.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import pe.dwkim.hapduk_terminal.Model.Destination;
import pe.dwkim.hapduk_terminal.R;

/**
 * Created by dwkim on 16. 5. 26..
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<String> expandableListGroupTitle;
    private HashMap<String, List<Destination>> expandableListChild;

    public CustomExpandableListAdapter(Context context,
                                       List<String> expandableListGroupTitle,
                                       HashMap<String, List<Destination>> expandableListDetail) {
        this.context = context;
        this.expandableListGroupTitle = expandableListGroupTitle;
        this.expandableListChild = expandableListDetail;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListChild
                .get(this.expandableListGroupTitle.get(groupPosition))
                .get(childPosition).getDestinationName();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        final String expandedGroupText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_item, null);
        }

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedGroupText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListChild.get(this.expandableListGroupTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListGroupTitle.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_item_group, null);
        }

        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);

        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return this.expandableListGroupTitle.size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
