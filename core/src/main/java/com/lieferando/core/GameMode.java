package com.lieferando.core;


public enum GameMode {
    INSTANCE;
    private boolean isAutoReply = true;

    public boolean isAutoReply(){
        return  isAutoReply;
    }
    public void setAutoReply(boolean val){
        this.isAutoReply = val;
    }
}
