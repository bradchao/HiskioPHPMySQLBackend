package com.bradchao.hiskiophpmysqlbackend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {
    private MainApp mainApp;
    private EditText account,passwd,realname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mainApp = (MainApp) getApplication();
        account = findViewById(R.id.create_account);
        passwd = findViewById(R.id.create_passwd);
        realname = findViewById(R.id.create_realname);
    }


    public void createAccount(View view) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "https://www.bradchao.com/hiskio/addMember.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("bradlog", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("bradlog", error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("account", account.getText().toString());
                params.put("passwd", passwd.getText().toString());
                params.put("realname", realname.getText().toString());
                return params;
            }

        };
        mainApp.queue.add(request);

    }
}