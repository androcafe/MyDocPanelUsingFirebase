package visitindia.androcafe.mydocpanelusingfirebase.model;

public class Doctor {
    String name;
    String phoneno;
    String img;
    String spec;

    public Doctor(String name, String phoneno, String img,String spec) {
        this.name = name;
        this.phoneno = phoneno;
        this.img = img;
        this.spec=spec;
    }

    public Doctor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
