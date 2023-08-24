package no.accelerate.chinook.models;

import java.util.List;

public class CustomerGenre {

    private Long id;
    private String genre;
    private int count;

    public CustomerGenre(Long id, String genre, int count) {
        this.id = id;
        this.genre = genre;
        this.count = count;
    }


    public String toString() {
        return "CustomerGenre{" +
                "id = " + id +
                ", genre = " + genre +  '\'' +
                ", count = " + count +
                '}';

    }
}
