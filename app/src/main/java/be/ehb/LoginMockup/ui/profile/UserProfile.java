package be.ehb.LoginMockup.ui.profile;

public class UserProfile {
    private String username;
    private String user_mail;
    private String age;
    private String phone;
    private String gender;
    private float user_weight;
    private float user_height;
    private boolean adult;
    private float userBMI;
    private float userBFP;

    //---constructors---//


    public UserProfile() {
    }

    public UserProfile(String username, String user_mail, String age,String phone, float user_weight, float user_height,String gender) {
        this.username = username;
        this.user_mail=user_mail;
        this.age = age;
        this.phone=phone;
        this.gender=gender;
        this.user_weight=user_weight;
        this.user_height=user_height;
    }
    public UserProfile( String username){
        this.username = username;
    }


    //---Getters en setters--//


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public float getUser_weight() {
        return user_weight;
    }

    public void setUser_weight(float user_weight) {
        this.user_weight = user_weight;
    }

    public float getUser_height() {
        return user_height;
    }

    public void setUser_height(float user_height) {
        this.user_height = user_height;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public float getUserBMI() {
        return userBMI;
    }

    public void setUserBMI(float userBMI) {
        this.userBMI = userBMI;
    }

    public float getUserBFP() {
        return userBFP;
    }

    public void setUserBFP(float userBFP) {
        this.userBFP = userBFP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", user_mail='" + user_mail + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", user_weight=" + user_weight +
                ", user_height=" + user_height +
                ", adult=" + adult +
                ", userBMI=" + userBMI +
                ", userBFP=" + userBFP +
                '}';
    }
}
