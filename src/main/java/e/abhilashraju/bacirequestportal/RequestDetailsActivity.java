package e.abhilashraju.bacirequestportal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class RequestDetailsActivity extends AppCompatActivity {

    TextView reqType;
    TextView reqDesc;
    TextView accNum;
    TextView compl;
    Button declineBtn, approveBtn;
    PendingListData data;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        preferences = getApplicationContext().getSharedPreferences("temp", Context.MODE_PRIVATE);
        reqDesc = findViewById(R.id.req_det_req_desc);
        reqType = findViewById(R.id.req_det_type);
        accNum = findViewById(R.id.req_det_accNum);
        compl = findViewById(R.id.req_det_status);
        declineBtn = findViewById(R.id.declineBtn);
        approveBtn = findViewById(R.id.apprBtn);

        onClickLister();

        data = getClickedData();
        reqType.setText("Request Type:  " + data.getReq_type());
        reqDesc.setText("Request Description: " + data.getReq_desc());
        accNum.setText("Account Number: " + data.getAcc_num());

        if (data.isCompleted()) {
            compl.setText("Status: Completed ");
            approveBtn.setVisibility(View.GONE);
            declineBtn.setText("BACK");
        } else
            compl.setText("Status: Pending");


    }

    private PendingListData getClickedData() {
        PendingListData data = (PendingListData) getIntent().getSerializableExtra("PendinLIstobject");
        return data;
    }

    private void onClickLister() {
        declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompletedList();
                Toast.makeText(getApplicationContext(),"Approved Succesfully",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    public void updateCompletedList() {
        data.setCompletedStatus(true);
        CompletedFragment.completedListData.add(data);
        setSharedPreference();
        updatePendingSharedPreference();

    }

    private void setSharedPreference() {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String completedDataList = gson.toJson(CompletedFragment.completedListData);
        editor.putString("CompletedListData", completedDataList);
        editor.commit();

    }

    private void updatePendingSharedPreference() {
        if (PendingFragment.dataList.contains(data))
            PendingFragment.dataList.remove(data);

        for (int i = 0; i < PendingFragment.dataList.size(); i++) {
            PendingListData currentData = PendingFragment.dataList.get(i);
            if (currentData.getAcc_num().equals(data.getAcc_num()))
                PendingFragment.dataList.remove(currentData);
        }


        Log.d("temp", "updatePendingSharedPreference: " + PendingFragment.dataList.size());
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String completedDataList = gson.toJson(PendingFragment.dataList);
        editor.putString("PendingDataList", completedDataList);
        editor.commit();

    }

}
