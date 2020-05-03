package be.ehb.proj.basicbfpapplication.model;

public class User {

    private int user_id=1;
    private int result_id;
    private float height;
    private float weight;
    private float result_bmi;
    private boolean save_bmi;



    public User( int result_id,int user_id, float height, float weight , boolean save_bmi)
    {
        this.user_id = user_id;
        this.result_id =result_id;
        this.height = height;
        this.weight = weight;
        this.save_bmi = save_bmi;
        this.calculateBMI();
    }

    public void setResult_bmi(float result_bmi) {
        this.result_bmi = result_bmi;
    }

    public boolean isSave_bmi() {
        return save_bmi;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getResult_id() {
        return result_id;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getResult_bmi() {
        return result_bmi;
    }

    public float calculateBMI()
    {
        float heightInM = (float) height/100;
        return this.result_bmi =  weight / (heightInM*heightInM); // weight in kg

    }
}

