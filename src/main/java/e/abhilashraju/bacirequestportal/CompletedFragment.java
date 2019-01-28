package e.abhilashraju.bacirequestportal;

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
public class CompletedFragment extends Fragment {

    public static List<PendingListData> completedListData;
    private ListView completedList;
    private SharedPreferences preferences;

    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.completed, container, false);

        preferences = getContext().getSharedPreferences("temp", Context.MODE_PRIVATE);
        completedListData = new ArrayList<>();

        if (completedListData.size() == 0)
            getData();


        completedList = v.findViewById(R.id.completedListView);
        CustomAdapter adapter = new CustomAdapter(getContext(), completedListData);
        completedList.setAdapter(adapter);

        completedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RequestDetailsActivity.class);
                intent.putExtra("PendinLIstobject", completedListData.get(position));
                startActivity(intent);
            }
        });

        return v;
    }

    public void getData() {
        Gson gson = new Gson();
        List<PendingListData> data1 = new ArrayList<>();
        String data = preferences.getString("CompletedListData", "");
        Type type = new TypeToken<List<PendingListData>>() {
        }.getType();
        if (data != null && data.length() != 0) {
            data1 = gson.fromJson(data, type);
            for (int i = 0; i < data1.size(); i++) {
                if (!completedListData.contains(data1.get(i)))
                    completedListData.add(data1.get(i));
            }
        }


    }

}
