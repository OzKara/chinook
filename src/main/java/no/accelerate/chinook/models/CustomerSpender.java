package no.accelerate.chinook.models;

public class CustomerSpender {
    private Long id;
    private String firstName;
    private String lastName;
    private double totaltSpent;

    public CustomerSpender(Long id, String firstName, String lastName, double totaltSpent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totaltSpent = totaltSpent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotaltSpent() {
        return totaltSpent;
    }

    public void setTotaltSpent(double totaltSpent) {
        this.totaltSpent = totaltSpent;
    }

    public String toString() {
        return "CustomerSpender{" +
                "id=" + id +
                "firstName=" + firstName +  '\'' +
                "lastName=" + lastName + '\'' +
                "totalSpent=" + totaltSpent +
                '}';

    }
}
