package be.ehb.LoginMockup.ui.steps;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import be.ehb.Ehealth.R;

public class StepCounter extends AppCompatActivity {
    private Handler mHandler;
    private Runnable _timer1;
    private int stepCounter = 0;
    private int lastStep = 0;
    private boolean showedGoalReach = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        stepCounter = (int)DebugActivity.mStepCounter;
        mHandler = new Handler();
        startRepeatingTask();
    }

    private void updateView(){
        if(DebugActivity.mStepCounter > stepCounter) {
            stepCounter = (int)DebugActivity.mStepCounter;
            if(stepCounter >= 500 && !showedGoalReach){
                showedGoalReach = true;
                Context context = getApplicationContext();
                CharSequence text = "Good Job! You've reached your goal!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            TextView stepCountStr = (TextView) this.findViewById(R.id.maintv1);
            stepCountStr.setText(new String("Step Count: " + stepCounter));
            TextView progressText = (TextView) this.findViewById(R.id.maintv2);
            progressText.setText(new String("Step Goal: " + 500 + ". Progress: " + stepCounter + " / 500"));

            ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", lastStep, stepCounter); //animate only from last known step to current step count
            animation.setDuration(5000); // in milliseconds
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
            lastStep = stepCounter;
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
        final Context context = this;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_debug) {
            Intent intent = new Intent(context, DebugActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateView();
            } finally {
                mHandler.postDelayed(mStatusChecker, 500);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
}




