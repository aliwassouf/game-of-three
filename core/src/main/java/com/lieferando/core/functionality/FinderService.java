package com.lieferando.core.functionality;


public abstract class FinderService {

    public static int findNearestNumberToThreeOf(int number){
        int quotient = number / 3;


        int nearestNumber = (number * 3) > 0 ? (3 * (quotient + 1)) : (3 * (quotient - 1));

        return nearestNumber;
    }
}
