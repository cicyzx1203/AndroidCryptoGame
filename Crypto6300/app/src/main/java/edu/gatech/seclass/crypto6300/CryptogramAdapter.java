package edu.gatech.seclass.crypto6300;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class CryptogramAdapter extends ArrayAdapter<Cryptogram> {
    Application application = DataStore.getApplication();

    int mResource;
    private Context mContext;

    public CryptogramAdapter(Context context, int resource, List<Cryptogram> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getTitle();

        float percentageWins;
        float div = getItem(position).getNumberOfCompletedGame();
        div = div == 0 ? 1 : div;
        try {
            percentageWins = Math.round(getItem(position).getNumberOfWins()* 1000 / div)  / 10f;
        }
        catch (Exception e){
            percentageWins = 0;
        }

        int completed = getItem(position).getNumberOfCompletedGame();
        String creator = getItem(position).getCreator().getUsername();



        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textStatTitle);
        TextView txtCompleted = (TextView) convertView.findViewById(R.id.textStatCompleted);
        TextView txtCreator = (TextView) convertView.findViewById(R.id.textStatCreator);
        TextView txtPercentageWins = (TextView) convertView.findViewById(R.id.textStatPercentageWins);

        SpannableString content = new SpannableString(title);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txtTitle.setText(content);

        txtCreator.setText(creator);
        txtPercentageWins.setText(String.valueOf(percentageWins));
        txtCompleted.setText(String.valueOf(completed));

        return convertView;
    }


}
