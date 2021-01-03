package com.lieferando.core;


public enum GameStatus {
    INSTANCE;
    private boolean isAutoReply = true;

    public boolean isAutoReply(){
        return  isAutoReply;
    }
    public void setAutoReply(boolean val){
        this.isAutoReply = val;
    }
}
