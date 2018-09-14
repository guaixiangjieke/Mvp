package com.nl.simple.template;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nl.simple.R;
import com.nl.simple.basic.BasicMvpFragment;

/**
 * mvp模板 mvp view实现类
 */

public class TemplateFragment extends BasicMvpFragment<Contract.IPresenter> implements Contract.IView {

    public TemplateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View contentView = inflater.inflate(R.layout.fragment_template, container, false);
        return contentView;
    }

    @Override
    public Contract.IPresenter getPresenter() {
        return null;
    }
}
