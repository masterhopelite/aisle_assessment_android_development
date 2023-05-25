package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.application.ui.login.LoginActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {
    private EditText otpEditText;
    private Button btnLogin;
    private Button buttonlgn;
    private TextView timerTextView;
    private TextView tvError;
    private CountDownTimer countDownTimer;
    private static final long COUNTDOWN_DURATION = 60000; // 1 minute in milliseconds
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("second run");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        //button3 =findViewById(R.id.button3);
        Button nextButton = findViewById(R.id.button3);
        btnLogin = findViewById(R.id.button3);


        otpEditText = findViewById(R.id.otpEditText);
        timerTextView = findViewById(R.id.timerTextView);
        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        tvError = findViewById(R.id.textView4);
        phoneNumberEditText.setText(phoneNumber);

        countDownTimer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView with the remaining time
                String timeLeft = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                timerTextView.setText("Timer: " + timeLeft);
            }

            @Override
            public void onFinish() {
                // Handle timer finish event (e.g., show a message or perform an action)
                timerTextView.setText("Timer: 00:00");
                // Add your code here for actions to be taken when the timer finishes
            }
        };

        countDownTimer.start(); // Start the countdown timer

        btnLogin.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String Otp = otpEditText.getText().toString().trim();

                                           // Perform phone number validation and login logic here
                                           if (isValidOTP(Otp)) {
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
                                                       String otp = "1234";
                                                       String otpApiUrl = "https://app.aisle.co/V1/users/verify_otp";

                                                       try {
                                                           URL url = new URL(otpApiUrl);
                                                           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                                           connection.setRequestMethod("POST");
                                                           connection.setRequestProperty("Authorization", "auth_token");
                                                           int responseCode = connection.getResponseCode();
                                                           if (responseCode == 200) {
                                                               System.out.println("success");
                                                           } else {
                                                               System.out.println("OTP API call failed with response code: " + responseCode);
                                                           }

                                                           connection.disconnect();
                                                       } catch (Exception var5) {
                                                           var5.printStackTrace();
                                                       }

                                                       Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                                                       startActivity(intent);


                                                   }
                                               });

                                           } else {
                                               // Invalid phone number, show error message
                                               tvError.setVisibility(View.VISIBLE);
                                               tvError.setText("Invalid phone number!");
                                           }

                                       }
            public boolean isValidOTP(String enteredOTP) {
                // Remove any whitespace from the entered OTP
                enteredOTP = enteredOTP.trim();
                String generatedOTP="1234";

                // Check if entered OTP and generated OTP have exactly 4 digits
                if (enteredOTP.length() != 4 || generatedOTP.length() != 4) {
                    return false;
                }

                // Compare the entered OTP with the generated OTP
                return enteredOTP.equals(generatedOTP);
            }


//        countDownTimer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                // Update the timer TextView with the remaining time
//                String timeLeft = String.format(Locale.getDefault(), "%02d:%02d",
//                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
//                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
//                timerTextView.setText("Timer: " + timeLeft);
//            }
//
//            @Override
//            public void onFinish() {
//                // Handle timer finish event (e.g., show a message or perform an action)
//                timerTextView.setText("Timer: 00:00");
//                // Add your code here for actions to be taken when the timer finishes
//            }
//        };
//
//        countDownTimer.start(); // Start the countdown timer

        });

    }
}