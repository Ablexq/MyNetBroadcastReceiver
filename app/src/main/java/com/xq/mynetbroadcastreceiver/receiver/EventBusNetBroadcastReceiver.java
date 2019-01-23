package com.xq.mynetbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.xq.mynetbroadcastreceiver.event.NetWorkEvent;
import com.xq.mynetbroadcastreceiver.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;


public class EventBusNetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtils.getNetWorkState(context);
            EventBus.getDefault().post(new NetWorkEvent(netWorkState));
        }

        NetUtils.isConnected(context);
    }
}
