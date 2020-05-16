package be.ehb.LoginMockup.ui.corona;

import java.util.Comparator;

/**
 * Able to use the Recycler View( to show the results of the user) I need to store the information of the user.
 * Class User has 2 Constructor, and 4 data-members with getters and setters.
 */
public class User implements Comparable<User> {
    //properties same name as in table on Firebase database
    private int countSymptoms;
    private String date;
    private int user_id;
    private float user_risk;

    /**
     * Empty Constructor
     */
    public User() {
    }

    /**
     * Constructor able to store the information of the user
     * @param countSymptoms : the amount of symptoms the user has depending on answers from the CheckUp
     * @param date : to have a evolution or creating graphics I need to store the date(and later to see the progress)
     * @param user_id : each user has a unique id, otherwise he can see al the data of other users
     * @param user_risk : able to give an indication I calculate a risk percentage tha indicate the chance to suffer from the Corona-virus.
     */
    public User(int countSymptoms, String date, int user_id, float user_risk) {
        this.countSymptoms = countSymptoms;
        this.date = date.toLowerCase();
        this.user_id = user_id;
        this.user_risk = user_risk;
    }

    public int getCountSymptoms() {
        return countSymptoms;
    }

    public void setCountSymptoms(int countSymptoms) {
        this.countSymptoms = countSymptoms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getUser_risk() {
        return user_risk;
    }

    public void setUser_risk(float user_risk) {
        this.user_risk = user_risk;
    }


    @Override
    public int compareTo(User o) {
        //interface: return negative or 0 or positive
        return this.user_id -o.getUser_id();
    }

    // implement comparator interface
    public static Comparator<User> myDate = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };
}
