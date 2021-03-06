package com.nl.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by NiuLei on 2018/1/29.
 * view生命周期以及一些回调
 */

public interface ViewCallback {
    void onCreate(@Nullable Bundle savedInstanceState);

    void onUiVisible();

    void onDestroy();

    void onStart();

    void onStop();

    void onResume();

    void onPause();

    boolean onBackPressed();

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
