package com.lieferando.core;


import java.util.function.IntBinaryOperator;

public enum NearestNumberService implements IntBinaryOperator {
    INSTANCE;

    @Override
    public int applyAsInt(int number, int move) {
        if (number == 2)
            return number + 1;
        else if (number == -2)
            return number - 1;
        return (number + move) / 3;
    }
}
