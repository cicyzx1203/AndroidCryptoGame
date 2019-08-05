package edu.gatech.seclass.crypto6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class PlayerAdapter extends ArrayAdapter<Player> {
    Application application = DataStore.getApplication();

    int mResource;
    private Context mContext;

    public PlayerAdapter(Context context, int resource, List<Player> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String username = getItem(position).getUsername();
        int points = getItem(position).getPoints();
        int attempts = getItem(position).getAttempts();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView txtUsername = (TextView) convertView.findViewById(R.id.txtListUsername);
        TextView txtAttempts = (TextView) convertView.findViewById(R.id.txtListAttempts);
        TextView txtPoints = (TextView) convertView.findViewById(R.id.txtListPoints);

        txtUsername.setText(username);
        txtAttempts.setText(String.valueOf(points));
        txtPoints.setText(String.valueOf(attempts));
        

        return convertView;
    }
}