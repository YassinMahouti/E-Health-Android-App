package be.ehb.LoginMockup.ui.foodchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import be.ehb.Ehealth.R;

public class FoodCheckerMain extends AppCompatActivity {

    // Fetch data from userInput - DONE
    // Place the fetched data in url - DONE
    // ID in data weer gebruiken in nieuwe call - DONE
    // Niuewe button aanmaken DONE
    // Voert nieuew api call uit via button - DONE
    // Toon nutrition op basis van id -  DONE

    // static members wont change so for that static and final so that other instances can't change them
    // URL of our food API
    private static final String BASE_URL_SEARCH = "https://api.spoonacular.com/food/products/search";
    //private static final String API_KEY = "apiKey=042f21f13acd4749b106f4af1dd52728";
    private static final String API_KEY = "apiKey=eb799ee8f33e4cf89c5a3649834df06e";

    // test
    //  private static final String TEST = "https://api.spoonacular.com/food/products/22347?";

    // Nutritional data has a different path than BASE_URL_SEARCH
    private static final String BASE_URL_DETAILS = "https://api.spoonacular.com/food/products/";

    // Member initialization
    private EditText mEditTextInput;
    private TextView mTextViewNutrition;
    private TextView mTextViewFoodResult;
    // A Queue containing the http requests that needs to be made.
    private RequestQueue mRequestQueue;

    // variable to contain the url of the image
    private String Image ="";
    // contains the value the user has inserted
    private String userInput = "";
    // jsonAnalyser needs to have access to it
    private String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food_checker);
        //---------editText-----------------------
        mEditTextInput = findViewById(R.id.editTextInput);
        //---------TextView-----------------------
        mTextViewFoodResult = findViewById(R.id.textViewFoodResult);
        mTextViewNutrition = findViewById(R.id.textViewNutrition);
        //---------Button-----------------------
        final Button buttonSearch = findViewById(R.id.buttonFetchFood);
        final Button buttonDetails = findViewById(R.id.buttonFetchDetails);
        final Button BtnClear = findViewById(R.id.BtnClear);
        //---------ImageView-----------------------
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //Picasso.get().load(Image).into(imageView);

        // Instantiate the RequestQueue with this context because we only need it for this activity
        mRequestQueue = Volley.newRequestQueue(this);

        // clears the variables by giving them empty values when clicked
        BtnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mTextViewNutrition.setText("");
                mTextViewFoodResult.setText("");
                imageView.setImageResource(0);

            }
        });

        // value the user has inserted is given to userInput
        // That values is then used as a query for the api call
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = mEditTextInput.getText().toString().trim();

                jsonAnalyser();
            }
        });

        // Shows the nutritional data based of the food id, extracted from the previous request
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonAnalyserDetails();

                // source: https://square.github.io/picasso/
                // image library for android
                // loads images through an url
                //Picasso.get().load(String.valueOf(Image)).into(imageView);
            }
        });
    }

    // A request for retrieving a JSONObject response body through an url
    private void jsonAnalyser() {
        // Get request and because the expected request is an object its an JsonObjectRequest
        // Pass the combined url as argument,
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL_SEARCH + "?" + API_KEY + "&query=" + userInput + "&number=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // the response is an json object
                            // get json array out of the object
                            // get the objects out of the array

                            // pass the name of the array
                            JSONArray jsonArray = response.getJSONArray("products");

                            // loop through the array to get the values out of the objects
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject product = jsonArray.getJSONObject(i);

                                String id = product.getString("id");
                                foodId = id;
                                String title = product.getString("title");
                                String image = product.getString("image");

                                Image = image;

                                // add the result to the textView
                                mTextViewFoodResult.append(title + "\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Handle the error
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue
        mRequestQueue.add(objectRequest);

    }

    // Get request and because the expected request is an object its an JsonObjectRequest
    // Pass the combined url as argument,
    private void jsonAnalyserDetails() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL_DETAILS + foodId + "?" + API_KEY, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                            System.out.println(response.toString());

                            // the response is an json object
                            JSONObject jsonObject = response.getJSONObject("nutrition");
//                            System.out.println(jsonObject.toString());

                            double calories = jsonObject.getDouble("calories");

//                            double calories = 0.0; // double cant be null!
//                            try {
//                            } catch (JSONException e) {
//                                calories = 0.0;
//                            }
                            // Gets values out of the object and store them in variables
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

                            // append the variables containing the values
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

        // Add the request to the RequestQueue
        mRequestQueue.add(objectRequest);
    }
}