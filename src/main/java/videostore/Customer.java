package main.java.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer (String name){
        _name = name;
    };

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName (){
        return _name;
    };

    /*
    *
    * Initial Comment
    *
    * Statement routine does far too much
    * Many of the things that it does should really be done by the other classes.
    * 변경이 생길 경우, 기존 코드는 재사용될 수 없음
    * 변경하고자 할 때, 여러 선택지가 존재할 것이고 이로 인해 다른 부분에도 영향을 주거나, 계속해서 변경할 수 밖에 없고 이로 인한 리소스 소모
    * */

    /*
    * Decomposing and Redistributing the Statement Method
    *
    * First I need to look in the fragment for any variables that are local in scope to the method we are looking at, the local variables and parameters.
    * This segment of code uses two: `each` and `thisAmount`.
    *
    * */
    public String statement(){
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "main.java.videostore.Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            //determine amounts for each line
            thisAmount = amountFor(each);

            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    &&
                    each.getDaysRented() > 1) frequentRenterPoints ++;

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    // 1. thisAmount가 기존 함수(local variable)에서 수정되는 것을 분리하였음
    // 2. local variable 명을 local context 에 맡게 수정하였음
    private double amountFor(Rental aRental) {
        double result = 0;
        switch (aRental.getMovie().getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                if(aRental.getDaysRented() > 2)
                    result += (aRental.getDaysRented() -2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if(aRental.getDaysRented() > 3)
                    result += (aRental.getDaysRented() -3) * 1.5;
                break;
        }
        return result;
    }
}
