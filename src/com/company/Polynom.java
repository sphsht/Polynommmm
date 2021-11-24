package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Polynom {

    double[] coef;

    public Polynom(double[] coef) {
        this.coef = coef;     //this. это к параметрам в классе(строка 5), coef - переданный аргумент
    }


    @Override
    public String toString() {
        String result = "";

        if (coef.length == 1) {
            if (coef[0] == 0) {
                result = "0.0";
            } else {
                result += coef[0];
            }
        }

        if (coef.length == 2) {
            if (coef[1] > 0) {
                if (coef[1] == 1) {
                    result += "x";
                } else
                    result += coef[1] + "x";
            }
            if (coef[1] == 0) {
                result = result;
            }
            if (coef[1] < 0) {
                if (coef[1] == -1) {
                    result += "-x";
                } else
                    result += coef[1] + "x";
            }

            if (coef[0] > 0) {
                result += "+" + coef[0];
            }
            if (coef[0] == 0) {
                result = result;
            }
            if (coef[0] < 0) {
                result += coef[0];
            }
            if (coef[0] == 0 && coef[1] == 0) {
                result = "0.0";
            }

        }

        if (coef.length > 2) {
            if (coef[coef.length - 1] == 1) {
                result = "x^" + (coef.length - 1);
            }
            if (coef[coef.length - 1] == -1) {
                result = "-x^" + (coef.length - 1);
            }
            if (coef[coef.length - 1] == 0) {
                result = "";
            }
            if (coef[coef.length - 1] != 1 && coef[coef.length - 1] != -1 && coef[coef.length - 1] != 0) {
                result = coef[coef.length - 1] + "x^" + (coef.length - 1);
            }

            for (int i = coef.length - 2; i > 1; i--) {

                if (coef[i] == 0) {
                    result += "";
                }

                if (coef[i] > 0) {
                    if (coef[i] == 1) {
                        result += "+x^" + i;
                    } else
                        result += "+" + coef[i] + "x^" + i;
                }

                if (coef[i] < 0) {
                    if (coef[i] == -1) {
                        result += "-x^" + i;
                    } else
                        result += coef[i] + "x^" + i;
                }

            }
            if (coef[1] > 0) {
                if (coef[1] == 1) {
                    result += "+x";
                } else
                    result += "+" + coef[1] + "x";
            }
            if (coef[1] == 0) {
                result = result;
            }
            if (coef[1] < 0) {
                if (coef[1] == -1) {
                    result += "-x";
                } else
                    result += coef[1] + "x";
            }

            if (coef[0] > 0) {
                result += "+" + coef[0];
            }
            if (coef[0] == 0) {
                result = result;
            }
            if (coef[0] < 0) {
                result += coef[0];
            }
        }

        return result;
    }


    public static Polynom add(Polynom a, Polynom b) {

        int max = Math.max(a.coef.length, b.coef.length);
        double[] newcoef = new double[max];
        if (a.coef.length == b.coef.length) {
            for (int i = 0; i < newcoef.length; i++) {
                newcoef[i] = a.coef[i] + b.coef[i];
            }
        }
        if (a.coef.length > b.coef.length) {
            for (int i = 0; i < b.coef.length; i++) {
                newcoef[i] = a.coef[i] + b.coef[i];
            }
            for (int i = b.coef.length; i < a.coef.length; i++) {
                newcoef[i] = a.coef[i];
            }
        }

        if (b.coef.length > a.coef.length) {
            for (int i = 0; i < a.coef.length; i++) {
                newcoef[i] = a.coef[i] + b.coef[i];
            }
            for (int i = a.coef.length; i < b.coef.length; i++) {
                newcoef[i] = b.coef[i];
            }
        }
        Polynom c = new Polynom(newcoef);
        return c;
    }


    public Polynom multNum(double n) {//умножение на число
        double[] newcoef = new double[coef.length];
        for (int i = coef.length - 1; i > -1; i--) {
            newcoef[i] = coef[i] * n;
        }
        return new Polynom(newcoef);
    }


    public static Polynom mult(Polynom a, Polynom b) { //умножение многочленов

        int size = a.coef.length + b.coef.length - 1;
        double[] newcoef = new double[size];
        for (int i = 0; i < a.coef.length; i++) {
            for (int j = 0; j < b.coef.length; j++) {
                newcoef[i + j] += a.coef[i] * b.coef[j];

            }
        }


        return new Polynom(newcoef);
    }


    public static double pointVal(Polynom a, double x0) { //значение полинома в точке (моеб работает)

        double val = 0;
        for (int i = 0; i < a.coef.length; i++) {
            val += a.coef[i] * (Math.pow(x0, i));
        }
        return val;
    }


    public double getValue(double x) { // значение полинома в точке
        double result = 0;
        for (int i = 0; i < coef.length; i++) {
            result += power(x, i) * coef[i];
        }
        return result;
    }


    public double power(double x, int n) { // вспомогательный метод возведение в степень
        double result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }


    public static Polynom lagrange(double[] points, double[] func) {// вычисление полинома Лагранжа
        xExceptions(points);
        checkDimensions(points, func);
        Polynom[] lagr = Polynom.lagrCoefs(points);
        Polynom lagrPol = new Polynom(new double[]{0});
        for (int i = 0; i < points.length; i++) {
            lagrPol = Polynom.add(lagrPol, lagr[i].multNum(func[i]));
        }
        return lagrPol;
    }


    public static Polynom[] lagrCoefs(double[] points) {// вычисление всех l_k
        Polynom[] num = Polynom.numerator(points); //посчитали все числители
        double[] div = Polynom.divider(points);// посчитали все делители
        Polynom[] lagr = new Polynom[points.length];
        for (int i = 0; i < points.length; i++) {
            lagr[i] = num[i].multNum(1 / div[i]);
        }
        return lagr;
    }


    public static Polynom[] smallPol(double[] points) { //создаем массив полиномов (x-x_k)
        Polynom[] pol = new Polynom[points.length];
        double[] newcoef = new double[2];
        for (int i = 0; i < points.length; i++) {
            pol[i] = new Polynom(new double[]{-1 * points[i], 1});
        }
        return pol;
    }


    public static double[] divider(double[] points) { //значения знаменателя (x1-x2)(x1-x3)...(x1-xn) просто числа
        double[] dividers = new double[points.length];    //для каждго k находим числитель и записываем в массив
        for (int i = 0; i < points.length; i++) {
            dividers[i] = 1;
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    dividers[i] *= points[i] - points[j];
                }
            }
        }
        return dividers;
    }


    public static Polynom[] numerator(double[] points) { // вычисление всех числителей (x-x1)..(x-x_j-1)(x-x_j+1)..
        Polynom[] smpols = Polynom.smallPol(points); // все (x-x_k)
        Polynom[] numerators = new Polynom[points.length];
        for (int i = 0; i < points.length; i++) {
            numerators[i] = new Polynom(new double[]{1});
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    numerators[i] = Polynom.mult(numerators[i], smpols[j]);
                }
            }
        }
        return numerators;
    }


    /*public static Polynom lagrange1(double[] points, double[] func) {// вычисление полинома Лагранжа1, СЛОМАЛСЯ НЕ РАБОТАЕТ
        //  xExceptions(points);
        // checkDimensions(points, func);
        Polynom lagrPol = new Polynom(new double[]{0});
        Polynom[] pol = new Polynom[points.length];
        for (int i = 0; i < points.length; i++) {
            Polynom begg = new Polynom(new double[]{1});
            pol[i] = new Polynom(new double[]{-1 * points[i], 1});
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    begg = Polynom.mult(begg, pol[i].multNum(1 / (points[i] - points[j])));
                }
            }
            lagrPol = Polynom.add(lagrPol, begg.multNum(func[i]));
        }
        return lagrPol;
    }*/

    public static void xExceptions(double[] points) {

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j && points[i] == points[j]) {
                    throw new WrongXException();
                }
            }
        }


    }

    public static void checkDimensions(double[] points, double[] func) {
        if (points.length != func.length) {
            throw new WrongLengthException();
        }
    }


    public static double[] getDoublefromString() throws IOException { //преобразует строчку в double массив
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] chars = s.split(" ");
        double[] pcoefs = new double[chars.length];
        for (int i = 0; i < chars.length; i++) {
            pcoefs[i] = Double.parseDouble(chars[i]);
        }

        return pcoefs;
    }

}
