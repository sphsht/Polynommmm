package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Polynom a = new Polynom(new double[]{0, 9, 1});
        Polynom b = new Polynom(new double[]{-2, 1, 1, -1});


        // System.out.println(Arrays.toString(c.coef));
        //  System.out.println(Polynom.lagrange(new double[]{0, 1, 4}, new double[]{-1, 1, 1}));//должно быть -0.5x^2+2.5x-1



      /*  Polynom[] test = Polynom.smallPol(new double[]{0, 1, 4});
        System.out.println(Arrays.toString(test));
        double[] test1 = Polynom.divider(new double[]{0, 1, 4});
        System.out.println(Arrays.toString(test1));
        Polynom[] testnumerator = Polynom.numerator(new double[]{0, 1, 4});
        System.out.println(Arrays.toString(testnumerator));
        Polynom[] testLagrcoef = Polynom.lagrCoefs(new double[]{0, 1, 4});
        System.out.println(Arrays.toString(testLagrcoef));
        Polynom testlagr = Polynom.lagrange1(new double[]{0, 1, 4}, new double[]{-1, 1, 1});
        System.out.println(Arrays.toString(testlagr.coef));*/


        double[] points = new double[0];
        double[] func = new double[0];

        while (true) {
            try {
                System.out.println("type in values of X:");
                points = Polynom.getDoublefromString();
                System.out.println("type in values of Function:");
                func = Polynom.getDoublefromString();
                Polynom lag = Polynom.lagrange(points, func);
                System.out.println("L(x)= " + lag.toString());
                // System.out.println(Arrays.toString(lag.coef));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongXException e) {
                System.out.println("incorrect X values, try again ");
            } catch (WrongLengthException e) {
                System.out.println("incorrect number of X and Function values, try again");
            }

        }


    }
}
