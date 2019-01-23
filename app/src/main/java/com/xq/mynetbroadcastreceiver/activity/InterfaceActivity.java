package com.xq.mynetbroadcastreceiver.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xq.mynetbroadcastreceiver.R;
import com.xq.mynetbroadcastreceiver.event.OnNetChangeListener;
import com.xq.mynetbroadcastreceiver.receiver.InterfaceNetBroadcastReceiver;
import com.xq.mynetbroadcastreceiver.utils.NetUtils;


public class InterfaceActivity extends AppCompatActivity implements OnNetChangeListener {

    private InterfaceNetBroadcastReceiver mBroadcastReceiver;
    private int netWorkState;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = ((TextView) this.findViewById(R.id.tv));
        mBroadcastReceiver = new InterfaceNetBroadcastReceiver(this);
        netWorkState = NetUtils.getNetWorkState(this);
        System.out.println("初始化==================" + netWorkState);
        setState();
    }

    //在onResume()方法注册
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onNetChange(int netState) {
        netWorkState = netState;
        System.out.println("接口监听变化==================" + netState);
        setState();
    }

    private void setState() {
        switch (netWorkState) {
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
