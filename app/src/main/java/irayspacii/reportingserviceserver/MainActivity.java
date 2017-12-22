    package irayspacii.reportingserviceserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmsReceiver.bindListener(new SmsListener() {

            @Override
            public void messageReceived(String messageText) {

                TextView tv = (TextView) findViewById(R.id.kwan);
                tv.setText(messageText);

            }

        });

        Button send = (Button) findViewById(R.id.post);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                TextView ipaddr = (TextView) findViewById(R.id.ipaddr);

                final String ipString = ipaddr.getText().toString();

                String url = "http://" + ipString + "/test.php";

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                TextView tv = (TextView) findViewById(R.id.kwan);
                                tv.setText(response);
                            }

                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                TextView tv = (TextView) findViewById(R.id.kwan);
                                tv.setText(ipString);
                            }
                        }
                ) {
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", "Ivan");
                        params.put("year", "4th");
                        return params;
                    }
                };

                queue.add(postRequest);


            }

        });
    }
}
