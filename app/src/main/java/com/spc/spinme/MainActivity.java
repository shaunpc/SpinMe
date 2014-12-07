package com.spc.spinme;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.util.Log;



public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.spc.spinme.MESSAGE";
    private final static String DEBUG_TAG = "SpinMe:MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void buttonPressed(View v) {
        // does something very interesting
        String  messageString;
        Integer messageInt = v.getId();

        switch (messageInt){

            case R.id.button1:
                // Who is being rude?
                messageString = getResources().getString(R.string.button1);
                break;

            case R.id.button2:
                // Who is the funniest?
                messageString = getResources().getString(R.string.button2);
                break;

            case R.id.button3:
                // Who goes first?
                messageString = getResources().getString(R.string.button3);
                break;

            case R.id.button4:
                // Who is making tea?
                messageString = getResources().getString(R.string.button4);
                break;

            default:
                // Unknown
                messageString = getResources().getString(R.string.unknown);
                break;

        }


        Log.d(DEBUG_TAG,"Action selected was: " + messageInt + ":" + messageString);

    	// Now acknowledge button press, and get ready to do something...
    	Toast.makeText(getBaseContext(), messageString, Toast.LENGTH_LONG).show();

        Log.d(DEBUG_TAG,"Passing EXTRA_MESSAGE in Intent: " + messageString);
        Intent intent = new Intent(this, DisplaySpinnerActivity.class);
        intent.putExtra (EXTRA_MESSAGE, messageInt);
    	startActivity(intent);
    	
    }
}
