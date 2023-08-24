package no.accelerate.chinook.models;

public class CustomerCountry {
        private String countryName;
        private int customerCount;


    public CustomerCountry(String countryName, int customerCount) {
        this.countryName = countryName;
        this.customerCount = customerCount;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public String toString() {
        //Formatted string representation of customer
        return "CustomerCountry{" +
                ", countryName=" + countryName +  '\'' +
                ", customerCount=" + customerCount +
                '}';

    }
}
