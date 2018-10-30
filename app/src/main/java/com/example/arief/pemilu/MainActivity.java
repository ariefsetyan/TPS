package com.example.arief.pemilu;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
//    Toolbar toolbar;
    AppCompatEditText user,pass;
    LinearLayout linearLayout;
    TextInputLayout userLayout,passLayout;
    Button login;
    String role, context, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        userLayout = (TextInputLayout)findViewById(R.id.user_layout);
        user = (AppCompatEditText)findViewById(R.id.user);
        passLayout = (TextInputLayout)findViewById(R.id.pass_layout);
        pass = (AppCompatEditText)findViewById(R.id.pass);
        linearLayout = (LinearLayout)findViewById(R.id.linierLayout);
        login = (Button)findViewById(R.id.login);

        linearLayout.setOnClickListener(null);

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (user.getText().toString().isEmpty()){
                    userLayout.setErrorEnabled(true);
                    userLayout.setError("Please Enter Username!");
                }else {
                    userLayout.setErrorEnabled(false);
                }
            }
        });
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (user.getText().toString().isEmpty()){
                    userLayout.setErrorEnabled(true);
                    userLayout.setError("Please Enter Username!");
                }else {
                    userLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passLayout.setCounterEnabled(true);
        passLayout.setCounterMaxLength(6);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);*/
                logintest();
            }
        });
    }

    /*public void login(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        role = "Get Method";
        context = "Get";
        new Login (username,password);

    }*/

  /*  public void loginPost(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        //method.setText("Post Method");
        new Login(this,status,role,1).execute(username,password);
    }*/

    public String logintest(){
        try{
            String username = user.getText().toString();
            String password = pass.getText().toString();

            String link="http://192.168.1.11/pemilu/android/test.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;

            }

            return sb.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

}
