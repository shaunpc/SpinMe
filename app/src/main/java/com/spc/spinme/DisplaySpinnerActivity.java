package com.spc.spinme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;


public class DisplaySpinnerActivity extends Activity {

    private final static String DEBUG_TAG = "SpinMe:Display:";
    private GestureDetectorCompat mDetector;
    public ImageView image;



    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_spinner);
        image=findViewById(R.id.imageView1);

        Log.d(DEBUG_TAG,"setting GestureDetector");
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        Log.d(DEBUG_TAG,"getting extras from intent");

        Intent intent = getIntent();
        int actionType = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,-1);


        Log.d(DEBUG_TAG,"Received EXTRA_MESSAGE in Intent: " + actionType);

        switch (actionType){

            case R.id.button1:
                // Who is being rude?
                image.setImageResource(R.drawable.arrow_blue);
                break;

            case R.id.button2:
                // Who is the funniest?
                image.setImageResource(R.drawable.arrow_dots);
                break;

            case R.id.button3:
                // Who goes first?
                image.setImageResource(R.drawable.arrow_red);
                break;

            case R.id.button4:
                // Who is making tea?
                image.setImageResource(R.drawable.arrow_green);
                break;

            default:
                // Unknown
                image.setImageResource(R.drawable.ic_launcher);
                break;

        }

    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "SpinMe:Gestures:";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            Toast.makeText(getBaseContext(), "Fling me baby!", Toast.LENGTH_LONG).show();
            spinThatArrow ();
            return true;
        }
    }

    public void spinThatArrow () {

        Random random = new Random();
        float timeToSpinFor = Math.abs(random.nextInt() % 3600)+360;
        Log.d(DEBUG_TAG,"timeToSpinFor is " + timeToSpinFor);

        // Step1 : create the  RotateAnimation object, around it's midpoint
        final RotateAnimation anim = new RotateAnimation(0f, timeToSpinFor,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // Step 2:  Set the Animation properties

        // Defines the interpolator used to smooth the animation movement in time.
        // Degree to which the animation should be eased.
        // Setting factor to 1.0f produces an upside-down y=x^2 parabola
        anim.setInterpolator(new DecelerateInterpolator(1.0f));
        // Need to use Linear as Decelerate applies to a single rotation, not continuous..
        // anim.setInterpolator(new LinearInterpolator());

        // Defines how many times the animation should repeat.
        // anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatCount(0);

        // Amount of time (in milliseconds) for the animation to run each sequence
        anim.setDuration(5000);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);

        // Step 3: Start animating the image
        image.startAnimation(anim);

        // Step 4:get a random amount of time to spin for, before we stop the animation
        //Random random = new Random();
        //int timeToSpinFor = Math.abs(random.nextInt() % 5000)+3000;
        //Log.d(DEBUG_TAG,"timeToSpinFor is " + timeToSpinFor);
        //final Handler handler = new Handler();
        //handler.postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        //Do something after 100ms
        //        Log.d(DEBUG_TAG,"Time's up - stopping animation");
        //        //image.setAnimation(null);
        //        //setting to null resets the imageView, maybe just try to wait a while...
        //        anim.wait(5000);
        //    }
        //}, timeToSpinFor);


    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_spinner, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return id == R.id.action_settings || super.onOptionsItemSelected(item);
	}

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = event.getActionMasked();
        this.mDetector.onTouchEvent(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
