package com.example.foodchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Data halen uit search DONE
    // Data plaatsen in url DONE
    // ID in data weer gebruiken in nieuwe call
    // Niuewe button aanmaken
    // Voert nieuew api call uit via button
    // Toon nutrition op basis van id

    private static final String BASE_URL = "https://api.spoonacular.com/food/products/search";
    private static final String API_KEY = "apiKey=042f21f13acd4749b106f4af1dd52728";

    private EditText mEditTextInput;
    private TextView mTextViewFoodResult;
    private RequestQueue mRequestQueue;

    private String userInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextInput = findViewById(R.id.editTextInput);
        mTextViewFoodResult = findViewById(R.id.textViewFoodResult);
        Button buttonSearch = findViewById(R.id.buttonFetchFood);

        mRequestQueue = Volley.newRequestQueue(this);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = mEditTextInput.getText().toString().trim();

                jsonFetch();
            }
        });
    }

    private void jsonFetch() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL + "?" + API_KEY + "&query=" + userInput + "&number=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject product = jsonArray.getJSONObject(i);

                                String id = product.getString("id");
                                String title = product.getString("title");
                                String image = product.getString("image");

                                mTextViewFoodResult.append(id + "\n" + title + "\n" + image + "\n\n");
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
