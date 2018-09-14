package com.nl.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by NiuLei on 2018/1/29.
 * MVP Fragment基类
 */

public abstract class MvpFragment<P extends MvpContract.IPresenter> extends Fragment implements MvpContract.IView<P>,DialogInterface.OnCancelListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (presenter != null) {
                    presenter.onUiVisible();
                }
                return false;
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.onCreate(savedInstanceState);
        } else {
            throw new NullPointerException("presenter is null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (presenter != null) {
            presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (presenter != null) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }


    protected P presenter;

    @Override
    public void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void runOnUiThread(Runnable action) {
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(action);
        }
    }

    @Override
    public void startProgress(String text) {
        if (!invokeMvpActivity("startProgress", new Class[]{String.class}, new Object[]{text})) {
            final Dialog dialog = generateDialog(text);
            if (!isVisible() && !dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    @Override
    public void stopProgress() {
        if (!invokeMvpActivity("stopProgress",null,null)) {
            if (dialog != null && !isVisible()) {
                dialog.dismiss();
            }
        }
    }
    @Override
    public void onCancel(DialogInterface dialog) {
        if (presenter != null) {
            presenter.onDialogCancel();
        }
    }
    private Dialog dialog;
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
    /**
     * 反射调用对应activity方法
     *
     * @param name
     * @param parameterTypes
     * @param objects
     */
    private boolean invokeMvpActivity(String name, Class<?>[] parameterTypes, Object[] objects) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MvpActivity) {
            Class<MvpActivity> mvpActivityClass = MvpActivity.class;
            try {
                Method declaredMethod = mvpActivityClass.getDeclaredMethod(name, parameterTypes);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(activity, objects);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
