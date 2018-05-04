package my.com.softlabs.appstsb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StatusAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public StatusAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Status object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
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
        StatusHolder statusHolder;
        if (row == null) {
            //MyActivity.setAppFont(view, mFont);
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            statusHolder = new StatusHolder();
            statusHolder.tx_applydate = (TextView) row.findViewById(R.id.tx_applydate);
            //statusHolder.tx_startdate = (TextView) row.findViewById(R.id.tx_startdate);
            //statusHolder.tx_enddate = (TextView) row.findViewById(R.id.tx_enddate);
            statusHolder.tx_status = (TextView) row.findViewById(R.id.tx_status);

            //final Typeface tvFont = Typeface.createFromAsset(assetManager, "OPTIMA.TTF");
            //statusHolder.tx_applydate.setTypeface(tvFont);
            //statusHolder.tx_applydate.setTextColor(Color.BLACK);

            row.setTag(statusHolder);
        } else {
            statusHolder = (StatusHolder) row.getTag();
        }

        Status status = (Status)this.getItem(position);
        statusHolder.tx_applydate.setText(status.getApply(position));
        //statusHolder.tx_startdate.setText(status.getStart());
        //statusHolder.tx_enddate.setText(status.getEnd());
        statusHolder.tx_status.setText(status.getStat());
        return row;

    }




    static class StatusHolder
    {
        TextView tx_applydate,tx_startdate,tx_enddate,tx_status;
    }
}

