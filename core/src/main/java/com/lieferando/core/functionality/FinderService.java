package com.lieferando.core.functionality;


public abstract class FinderService {

    public static int findNearestNumberToThreeOf(int number){
        int quotient = number / 3;

        int firstNearestNumber = 3 * quotient;

        int secondNearestNumber = (number * 3) > 0 ? (3 * (quotient + 1)) : (3 * (quotient - 1));

        if (Math.abs(number - firstNearestNumber) < Math.abs(number - secondNearestNumber))
            return firstNearestNumber;

        return secondNearestNumber;
    }
}
