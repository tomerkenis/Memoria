package me.tomerkenis.memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Decks extends AppCompatActivity {

    TextView data;
    EditText id_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decks);
        data = findViewById(R.id.data_view);
        id_view = findViewById(R.id.product_id);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public String getCodeFromInternet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try  {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_button:
                data.setText(getCodeFromInternet("http://192.168.1.180:9090/"));
                break;
            case R.id.products_button:
                data.setText(getCodeFromInternet("http://192.168.1.180:9090/products"));
                break;
            case R.id.product_button:
                data.setText(getCodeFromInternet("http://192.168.1.180:9090/product/" + id_view.getText().toString()));
                break;
        }
    }


}