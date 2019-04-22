package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectsAdapter extends BaseAdapter{
    Context context;
    private LayoutInflater inflater;

    private ArrayList<SubjectClass> menuData=new ArrayList<>();

    public SubjectsAdapter(Context context, ArrayList<SubjectClass>duties)
    {
        this.context = context;
        this.menuData=duties;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return menuData.size();
    }

    @Override
    public Object getItem(int i) {
        return menuData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderShowAllBooking holder;
        if (convertView == null) {

            holder = new ViewHolderShowAllBooking();
            convertView = inflater.inflate(R.layout.show_subject_list_row_layout, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.duty_location_textView_guards_duties_list_row_layout);
            holder.start = (TextView) convertView.findViewById(R.id.to_change_textView_guards_duties_list_row_layout);
            holder.end = (TextView) convertView.findViewById(R.id.from_change_textView_guards_duties_list_row_layout);

            holder.location = (TextView) convertView.findViewById(R.id.one_change);

            holder.ins = (TextView) convertView.findViewById(R.id.two_change);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderShowAllBooking) convertView.getTag();
        }
        /** THIS ARRAY IS FOR REST OF THE DETAILS*/
        holder.title.setText(menuData.get(position).getTitle());
        holder.start.setText(menuData.get(position).getStarttime());
        holder.end.setText(menuData.get(position).getEndTime());
        holder.location.setText(menuData.get(position).getLocation());
        holder.ins.setText(menuData.get(position).getInstructor());


        return convertView;
    }
    static class ViewHolderShowAllBooking
    {
        TextView title;
        TextView start;
        TextView end;
        TextView location;
        TextView ins;
    }

}
