package com.example.application.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.example.application.ApiInterface;
import com.example.application.R;
import com.example.application.SecondActivity;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private EditText etPhoneNumber;
    private Button btnLogin;
    private TextView tvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button nextButton = findViewById(R.id.button);
        etPhoneNumber = findViewById(R.id.editTextPhone);
        btnLogin = findViewById(R.id.button);
        tvError = findViewById(R.id.textView6);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                // Perform phone number validation and login logic here
                if (isValidPhoneNumber(phoneNumber)) {
                    // Login successful
                    tvError.setVisibility(View.GONE);
                    // Proceed to the next activity or perform necessary actions
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Create and start the Intent to launch the next activity
//                            Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
//                            intent.putExtra("phoneNumber", phoneNumber);
//                            startActivity(intent);
                                String countryCode = "+91";
                                String phoneNumber = "9876543212";
                                String phoneNumberApiUrl = "https://app.aisle.co/V1/users/phone_number_login";
                                String phoneNumberApiRequestUrl = phoneNumberApiUrl + "?country_code=" + countryCode + "&phone_number=" + phoneNumber;

                                try {
                                    URL url = new URL(phoneNumberApiRequestUrl);
                                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                                    connection.setRequestMethod("POST");
                                    int responseCode = connection.getResponseCode();
                                    if (responseCode == 200) {
                                        System.out.println("success");
                                    } else {
                                        System.out.println("Phone number API call failed with response code: " + responseCode);
                                    }

                                    connection.disconnect();
                                } catch (Exception var8) {
                                    var8.printStackTrace();
                                }
                            Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                            intent.putExtra("phoneNumber", phoneNumber);
                            startActivity(intent);
                        }
                    });

                } else {
                    // Invalid phone number, show error message
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Invalid phone number!");
                }
            }


            private boolean isValidPhoneNumber(String phoneNumber) {
                // Remove any spaces or dashes from the phone number
                String strippedNumber = phoneNumber.replaceAll("\\s", "").replaceAll("-", "");

                // Check if the stripped number is empty or null
                if (strippedNumber == null || strippedNumber.isEmpty()) {
                    return false;
                }

                // Check if the stripped number contains only numeric digits
                if (!TextUtils.isDigitsOnly(strippedNumber)) {
                    return false;
                }

                // Check if the stripped number has a valid length
                if (strippedNumber.length() < 10 || strippedNumber.length() > 12) {
                    return false;
                }

                // Additional custom validation rules can be added here if needed

                return true; // Phone number is valid
            }

//            private void performPhoneNumberLogin(String phoneNumber) {
//                // Create an instance of the Retrofit API interface
//                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//                // Create the request model
//                YourRequestModel requestModel = new YourRequestModel(phoneNumber); // Replace with your own request model
//
//                // Make the API call
//                Call<YourResponseModel> call = apiInterface.phoneNumberLogin(requestModel);
//                call.enqueue(new Callback<YourResponseModel>() {
//                    @Override
//                    public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
//                        if (response.isSuccessful()) {
//                            // Handle the successful response here
//                            YourResponseModel responseModel = response.body();
//                            // ...
//                        } else {
//                            // Handle the error response here
//                            // ...
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<YourResponseModel> call, Throwable t) {
//                        // Handle the API call failure here
//                        // ...
//                    }
//                });
//            }

            });

    }

//    private boolean isValidPhoneNumber(String phoneNumber) {
//        // Implement your phone number validation logic here
//        // You can use regular expressions, libraries, or custom rules to validate the phone number
//        // Return true if the phone number is valid; otherwise, return false
//    }
}
