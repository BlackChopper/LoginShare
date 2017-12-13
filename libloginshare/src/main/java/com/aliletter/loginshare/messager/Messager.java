package com.aliletter.loginshare.messager;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：alilettter
 * Github: http://github.com/aliletter
 * Email: 4884280@qq.com
 * data: 2017/12/11
 */

public class Messager {
    private static Messager instance;
    private List<IWXAPIEventHandler> binders;

    public static Messager getInstance() {
        if (instance == null) {
            synchronized (Messager.class) {
                if (instance == null)
                    instance = new Messager();
            }
        }
        return instance;
    }

    private Messager() {
        binders = new ArrayList<>();
    }

    public void register(IWXAPIEventHandler baseLogin) {
        binders.add(baseLogin);
    }

    public void unRegister(IWXAPIEventHandler baseLogin) {
        binders.remove(baseLogin);
    }

    public void sendReq(BaseReq baseReq) {
        for (IWXAPIEventHandler binder : binders) {
            binder.onReq(baseReq);
        }
    }

    public void sendResp(BaseResp resp) {
        for (IWXAPIEventHandler binder : binders) {
            binder.onResp(resp);
        }
    }
}
