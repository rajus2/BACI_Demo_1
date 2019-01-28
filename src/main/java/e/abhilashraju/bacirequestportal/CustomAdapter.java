package e.abhilashraju.bacirequestportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<PendingListData> pendingListData;
    TextView accNum,reqType,reqDesc;
    Context context;

    public CustomAdapter(Context context,List<PendingListData>pendingListData ){
        this.pendingListData=pendingListData;
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getCount() {
        return pendingListData.size();
    }

    @Override
    public Object getItem(int position) {
        return pendingListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= mInflater.inflate(R.layout.pending_list,null);
        accNum=(TextView)convertView.findViewById(R.id.acc_num_textView);
        reqType=(TextView)convertView.findViewById(R.id.req_type_textView);
        reqDesc=(TextView)convertView.findViewById(R.id.req_desc_textView);

        accNum.setText(accNum.getText() + pendingListData.get(position).getAcc_num());
        reqType.setText(reqType.getText() + pendingListData.get(position).getReq_type());
        reqDesc.setText(reqDesc.getText() + pendingListData.get(position).getReq_desc());
        return convertView;
    }

}
