package com.example.foodchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private static final String BASE_URL = "https://api.spoonacular.com/food/products/search?query=pizza&number=1";
    private static final String API_KEY = "&apiKey=042f21f13acd4749b106f4af1dd52728";

    private TextView mTextViewFoodResult;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewFoodResult = findViewById(R.id.textViewFoodResult);
        Button buttonFetch = findViewById(R.id.buttonFetchFood);

        mRequestQueue = Volley.newRequestQueue(this);

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonFetch();
            }
        });
    }

    private void jsonFetch() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL + API_KEY, null,
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

                                mTextViewFoodResult.append(id + "\n" + title + "\n" + image);
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
