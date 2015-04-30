package dk.itsmap.group22.itsmap_hi3_22;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "AlarmServiceTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendTime(View view) {
        EditText editText = (EditText) findViewById(R.id.inputField);
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please input a time", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "Attempting to start service");
            int data = Integer.parseInt(editText.getText().toString());

            Intent intent = new Intent(this, AlarmService.class);
            intent.putExtra("data", data);

            startService(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
