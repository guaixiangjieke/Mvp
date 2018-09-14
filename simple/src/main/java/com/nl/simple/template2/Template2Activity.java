package com.nl.simple.template2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nl.simple.R;
import com.nl.simple.basic.BasicMvpActivity;


/**
 * mvp模板 mvp view实现类
 */

public class Template2Activity extends BasicMvpActivity<Contract.IPresenter> implements Contract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template2);
    }

    @Override
    public Contract.IPresenter getPresenter() {
        return null;
    }
}
