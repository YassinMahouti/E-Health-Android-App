package com.example.foodchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    // Fetch data from userInput - DONE
    // Place the fetched data in url - DONE
    // ID in data weer gebruiken in nieuwe call
    // Niuewe button aanmaken
    // Voert nieuew api call uit via button
    // Toon nutrition op basis van id

    private static final String BASE_URL_SEARCH = "https://api.spoonacular.com/food/products/search";
    //private static final String API_KEY = "apiKey=042f21f13acd4749b106f4af1dd52728";
    private static final String API_KEY = "apiKey=bd789f29c279480fb2a12b1d4102c658";

    // test
//    private static final String TEST = "https://api.spoonacular.com/food/products/22347?";
    private static final String BASE_URL_DETAILS = "https://api.spoonacular.com/food/products/";


    private EditText mEditTextInput;
    private TextView mTextViewNutrition;
    private TextView mTextViewFoodResult;
    private RequestQueue mRequestQueue;
    private String Image ="";

    private String userInput = "";
    private String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextInput = findViewById(R.id.editTextInput);

        mTextViewFoodResult = findViewById(R.id.textViewFoodResult);
        mTextViewNutrition = findViewById(R.id.textViewNutrition);
        final Button buttonSearch = findViewById(R.id.buttonFetchFood);
        final Button buttonDetails = findViewById(R.id.buttonFetchDetails);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final Button BtnClear = findViewById(R.id.BtnClear);
        //Picasso.get().load(Image).into(imageView);
        mRequestQueue = Volley.newRequestQueue(this);

        BtnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mTextViewNutrition.setText("");
                mTextViewFoodResult.setText("");
                imageView.setImageResource(0);

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = mEditTextInput.getText().toString().trim();

                jsonAnalyser();


            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonAnalyserDetails();

                Picasso.get().load(Image).into(imageView);



            }
        });
    }


    private void jsonAnalyser() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL_SEARCH + "?" + API_KEY + "&query=" + userInput + "&number=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject product = jsonArray.getJSONObject(i);

                                String id = product.getString("id");
                                foodId = id;
                                String title = product.getString("title");
                                String image = product.getString("image");

                                Image = image;

                                mTextViewFoodResult.append(title + "\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(objectRequest);

    }

    private void jsonAnalyserDetails() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL_DETAILS + foodId + "?" + API_KEY, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                            System.out.println(response.toString());

                            JSONObject jsonObject = response.getJSONObject("nutrition");
//                            System.out.println(jsonObject.toString());

                            double calories = jsonObject.getDouble("calories");

//                            double calories = 0.0; // double cant be null!
//                            try {
//                            } catch (JSONException e) {
//                                calories = 0.1;
//                            }

                            String fat = "";
                            try {
                                fat = (String) jsonObject.get("fat");

                            } catch (JSONException e) {
                                fat = "N.A.";
                            }

                            String protein = "";
                            try {
                                protein = (String) jsonObject.get("protein");
                            } catch (JSONException e) {
                                protein = "N.A.";
                            }
                            String carbs;

                            try {
                                carbs = (String) jsonObject.get("carbs");
                            } catch (JSONException e) {
                                carbs = "N.a.";
                            }

                            String serving_size = "";
                            try {
                                serving_size = (String) jsonObject.get("serving_size");
                            } catch (JSONException e) { serving_size = "N.A.";
                            }

                            mTextViewNutrition.append("Calories: " + calories + "\nFat: " + fat + "\nProtein: " + protein + "\nCarbs: " + carbs + "\nServing Size: " + serving_size);


//                            String protein = (String) jsonObject.get("protein");
//                            String carbs = (String) jsonObject.get("carbs");
                            System.out.println("calories: " + calories);
                            System.out.println("fat: " + fat);




                            JSONArray jsonArray = response.getJSONArray("nutrients");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject nutrients = jsonArray.getJSONObject(i);

                                String title = nutrients.getString("title");
                                String amount = nutrients.getString("amount");

                                mTextViewNutrition.append(title + "\n" + amount);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(objectRequest);
    }

}
