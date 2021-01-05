package com.lieferando.core;


public abstract class NearestNumberService {

    public static int findByAddingOne(int number) {
        return (number + 1) / 3;
    }

    public static int findBySubtractingOne(int number) {
        return (number - 1) / 3;
    }

    public static int find(int number) {
        return checkEdgeCases(number) / 3;
    }

    private static int checkEdgeCases(int number) {
        if (number == 2)
            return number + 1;
        else if (number == -2)
            return number - 1;
        return number;
    }
}
