package com.nl.simple.template;

import com.nl.simple.basic.BasicMvpPresenter;

/**
 * mvp模板 mvp presenter实现类
 */

public class TemplatePresenterImp extends BasicMvpPresenter<Contract.IModel, Contract.IView>
        implements Contract.IPresenter {
    public TemplatePresenterImp(Contract.IModel model, Contract.IView view) {
        super(model, view);
    }
}
