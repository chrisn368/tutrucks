/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.List;

/**
 * This interface should be implemented by all classes which can have reviews written about them. 
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 */
public interface Reviewable {
    /**
     * Associates a review with an object.
     * @param r The review to be associated
     */
    public void addReview(Review r);
    /**
     * Calculates the average total score of a reviewable entity.
     * @return the average total score of a reviewable entit
     */
    public int getScore();
    /**
     * Loads the reviewable entities reviews from the database.
     * @return the reviewable entities reviews from the database
     */
    public List<? extends Review> reloadReviews();
    /**
     * Loads the reviewable entities reviews from the database if they have not been loaded previously. Otherwise, returns the previously loaded reviews.
     * @return the reviewable entities reviews from the database
     */
    public List<? extends Review> loadReviews();
    /**
     * Checks the validity of the current reviews for this reviewable.
     * @return true if the reviews are valid, false otherwise.
     */
    boolean reviewsValid();
}
