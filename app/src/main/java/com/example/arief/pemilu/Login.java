package com.example.arief.pemilu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AsyncTask{
    private TextView statusField,roleField;
    private Context context;
    String role, status, method;
    private int byGetOrPost = 0;

    public Login(Context context, String status, String role,int flag) {
        this.context = context;
        this.status = status;
        this.role = role;
        byGetOrPost = flag;
    }

    protected void onPostExecute(String result){
        //this.statusField.setText("Login Successful");
        //this.roleField.setText(result);
        Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Object doInBackground(Object[] arg0) {
        try{
            String username = (String)arg0[0];
            String password = (String)arg0[1];

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
