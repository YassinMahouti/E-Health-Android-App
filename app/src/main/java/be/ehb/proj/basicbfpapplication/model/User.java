package be.ehb.proj.basicbfpapplication.model;

public class User {

        //properties same name as in table on Firebase database
        private float height;
        private String date;
        private float weight;
        private float user_bmi;
        private float user_bfp;
        private int sex;
        private int age=5;


        public User() {
        }

        public User(float height, String date, float weight,int sex, int age, float user_bmi, float user_bfp) {
            this.height = height;
            this.date = date;
            this.weight = weight;
            this.sex = sex;
            this.age = age;
            this.user_bmi = user_bmi;
            this.user_bfp = user_bfp;
        }

    public float getUser_bfp() {
        return user_bfp;
    }

    public void setUser_bfp(float user_bfp) {
        this.user_bfp = user_bfp;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getUser_bmi() {
        return user_bmi;
    }

    public void setUser_bmi(float user_bmi) {
        this.user_bmi = user_bmi;
    }
}
