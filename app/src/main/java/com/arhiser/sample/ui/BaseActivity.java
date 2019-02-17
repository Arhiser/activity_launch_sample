package com.arhiser.sample.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    ArrayList<ActivityCall> activityCalls;

    protected <T extends ActivityCall<V>, V> T registerActivityCall(T activityCall, ActivityCall.OnActivityResult<V> onActivityResult) {
        if (activityCalls == null) {
            activityCalls = new ArrayList<>();
        }
        activityCall.setHost(this);
        activityCall.setRequestId(activityCalls.size() + 1);
        activityCall.setOnActivityResult(onActivityResult);
        activityCalls.add(activityCall);
        return activityCall;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            for(ActivityCall activityCall: activityCalls) {
                if (activityCall.getRequestId() == requestCode) {
                    activityCall.onResultIntent(data);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
