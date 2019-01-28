package e.abhilashraju.bacirequestportal;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingFragment extends Fragment {

    public static List<PendingListData> dataList;
    ListView pendingList;
    SharedPreferences preferences;

    public PendingFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        preferences = getContext().getSharedPreferences("temp", Context.MODE_PRIVATE);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pending, container, false);
        PendingListData pd1 = new PendingListData("temp", "temp1", "1213", false);
        PendingListData pd2 = new PendingListData("temp", "temp2", "12121", false);
        PendingListData pd3 = new PendingListData("temp", "temp3", "12133", false);
        dataList = new ArrayList<>();

        if (dataList.size() == 0)
            getData();

        pendingList = v.findViewById(R.id.pendinListView);
        CustomAdapter adapter = new CustomAdapter(getContext(), dataList);
        pendingList.setAdapter(adapter);

        pendingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RequestDetailsActivity.class);
                intent.putExtra("PendinLIstobject", dataList.get(position));
                startActivity(intent);
            }
        });
        return v;
    }

    public void updateData(String str) {
        Gson gson = new Gson();
        List<PendingListData> data1 = new ArrayList<>();
        Type type = new TypeToken<List<PendingListData>>() {
        }.getType();

        data1 = gson.fromJson(str, type);

        for (int i = 0; i < data1.size(); i++) {
            if (!dataList.contains(data1.get(i)))
                dataList.add(data1.get(i));
            else
                dataList.remove(data1.get(i));
        }
    }

    public void getData() {
        Gson gson = new Gson();
        List<PendingListData> data1 = new ArrayList<>();
        String data = preferences.getString("PendingDataList", "");
        Type type = new TypeToken<List<PendingListData>>() {
        }.getType();

        if(data!=null&& data.length()!=0){
            data1 = gson.fromJson(data, type);

            for (int i = 0; i < data1.size(); i++) {
                if (dataList != null && !dataList.contains(data1.get(i)))
                    dataList.add(data1.get(i));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}

