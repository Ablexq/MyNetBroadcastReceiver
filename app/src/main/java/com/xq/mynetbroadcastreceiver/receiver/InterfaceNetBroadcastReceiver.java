package com.xq.mynetbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.xq.mynetbroadcastreceiver.event.OnNetChangeListener;
import com.xq.mynetbroadcastreceiver.utils.NetUtils;


public class InterfaceNetBroadcastReceiver extends BroadcastReceiver {

    private OnNetChangeListener onNetChangeListener;

    public InterfaceNetBroadcastReceiver(OnNetChangeListener onNetChangeListener) {
        this.onNetChangeListener = onNetChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtils.getNetWorkState(context);
            onNetChangeListener.onNetChange(netWorkState);
        }
        NetUtils.isConnected(context);
    }
}
