package com.example.sayandeep.fooddelivery;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 19-04-2018.
 */

public class CustomListAdapter extends ArrayAdapter<ListItems> {

    List<ListItems> list;
    Context context;

    int resource;

   public CustomListAdapter(Context context, int resource, List<ListItems> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView username = view.findViewById(R.id.textView_username);
        TextView location = view.findViewById(R.id.textview_location);
        Button btn_callnow = view.findViewById(R.id.call_now);
        ListItems list1 = list.get(position);

        username.setText(list1.getUsernameName());
        location.setText(list1.getLocation());

        btn_callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }



}
