package com.example.diplom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class Adapter extends BaseAdapter implements ListAdapter {
    private List<Note> mValues;
    private Context mContext;

    public Adapter(List<Note> values, Context applicationContext) {
        mValues = values;
        mContext = applicationContext;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_layout, null);
        }
        Note note = mValues.get(position);
        TextView title = convertView.findViewById(R.id.TextOne);
        TextView subtitle = convertView.findViewById(R.id.TextTwo);
        TextView date = convertView.findViewById(R.id.TextThree);
        title.setText(note.getHeading());
        subtitle.setText(note.getBodyOfNote());
        date.setText(DateUtil.DateToString(note.getDate()));
        Date temp = note.getDate();
        Date now = DateUtil.clockReset(new Date());

        if (temp != null) {
            if (DateUtil.clockReset(temp).toString().equals(now.toString())) {
                convertView.setBackgroundColor(Color.YELLOW);
            } else if (temp.before(now)) {
                convertView.setBackgroundColor(Color.RED);
            } else {
                convertView.setBackground(null);
            }

        }
        return convertView;
    }
}
