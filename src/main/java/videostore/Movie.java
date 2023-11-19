package main.java.videostore;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }

    //  In most cases a method should be on the object whose data it uses, thus the method should be moved to the rental class.
    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                if(daysRented > 2)
                    result += (daysRented -2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += daysRented * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if(daysRented > 3)
                    result += (daysRented -3) * 1.5;
                break;
        }
        return result;
    }

    int getFrequentRenterPoints(int daysRented) {
        if ((getPriceCode() == Movie.NEW_RELEASE)
                &&
                daysRented > 1) return 2;
        else
            return 1;
    }
}
