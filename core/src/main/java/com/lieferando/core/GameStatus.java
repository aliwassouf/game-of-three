package com.lieferando.core;


public enum GameStatus {
    INSTANCE;
    private int number = 0;
    private boolean isMyTurn = false;
    private boolean isAutoReply = false;

    public boolean isStarted() {
        return number != 0;
    }

    public int getValue() {
        return number;
    }

    public void setValue(int value) {
        this.number = value;
    }

    public boolean isMyTurn(){
        return isMyTurn;
    }
    public void setMyTurn(boolean myTurn){
        this.isMyTurn = myTurn;
    }

    public boolean isAutoReply(){
        return  isAutoReply;
    }
    public void setAutoReply(boolean val){
        this.isAutoReply = val;
    }
}
