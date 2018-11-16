package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import visitindia.androcafe.mydocpanelusingfirebase.R;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.HomeActivity;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.adapter.FindDocAdapter;
import visitindia.androcafe.mydocpanelusingfirebase.model.Doctor;

public class FindDoctorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Doctor doctor;

    ArrayList<Doctor> arrayList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FindDocAdapter findDocAdapter;


    ArrayList<Doctor> arrayList1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctors);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Find Doctors");

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindDoctorsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        final ArrayList<Doctor> arrayListUser=new ArrayList<>();

        database.child("doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Log.d("response","lkvlf");

                    doctor=noteDataSnapshot.getValue(Doctor.class);

                    Doctor doctor1=new Doctor(doctor.getName(),doctor.getPhoneno(),doctor.getImg(),doctor.getSpecialize());

                    Log.d("response",doctor1.getName());
                    arrayListUser.add(doctor1);

                }

                Log.d("response",""+arrayListUser.size());
                findDocAdapter=new FindDocAdapter(FindDoctorsActivity.this,arrayListUser);
                recyclerView.setAdapter(findDocAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(FindDoctorsActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return  false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent=new Intent(FindDoctorsActivity.this,HomeActivity.class);
        startActivity(intent);

    }
}
