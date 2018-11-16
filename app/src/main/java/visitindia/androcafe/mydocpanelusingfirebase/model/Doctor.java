package visitindia.androcafe.mydocpanelusingfirebase.model;

public class Doctor {
    String name;
    String phoneno;
    String img;
    String specialize;


    public Doctor() {

    }

    public Doctor(String name, String phoneno, String img, String specialize) {
        this.name = name;
        this.phoneno = phoneno;
        this.img = img;
        this.specialize = specialize;
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

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }
}
