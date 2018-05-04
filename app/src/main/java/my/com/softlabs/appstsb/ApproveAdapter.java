package my.com.softlabs.appstsb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Akmal on 7/4/2017.
 */

public class ApproveAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public ApproveAdapter(Context context, int resource) {
        super(context,resource);
    }


    public void add(Approve object) {
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
        ApproveAdapter.ApproveHolder approveHolder;
        if (row == null) {
            //MyActivity.setAppFont(view, mFont);
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_approve, parent, false);
            approveHolder = new ApproveAdapter.ApproveHolder();
            approveHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            approveHolder.tx_status = (TextView) row.findViewById(R.id.tx_status);

            row.setTag(approveHolder);
        } else {
           approveHolder = (ApproveAdapter.ApproveHolder) row.getTag();
        }

        Approve approve = (Approve)this.getItem(position);
        approveHolder.tx_name.setText(approve.getName(position));
        //statusHolder.tx_startdate.setText(status.getStart());
        //statusHolder.tx_enddate.setText(status.getEnd());
        approveHolder.tx_status.setText(approve.getStatus());
        return row;

    }




    static class ApproveHolder
    {
        TextView tx_name,tx_status;
    }

}
