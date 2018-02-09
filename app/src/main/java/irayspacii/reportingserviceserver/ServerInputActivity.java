package irayspacii.reportingserviceserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ServerInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_input);
    }

    public void onClick(View v) {

        // Get the text
        EditText ip = (EditText) findViewById(R.id.IpText);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ip", ip.getText().toString());

        startActivity(i);

    }

}
