package com.nl.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

/**
 * Created by NiuLei on 2018/1/29.
 * MVP activity基类
 */

public abstract class MvpActivity<P extends MvpContract.IPresenter> extends AppCompatActivity
        implements MvpContract.IView<P> ,DialogInterface.OnCancelListener {
    protected P presenter;
    protected Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.onCreate(savedInstanceState);
        } else {
            throw new NullPointerException("presenter is null");
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final View decorView = getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                if (presenter != null) {
                    presenter.onUiVisible();
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onBackPressed() {
        if (presenter == null) {
            super.onBackPressed();
        } else {
            if (presenter.onBackPressed()) {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (presenter != null) {
            presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (presenter != null) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgress(String text) {
        final Dialog dialog = generateDialog(text);
        if (!isFinishing() && !dialog.isShowing()) {
            dialog.show();
        }

    }

    @Override
    public void stopProgress() {
        if (dialog != null && !isFinishing()) {
            dialog.dismiss();
        }
    }

    /**
     * 生产对话框
     * @param text
     * @return
     */
    protected Dialog generateDialog(String text) {
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            dialog.setOnCancelListener(this);
        }
        dialog.setTitle(text);
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (presenter != null) {
            presenter.onDialogCancel();
        }
    }
}
