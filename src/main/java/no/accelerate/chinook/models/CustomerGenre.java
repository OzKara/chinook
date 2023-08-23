package no.accelerate.chinook.models;

import java.util.List;

public class CustomerGenre {

    private Long id;
    private String firstName;
    private String lastName;
    private List<String> popularGenres;

    public CustomerGenre(Long id, String firstName, String lastName, List<String> popularGenres) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.popularGenres = popularGenres;
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

    public List<String> getPopularGenres() {
        return popularGenres;
    }

    public void setPopularGenres(List<String> popularGenres) {
        this.popularGenres = popularGenres;
    }

    public String toString() {
        return "CustomerGenre{" +
                "id=" + id +
                "firstName=" + firstName +  '\'' +
                "lastName=" + lastName + '\'' +
                "popularGenres=" + popularGenres +
                '}';

    }
}
