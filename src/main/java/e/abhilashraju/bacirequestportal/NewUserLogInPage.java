package e.abhilashraju.bacirequestportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserLogInPage extends AppCompatActivity {

    private EditText userName, mobileNumber, address, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_log_in_page);

        userName = findViewById(R.id.emailaddress);
        mobileNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.postaladdress);
        password = findViewById(R.id.password);
    }

    public void signUP(View view) {

        String sUserDetails = userName.getText().toString();
        String sMobileNumber = mobileNumber.getText().toString();
        String sAddress = address.getText().toString();
        String sPassword = password.getText().toString();
        Log.d("Test", sUserDetails);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (sUserDetails.equals("")) {

            Toast.makeText(getApplicationContext(), "Kindly Enter all the detials", Toast.LENGTH_LONG).show();
        } else if (sMobileNumber.equals("")) {

            Toast.makeText(getApplicationContext(), "Kindly Enter all the detials", Toast.LENGTH_LONG).show();
        } else if (sAddress.equals("")) {

            Toast.makeText(getApplicationContext(), "Kindly Enter all the detials", Toast.LENGTH_LONG).show();
        } else if (sPassword.equals("")) {

            Toast.makeText(getApplicationContext(), "Kindly Enter all the detials", Toast.LENGTH_LONG).show();
        } else if (sUserDetails.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), sUserDetails + "  ---  New User Account is Successfully Created", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {

            Toast.makeText(getApplicationContext(), sUserDetails + " Not a Valid Email ID", Toast.LENGTH_LONG).show();

        }

    }
}
