package edu.ung.mccb.csci.csci3300.customer_review.model;

public class Review {

    private int reviewID;
    private String reviewText;
    private String IP;
    private int rating;
    private boolean isValid;

    public Review () {
        // TODO: Constructor body
    }

    public Review (String text, String IP, int rating) {
        // TODO: constructor body
    }

    public void writeToDatabase () {
        // TODO: method body
    }

    public void writeToDatabase (String text, String IP, int rating) {
        // TODO: method body
    }

    public void loadFromDatabase () {
        // TODO: method body
    }

    public void loadFromDatabase (int ID) {
        // TODO: method body
    }

    // accessor/mutator methods
    public int getReviewID () {
        return reviewID;
    }

    public void setReviewID (int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewText () {
        return reviewText;
    }

    public void setReviewText (String reviewText) {
        this.reviewText = reviewText;
    }

    public String getIP () {
        return IP;
    }

    public void setIP (String IP) {
        this.IP = IP;
    }

    public int getRating () {
        return rating;
    }

    public void setRating (int rating) {
        this.rating = rating;
    }

    public boolean getIsValid () {
        return isValid;
    }

    public void setIsValid (boolean isValid) {
        this.isValid = isValid;
    }
}
