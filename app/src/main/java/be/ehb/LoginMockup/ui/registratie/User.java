package be.ehb.LoginMockup.ui.registratie;

public class User {
    public String name, email, phone, weight, height, age, gender;

    public User() {
    }

    public User(String name, String email, String phone, String weight, String height, String age, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

}
