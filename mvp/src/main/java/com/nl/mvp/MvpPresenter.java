package com.nl.mvp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NiuLei on 2018/1/29.
 * MVP Presenter基类
 */

public class MvpPresenter<M extends MvpContract.IModel, V extends MvpContract.IView> implements MvpContract.IPresenter {
    private static final int PERMISSION_REQUEST_CODE = 102;
    protected M model;
    protected V view;

    public MvpPresenter(M model, V view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onUiVisible() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {
        checkNeedPermissions();
    }

    /**
     * 检查所需权限
     */
    private void checkNeedPermissions() {
        String permissions[] = getNeedPermissions();
        if (permissions != null && permissions.length > 0) {
            for (int i = 0; i < permissions.length; i++) {
                String needPermission = permissions[i];
                List<String> deniedPermissions = new ArrayList<>();
                //收集没有获得的权限
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(view.getActivity(), needPermission)) {
                    deniedPermissions.add(needPermission);
                }
                int size = deniedPermissions.size();
                if (size > 0) {//请求权限
                    ActivityCompat.requestPermissions(view.getActivity(), deniedPermissions.toArray(new String[size]), PERMISSION_REQUEST_CODE);
                }
            }
        }
    }

    protected String[] getNeedPermissions() {
        return null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0) {
                // 2017/12/25 未做其他处理
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    if (PackageManager.PERMISSION_DENIED == grantResult) {
                        Log.w("","onRequestPermissionsResult: "+permissions[i] + " PERMISSION_DENIED");
                    }
                }
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public MvpContract.IPresenter getParent() {
        if (view != null && view instanceof Fragment) {
            Activity activity = view.getActivity();
            if (activity != null && activity instanceof MvpActivity) {
                return ((MvpActivity) activity).getPresenter();
            }
        }
        return null;
    }

    @Override
    public void onDialogCancel() {

    }
}
