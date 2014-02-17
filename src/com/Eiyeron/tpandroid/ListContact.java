package com.Eiyeron.tpandroid;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.Eiyeron.tpandroid.Adapters.ContactListAdapter;
import com.Eiyeron.tpandroid.SQLHelper.SQLContact;

public class ListContact extends ListActivity {
	ListView listView;
	ArrayList<SQLContact> items;
	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
 
        // 1. pass context and data to the custom adapter
        ContactListAdapter adapter = new ContactListAdapter(this, generateData());
 
        //2. setListAdapter
        setListAdapter(adapter);
    }
 
    private ArrayList<SQLContact> generateData(){
        items = new ArrayList<SQLContact>();
        items.add(new SQLContact(1, "Florian", "DORMONT", "0635511472"));
        items.add(new SQLContact(1, "Meh", "Lol", "0635511472"));
        items.add(new SQLContact(1, "Meh", "Lol", "0635511472"));
 
        return items;
    }
    
    @Override 
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ContactDetail.class);
        intent.putExtra("contact", items.get(position));
        startActivity(intent);
    }
}
