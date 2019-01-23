package com.xq.mynetbroadcastreceiver.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xq.mynetbroadcastreceiver.event.NetWorkEvent;
import com.xq.mynetbroadcastreceiver.R;
import com.xq.mynetbroadcastreceiver.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private int netState;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = ((TextView) this.findViewById(R.id.tv));

        netState = NetUtils.getNetWorkState(this);//初始化
        System.out.println("初始化===========" + netState);
        setState();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetWorkEvent event) {
        netState = event.getNetState();
        System.out.println("监听变化==================" + netState);
        setState();
    }

    private void setState() {
        switch (netState) {
            case -1:
                mTv.setText("无网络");
                break;
            case 0:
                mTv.setText("移动网");
                break;
            case 1:
                mTv.setText("WiFi");
                break;
        }
    }
}
