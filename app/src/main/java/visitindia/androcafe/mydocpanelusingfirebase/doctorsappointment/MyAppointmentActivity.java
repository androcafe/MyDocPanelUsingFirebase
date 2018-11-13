package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment;

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

import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.adapter.MyAppointmentAdapter;
import visitindia.androcafe.mydocpanelusingfirebase.model.MyAppointment;

public class MyAppointmentActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    MyAppointmentAdapter myAppointmentAdapter;

    MyAppointment myAppointment;

    ArrayList<MyAppointment> arrayList_my_appointment=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("My Appointment");

        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyAppointmentActivity.this);
       // linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        database.child("myappointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MyAppointment myAppointment1;
                arrayList_my_appointment.clear();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Log.d("response","appointment");
                    myAppointment1=noteDataSnapshot.getValue(MyAppointment.class);

                    MyAppointment appointment=new MyAppointment(myAppointment1.getDoctor(),myAppointment1.getName(),myAppointment1.getAge(),myAppointment1.getTreatment(),myAppointment1.getDate(),myAppointment1.getTime(),myAppointment1.getStatus());
                    System.out.println(""+myAppointment1.getDoctor());
                    arrayList_my_appointment.add(appointment);
                }

                myAppointmentAdapter=new MyAppointmentAdapter(MyAppointmentActivity.this,arrayList_my_appointment);
                recyclerView.setAdapter(myAppointmentAdapter);

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
                Intent intent=new Intent(MyAppointmentActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return  false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent=new Intent(MyAppointmentActivity.this,HomeActivity.class);
        startActivity(intent);

    }
}
