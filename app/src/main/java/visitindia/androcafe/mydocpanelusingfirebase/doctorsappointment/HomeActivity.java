package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import visitindia.androcafe.mydocpanelusingfirebase.R;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.adapter.ViewPagerAdapter;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.home.FindDoctorsActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout btn_find_doc,btn_fav_doc,btn_my_appointment;

    ViewPager viewPager;
    int images[]={R.raw.doc1,R.raw.doc2,R.raw.doc3};
    TextView textViewPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Home");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        textViewPhoneNo=headerView.findViewById(R.id.phoneno);
        SharedPreferences sharedPreferences=getSharedPreferences(LoginActivity.MyPref,MODE_PRIVATE);
        String phoneno=sharedPreferences.getString("phoneno",null);
        textViewPhoneNo.setText(""+phoneno);

        viewPager =findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(HomeActivity.this,images));

        btn_find_doc=findViewById(R.id.find_doc);
        btn_fav_doc=findViewById(R.id.fav_doc);
        btn_my_appointment=findViewById(R.id.my_booking);

        btn_find_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, FindDoctorsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_my_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,MyAppointmentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
           // Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            //startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_fav) {

        } else if (id == R.id.nav_boobking_status) {
            Intent intent=new Intent(HomeActivity.this,MyAppointmentActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_sign_out) {
            SharedPreferences sharedPreferences=getSharedPreferences(LoginActivity.MyPref,MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.commit();

            finish();
            Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
