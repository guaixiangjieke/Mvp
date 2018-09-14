package com.nl.simple.template2;

import com.nl.simple.basic.BasicMvpPresenter;

/**
 * mvp模板 mvp presenter实现类
 */

public class Template2PresenterImp extends BasicMvpPresenter<Contract.IModel, Contract.IView>
        implements Contract.IPresenter {
    public Template2PresenterImp(Contract.IModel model, Contract.IView view) {
        super(model, view);
    }
}
