package com.xq.mynetbroadcastreceiver.event;


public class NetWorkEvent {
    private int netState;

    public NetWorkEvent(int netState) {
        this.netState = netState;
    }

    public int getNetState() {
        return netState;
    }

    public void setNetState(int netState) {
        this.netState = netState;
    }
}
