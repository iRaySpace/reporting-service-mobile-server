    package irayspacii.reportingserviceserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

    public class MainActivity extends AppCompatActivity {

        // Development branch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the passed IP
        String ip = getIntent().getStringExtra("ip");

        // Set the ip text
        TextView ipText = (TextView) findViewById(R.id.ServedText);
        ipText.setText(ip);

        // If SMS has been received.
        SmsReceiver.bindListener(new SmsListener() {

            @Override
            public void messageReceived(String messageText) {

                // Extraction of information
                // -------------------------
                // [0] Code, [1] Latitude, [2] Longitude, [3] MainCategory, [4] Subcategory, [5] Accuracy, [6] Time, [7] Provider
                String[] info = messageText.split(",");

                // If correct code
                if(info[0].equals("ABM")) {

                    // Update category
                    TextView mc = (TextView) findViewById(R.id.ReportedMainText);
                    mc.setText(info[3]);

                    TextView sc = (TextView) findViewById(R.id.ReportedSubText);
                    sc.setText(info[4]);

                    // Latitude
                    TextView lat = (TextView) findViewById(R.id.LatitudeText);
                    lat.setText(info[1]);

                    // Longitude
                    TextView longitude = (TextView) findViewById(R.id.LongitudeText);
                    longitude.setText(info[2]);

                    // Time taken
                    TextView timeTaken = (TextView) findViewById(R.id.TimeTakenText);
                    timeTaken.setText(info[6]);

                    // Location Provider
                    TextView locationProvider = (TextView) findViewById(R.id.LocationProviderText);
                    locationProvider.setText(info[7]);

                    // Accuracy
                    TextView accuracy = (TextView) findViewById(R.id.AccuracyText);
                    accuracy.setText(info[5]);

                    // Reported Time
                    TextView reportedAt = (TextView) findViewById(R.id.LastReceivedText);
                    reportedAt.setText(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

                }

            }

        });

//        Button send = (Button) findViewById(R.id.post);
//        send.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//
//                TextView ipaddr = (TextView) findViewById(R.id.ipaddr);
//
//                final String ipString = ipaddr.getText().toString();
//
//                String url = "http://" + ipString + "/test.php";
//
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//
//                            @Override
//                            public void onResponse(String response) {
//                                TextView tv = (TextView) findViewById(R.id.kwan);
//                                tv.setText(response);
//                            }
//
//                        },
//                        new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                TextView tv = (TextView) findViewById(R.id.kwan);
//                                tv.setText(ipString);
//                            }
//                        }
//                ) {
//                    protected Map<String, String> getParams()
//                    {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("name", "Ivan");
//                        params.put("year", "4th");
//                        return params;
//                    }
//                };
//
//                queue.add(postRequest);
//
//
//            }
//
//        });
    }
}
