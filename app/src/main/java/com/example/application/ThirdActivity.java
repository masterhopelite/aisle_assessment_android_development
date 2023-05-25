package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThirdActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button buttonlgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button nextButton = findViewById(R.id.button2);
        btnLogin = findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Create and start the Intent to launch the next activity
//                            Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
//                            intent.putExtra("phoneNumber", phoneNumber);
//                            startActivity(intent);
                            String notesApiUrl = "https://app.aisle.co/V1/users/test_profile_list";


                            try {
                                URL url = new URL(notesApiUrl);
                                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                                connection.setRequestMethod("GET");
                                connection.setRequestProperty("Authorization", "Bearer $authToken");
                                int responseCode = connection.getResponseCode();
                                if (responseCode != 200) {
                                    System.out.println("Notes API call failed with response code: " + responseCode);
                                } else {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                    StringBuilder response = new StringBuilder();

                                    String line;
                                    while((line = reader.readLine()) != null) {
                                        response.append(line);
                                    }

                                    reader.close();
                                    System.out.println("Notes API response: " + response.toString());
                                }

                                connection.disconnect();
                            } catch (Exception var7) {
                                var7.printStackTrace();
                            }


                        }
                    });

                }

            });
    }
}