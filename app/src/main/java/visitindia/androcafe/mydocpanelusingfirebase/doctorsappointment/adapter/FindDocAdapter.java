package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;
import visitindia.androcafe.mydocpanelusingfirebase.R;
import de.hdodenhof.circleimageview.CircleImageView;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.DoctorInterface;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.home.FindDoctorsActivity;
import visitindia.androcafe.mydocpanelusingfirebase.model.Doctor;
import visitindia.androcafe.mydocpanelusingfirebase.model.MyAppointment;

public class FindDocAdapter extends RecyclerView.Adapter<FindDocAdapter.MyViewHolder> implements DoctorInterface {

    ArrayList<Doctor> arrayList;
    Context context;

    LayoutInflater inflater;


    ArrayAdapter<String> arrayAdapterTime;
    ArrayAdapter<String> arrayAdapterTreatment;

    private FirebaseDatabase database;

    String Time="",Treatment="";

    public FindDocAdapter(FindDoctorsActivity findDoctorsActivity, ArrayList<Doctor> arrayList) {
        this.context=findDoctorsActivity;
        this.arrayList=arrayList;

        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=inflater.inflate(R.layout.find_doctor_row,null);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        System.out.println("bind");
         holder.textView_name.setText(" "+arrayList.get(position).getName());
         holder.mobileno.setText("Phone No : "+arrayList.get(position).getPhoneno());
        // holder.textView_spec.setText("Specialize : "+arrayList.get(position).getSpec());

        holder.imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imageViewFav.setImageResource(R.drawable.selectesd_fav);
            }
        });

        Picasso.with(context).load(arrayList.get(position).getImg()).fit().error(R.raw.doctor).into(holder.circleImageView);


         holder.btnCall.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent_call=new Intent(Intent.ACTION_DIAL);
                 intent_call.setData(Uri.parse("tel:"+arrayList.get(position).getPhoneno()));
                 context.startActivity(intent_call);
             }
         });

         holder.btnBookAppointment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openDialogForAppointment(position,holder.textView_name.getText().toString());
             }
         });


    }

    private void openDialogForAppointment(int position, final String doctorName) {

        final Dialog dialog=new Dialog(context, R.style.ThemeOverlay_AppCompat);

        LayoutInflater inflater1=LayoutInflater.from(context);
        View view1=inflater1.inflate(R.layout.dialog_book_appointment,null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(view1);

        dialog.setCancelable(true);


        RelativeLayout relativeLayout=dialog.findViewById(R.id.relative_book_appointment);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });
        final EditText editText_name=dialog.findViewById(R.id.edittext_name);
        final EditText editText_age=dialog.findViewById(R.id.edittext_age);
        final EditText editText_date=dialog.findViewById(R.id.edittext_date);

        Spinner spinner_time=dialog.findViewById(R.id.spinner_time);
        Spinner spinner_treatment=dialog.findViewById(R.id.spinner_treatment);


        arrayAdapterTime=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,time);
        spinner_time.setAdapter(arrayAdapterTime);

        arrayAdapterTreatment=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,treatment);
        spinner_treatment.setAdapter(arrayAdapterTreatment);

        Button button_cancel=dialog.findViewById(R.id.btn_cancel);
        final Button button_submit=dialog.findViewById(R.id.btn_submit);

        editText_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int mYear, mMonth, mDay;
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    // date .getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    editText_date.setText( (monthOfYear + 1) + "-"+dayOfMonth + "-" + year);


                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePickerDialog.show();
                }
            }
        });



        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Time=time[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Time="";
                hideKeyboard(adapterView);
            }
        });

        spinner_treatment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideKeyboard(view);
                Treatment=treatment[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Treatment="";
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard(view);
                dialog.dismiss();
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                button_submit.setEnabled(false);

                int flag=0;

                if(editText_name.getText().toString().equals(""))
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Enter valid name",Toast.LENGTH_LONG).show();
                }

                if(editText_age.getText().toString().equals(""))
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Enter valid age",Toast.LENGTH_LONG).show();
                }

                if(Integer.parseInt(editText_age.getText().toString())<0||Integer.parseInt(editText_age.getText().toString())>100)
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Enter valid age",Toast.LENGTH_LONG).show();
                }

                if(Treatment.equals("Select Treatment")||Treatment.equals(""))
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Select Treatment",Toast.LENGTH_LONG).show();
                }
                if(Time.equals("Select Time")||Time.equals(""))
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Select Time",Toast.LENGTH_LONG).show();
                }

                if(editText_date.getText().toString().equals(""))
                {
                    flag=1;
                    button_submit.setEnabled(true);
                    Toast.makeText(context,"Select Date",Toast.LENGTH_LONG).show();
                }

                if(flag==0)
                {
                    insertAppointmentData(doctorName,editText_name.getText().toString(),editText_age.getText().toString(),Treatment,editText_date.getText().toString(),Time,"Pending");
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void insertAppointmentData(String doctorName,String name, String age, String treatment, String date, String time,String status) {
        DatabaseReference mRef=FirebaseDatabase.getInstance().getReference("myappointments");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = mRef.push().getKey();

        // creating user object
        MyAppointment myAppointment = new MyAppointment(doctorName,name,age,treatment,date,time,status);

        // pushing user to 'users' node using the userId
        mRef.child(userId).setValue(myAppointment);

        Toast.makeText(context,"Request accepted successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView circleImageView;
        ImageView imageViewFav;
        TextView textView_name;
       // TextView textView_spec;
        TextView mobileno;
        Button btnCall;
        Button btnBookAppointment;

        public MyViewHolder(View itemView) {

            super(itemView);
            textView_name=itemView.findViewById(R.id.textview_name);
           // textView_spec=itemView.findViewById(R.id.textview_spec);
            mobileno=itemView.findViewById(R.id.textview_mobile);
            circleImageView=itemView.findViewById(R.id.imageView_doctor);
            btnCall=itemView.findViewById(R.id.button_call);
            btnBookAppointment=itemView.findViewById(R.id.button_book);
            imageViewFav=itemView.findViewById(R.id.imageview_fav);
        }
    }

    protected void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(context.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
