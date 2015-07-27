package belajar.qiwary.contactangular.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import belajar.qiwary.contactangular.datamodel.ListContact;
import belajar.qiwary.contactangular.R;


public class SwipeListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ListContact> contactList;
    private String[] bgColors;

    public SwipeListAdapter(Activity activity, List<ListContact> contactList) {
        this.activity = activity;
        this.contactList = contactList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int location) {
        return contactList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView idnomor = (TextView) convertView.findViewById(R.id.serial);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView email = (TextView) convertView.findViewById(R.id.tv_email);
        TextView number = (TextView) convertView.findViewById(R.id.tv_number);

        idnomor.setText(String.valueOf(contactList.get(position).nourut));
        name.setText(contactList.get(position).name);
        email.setText(contactList.get(position).email);
        number.setText(contactList.get(position).number);

        String color = bgColors[position % bgColors.length];
        idnomor.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }

}