package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import visitindia.androcafe.mydocpanelusingfirebase.R;
import visitindia.androcafe.mydocpanelusingfirebase.model.User;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;

    EditText etPhone;
    EditText etPassword;
    EditText etConfirmPassword;

    int flag=0;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewByIds();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPhone.getText().toString().length()==10)
                {
                    flag=0;
                }
                else
                {
                    etPhone.setError("Insert Valid Phone No");
                    flag=1;
                }

                if(etPassword.getText().toString().length()<6 || etPassword.getText().toString().length()>12)
                {
                    etPassword.setError("Password must contain 6 to 12 characters");
                    flag=1;
                }
                else
                {
                    flag=0;
                }

                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                {
                    flag=0;
                }
                else
                {
                    flag=1;
                    etConfirmPassword.setError("Password is not matching");
                }


                if(flag==0)
                {
                    DatabaseReference mRef=FirebaseDatabase.getInstance().getReference("users");

                    // Creating new user node, which returns the unique key value
                    // new user node would be /users/$userid/
                    String userId = mRef.push().getKey();

                    // creating user object
                    User user = new User(etPhone.getText().toString(), etPassword.getText().toString());

                    // pushing user to 'users' node using the userId
                    mRef.child(userId).setValue(user);

                    Toast.makeText(RegisterActivity.this,"Account created successfully",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void findViewByIds() {

        etPhone=findViewById(R.id.edittext_mobile_no);
        etPassword=findViewById(R.id.edittext_password);
        etConfirmPassword=findViewById(R.id.edittext_confirm_password);

        btnRegister=findViewById(R.id.button_register);
    }
}
