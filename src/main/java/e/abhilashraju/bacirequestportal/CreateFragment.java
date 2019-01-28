package e.abhilashraju.bacirequestportal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class

CreateFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    List<PendingListData> pendingListData;
    SharedPreferences preferences;
    private Spinner spinner;
    private Button submit;
    private EditText description, accountNumber;
    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create, container, false);
        preferences = getContext().getSharedPreferences("temp", Context.MODE_PRIVATE);
        spinner = view.findViewById(R.id.spinner);
        submit = view.findViewById(R.id.submit);
        description = view.findViewById(R.id.additionalrequest);
        accountNumber = view.findViewById(R.id.accountNum);
        pendingListData = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.label_string, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerData = spinner.getSelectedItem().toString();
                String commentDetails = description.getText().toString();
                String accNum = accountNumber.getText().toString();

                if (accNum.equals("")) {
                    Toast.makeText(getContext(), "Kindly Enter Your Account Number", Toast.LENGTH_LONG).show();

                } else if (commentDetails.equals("")) {
                    Toast.makeText(getContext(), "Kindly Enter the Comments Details", Toast.LENGTH_LONG).show();

                } else if (spinnerData.equals("Select one")) {
                    Toast.makeText(getContext(), "Kindly Select one Request Type", Toast.LENGTH_LONG).show();

                } else {

                    PendingListData data = new PendingListData(spinnerData, commentDetails, accNum, false);
                    PendingFragment.dataList.add(data);
                    setSharedPreference();
                    Toast.makeText(getContext(), "Successfully Submitted", Toast.LENGTH_LONG).show();

                    accountNumber.setText("");
                    description.setText("");
                    spinner.setSelection(0);
                }




            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setSharedPreference() {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String pendinDataList = gson.toJson(PendingFragment.dataList);
        editor.putString("PendingDataList", pendinDataList);
        editor.commit();

    }



    public interface DataListenter {
        void onInputSent(String str);
    }

}
