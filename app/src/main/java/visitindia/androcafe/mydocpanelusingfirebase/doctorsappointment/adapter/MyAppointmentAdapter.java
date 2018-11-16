package visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import visitindia.androcafe.mydocpanelusingfirebase.R;
import visitindia.androcafe.mydocpanelusingfirebase.doctorsappointment.MyAppointmentActivity;
import visitindia.androcafe.mydocpanelusingfirebase.model.MyAppointment;


public class MyAppointmentAdapter extends RecyclerView.Adapter<MyAppointmentAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyAppointment> arrayList=new ArrayList<>();

    LayoutInflater layoutInflater;


    public MyAppointmentAdapter(MyAppointmentActivity myAppointmentActivity, ArrayList<MyAppointment> myAppointment) {
        this.context=myAppointmentActivity;
        this.arrayList=myAppointment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_appointment_row,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textViewDoctor.setText("Doctor : "+arrayList.get(i).getDoctor());
        myViewHolder.textViewPatient.setText("Patient : "+arrayList.get(i).getName());
        myViewHolder.textViewAge.setText("Age : "+arrayList.get(i).getAge());
        myViewHolder.textViewdate.setText("Date : "+arrayList.get(i).getDate());
        myViewHolder.textViewTime.setText("Time : "+arrayList.get(i).getTime());
        myViewHolder.textViewStatus.setText("Status : "+arrayList.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewDoctor;
        TextView textViewPatient;
        TextView textViewdate;
        TextView textViewTime;
        TextView textViewStatus;
        TextView textViewAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDoctor=itemView.findViewById(R.id.textview_doctor);
            textViewPatient=itemView.findViewById(R.id.textview_patient);
            textViewdate=itemView.findViewById(R.id.textview_date);
            textViewTime=itemView.findViewById(R.id.textview_time);
            textViewStatus=itemView.findViewById(R.id.textview_status);
            textViewAge=itemView.findViewById(R.id.textview_age);
        }
    }
}
