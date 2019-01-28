package e.abhilashraju.bacirequestportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private RelativeLayout relativeLayout;
    private Button signUp;
    private EditText userName, password;
    private String userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkBox = findViewById(R.id.member);
        relativeLayout = findViewById(R.id.visibility);
        signUp = findViewById(R.id.signUp);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    signUp.setVisibility(View.GONE);

                } else {
                    userName.setText("");
                    password.setText("");
                    relativeLayout.setVisibility(View.GONE);
                    signUp.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void signUP(View view) {
        Intent intent = new Intent(this, NewUserLogInPage.class);
        startActivity(intent);
    }

    public void signIn(View view) {

        userDetails = userName.getText().toString();
        String userPassword = password.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (userDetails.equals("")) {
            Toast.makeText(getApplicationContext(), "Kindly Enter Your User Name ", Toast.LENGTH_LONG).show();
        } else if (userPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Kindly Enter Your Password", Toast.LENGTH_LONG).show();
        } else if (userDetails.matches(emailPattern)) {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), userDetails + "  Has Successfully Signed In", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), userDetails + " Not a Valid Email ID", Toast.LENGTH_LONG).show();
        }
    }
}
