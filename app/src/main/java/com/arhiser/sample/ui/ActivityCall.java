package com.arhiser.sample.ui;

import android.app.Activity;
import android.content.Intent;

public abstract class ActivityCall<T> {

    private OnActivityResult<T> onActivityResult;
    private Activity host;
    private int requestId;

    protected abstract T fromIntent(Intent intent);
    protected abstract Class<? extends Activity> getActivityClass();

    void setHost(Activity host) {
        this.host = host;
    }

    int getRequestId() {
        return requestId;
    }

    void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    void setOnActivityResult(OnActivityResult<T> onActivityResult) {
        this.onActivityResult = onActivityResult;
    }

    public void start(Intent intent){
        intent.setClass(host, getActivityClass());
        host.startActivityForResult(intent, requestId);
    }

    void onResultIntent(Intent intent) {
        if (onActivityResult != null) {
            onActivityResult.onActivityResult(fromIntent(intent));
        }
    }

    public interface OnActivityResult<T> {
        void onActivityResult(T result);
    }
}
