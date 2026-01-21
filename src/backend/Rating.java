package backend;

import java.util.Date;

public class Rating {
    private String text;
    private int rating;
    private Date date;
    private String username;

    public Rating(String text, int rating, Date date, String username) {
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
