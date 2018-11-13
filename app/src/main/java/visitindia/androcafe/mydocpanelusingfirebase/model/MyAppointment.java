package visitindia.androcafe.mydocpanelusingfirebase.model;

public class MyAppointment {
    String doctor;
    String name;
    String age;
    String treatment;
    String date;
    String time;
    String status;

    public MyAppointment() {
    }

    public MyAppointment(String doctor, String name, String age, String treatment, String date, String time, String status) {
        this.doctor = doctor;
        this.name = name;
        this.age = age;
        this.treatment = treatment;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

}
