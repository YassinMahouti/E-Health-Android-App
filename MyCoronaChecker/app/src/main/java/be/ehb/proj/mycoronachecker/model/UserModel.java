package be.ehb.proj.mycoronachecker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserModel {

    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "symptom")
    public String symptom;
    @ColumnInfo(name =" disease")
    public String disease;
    @ColumnInfo(name = "userRisk")
    public int userRisk;


    private int id;
    private int age;

    private int indicatiepercentage;
    private String user_risk;

    /**
     * Constructor -> when object created
     * @param id unique from Db
     * @param age
     * @param indicatiepercentage
     * @param user_risk a message
     */
    public UserModel(int id, int age, int indicatiepercentage, String user_risk) {
        this.id = id;
        this.age = age;
        this.indicatiepercentage = indicatiepercentage;
        this.user_risk = user_risk;
    }

    public UserModel() {
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", age=" + age +
                ", indicatiepercentage=" + indicatiepercentage +
                ", user_risk='" + user_risk + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndicatiepercentage() {
        return indicatiepercentage;
    }

    public void setIndicatiepercentage(int indicatiepercentage) {
        this.indicatiepercentage = indicatiepercentage;
    }

    public String getUser_risk() {
        return user_risk;
    }

    public void setUser_risk(String user_risk) {
        this.user_risk = user_risk;
    }
}
