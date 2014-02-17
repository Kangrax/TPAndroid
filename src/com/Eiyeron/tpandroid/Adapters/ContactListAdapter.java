package com.Eiyeron.tpandroid.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Eiyeron.tpandroid.R;
import com.Eiyeron.tpandroid.SQLHelper.SQLContact;

public class ContactListAdapter extends ArrayAdapter<SQLContact> {
	
	private final Context context;
    private final ArrayList<SQLContact> itemsArrayList;
    
	public ContactListAdapter	(Context context, ArrayList<SQLContact> itemsArrayList) {
		 
        super(context, R.layout.activity_list_contact_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	 
        // 1. Create inflater 
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.activity_list_contact_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameView = (TextView) rowView.findViewById(R.id.contact_item_name);
        TextView numberView = (TextView) rowView.findViewById(R.id.contact_item_number);

        // 4. Set the text for textView 
        nameView.setText(itemsArrayList.get(position).getFirst_name() + " " + itemsArrayList.get(position).getLast_name());
        numberView.setText(itemsArrayList.get(position).getPhone_number());

        // 5. retrn rowView
        return rowView;
    }
}
