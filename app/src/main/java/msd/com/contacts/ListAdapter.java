package msd.com.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johns on 10/12/2015.
 */
public class ListAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView Name, Mobile, Email, Latitude, Longitude, AddressName, Address;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.Name = (TextView)row.findViewById(R.id.text_name);
            layoutHandler.Mobile = (TextView)row.findViewById(R.id.text_mobile);
            layoutHandler.Email = (TextView)row.findViewById(R.id.text_email);
            layoutHandler.Latitude = (TextView)row.findViewById(R.id.text_latitude);
            layoutHandler.Longitude = (TextView)row.findViewById(R.id.text_Longitude);
            layoutHandler.AddressName = (TextView)row.findViewById(R.id.text_AddressName);
            layoutHandler.Address = (TextView)row.findViewById(R.id.text_Address);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler)row.getTag();
        }
        Data data = (Data)this.getItem(position);

        Log.e("listadapter Operations", "position: " + position);


        Log.e("listadapter Operations", "name: " + data.getName());
        Log.e("listadapter Operations", "latitude: " + data.getLatitude());


        Log.e("listadapter Operations", "latitude object reference itself: " + layoutHandler.Latitude);


        layoutHandler.Latitude.setText("latitude");

        //layoutHandler.Name.setText

        layoutHandler.Name.setText(data.getName());
        layoutHandler.Mobile.setText(data.getMobile());
        layoutHandler.Email.setText(data.getEmail());
        layoutHandler.Latitude.setText(""+data.getLatitude());
        layoutHandler.Longitude.setText(""+data.getLongitude());
        layoutHandler.AddressName.setText(data.getAddressName());
        layoutHandler.Address.setText(data.getAddress());

        return row;
    }
}
