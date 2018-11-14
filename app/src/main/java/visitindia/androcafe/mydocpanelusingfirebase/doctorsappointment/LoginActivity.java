package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import visitindia.androcafe.mydocpanelusingfirebase.R;
import visitindia.androcafe.mydocpanelusingfirebase.model.User;


public class LoginActivity extends AppCompatActivity {

    EditText et_mobile_no;
    EditText et_password;

    Button button_login;

    TextView tvNewUser;

    LinearLayout linearLayoutLogin,linearLayoutLogin2;

    private FirebaseDatabase database;

    public static String MyPref = "MyPref";
    public static String phoneno = "phoneno";
    SharedPreferences sharedPreferences;

    private ProgressDialog progressBar;

    private Handler progressBarbHandler = new Handler();

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(MyPref, MODE_PRIVATE);

        findViewByIds();

        linearLayoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        linearLayoutLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar();
                 button_login.setEnabled(false);
                checkData();
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkData() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        final ArrayList<User> arrayListUser = new ArrayList<>();



        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Log.d("response", "lkvlf");
                    User user = noteDataSnapshot.getValue(User.class);
                    arrayListUser.add(user);

                    if (user.getMobileNo().equals(et_mobile_no.getText().toString())) {
                        if (user.getPassword().equals(et_password.getText().toString())) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(phoneno, et_mobile_no.getText().toString());
                            editor.commit();

                            button_login.setEnabled(true);
                            progressBar.dismiss();
                            flag=1;

                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                if(flag==0)
                {
                    Toast.makeText(LoginActivity.this, "Phone number and password not found", Toast.LENGTH_LONG).show();
                    button_login.setEnabled(true);
                    progressBar.dismiss();;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        for (int i = 0; i < arrayListUser.size(); i++) {
            System.out.println("iiii " + i);
        }
    }

    private void findViewByIds() {

        linearLayoutLogin=findViewById(R.id.linearlayout_login);
        linearLayoutLogin2=findViewById(R.id.linearlayout_login2);

        et_mobile_no = findViewById(R.id.edittext_mobile_no);
        et_password = findViewById(R.id.edittext_password);

        button_login = findViewById(R.id.button_login);

        tvNewUser = findViewById(R.id.textview_new_user);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();

    }

    private void showProgressBar() {
        progressBar = new ProgressDialog(LoginActivity.this);
        progressBar.setMessage("Login ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(false);
        progressBar.show();
        final int[] progressBarStatus = {0};

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus[0] < 100) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus[0]);
                        }
                    });
                }

            }
        }).start();

    }

    protected void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(LoginActivity.this.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}