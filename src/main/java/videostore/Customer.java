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

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

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
        Enumeration rentals = _rentals.elements();
        String result = "main.java.videostore.Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // show figures for this rental

            /*  Of course there is a performance price to pay; here the charge is now calculated twice.
              But it is easy to optimize that in the rental class,
              and you can optimize much more effectively when the code is properly factored.
            */
            result += "\t" + each.getMovie().getTitle()+ "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // show figures for each rental
            result += each.getMovie().getTitle()+ ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }

        // add footer lines
        result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</EM> frequent renter points<P>";
        return result;
    }
}
