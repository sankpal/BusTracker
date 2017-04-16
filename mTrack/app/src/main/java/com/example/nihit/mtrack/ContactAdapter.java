package com.example.nihit.mtrack;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nihit on 19-02-2017.
 */
public class ContactAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }



    public void add(Info object)
    {
        super.add(object);
        list.add(object);
    }


    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_id = (TextView) row.findViewById(R.id.id);
            contactHolder.tx_time = (TextView) row.findViewById(R.id.time1);
            contactHolder.tx_busno = (TextView) row.findViewById(R.id.bus_no);
            contactHolder.tx_id.setTextColor(Color.WHITE);
            contactHolder.tx_time.setTextColor(Color.WHITE);
            contactHolder.tx_busno.setTextColor(Color.WHITE);
            contactHolder.tx_id.setTextSize(15);
            contactHolder.tx_time.setTextSize(15);
            contactHolder.tx_busno.setTextSize(15);
            if(position %2 == 1)
            {

                row.setBackgroundColor(Color.parseColor("#525A46"));
            }
            else
            {

                row.setBackgroundColor(Color.parseColor("#212121"));
            }

            row.setTag(contactHolder);
        }
        else
        {
            contactHolder = (ContactHolder) row.getTag();
        }
        Info info = (Info) this.getItem(position);
        contactHolder.tx_id.setText(info.getId());
        contactHolder.tx_time.setText(info.getTime());
        contactHolder.tx_busno.setText(info.getBus_no());




        return row;
    }

    static class ContactHolder
    {
        TextView tx_id,tx_time,tx_busno;
    }
}
