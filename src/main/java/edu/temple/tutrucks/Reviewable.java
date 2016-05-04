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
     * @return the average total score of a reviewable entity
     */
    public int getScore();
    /**
     * Loads the reviewable entities reviews from the database.
     * @return the reviewable object with its reviews loaded from the database
     */
    public Reviewable loadReviews();
    /**
     * Removes null review values from the review list.
     */
    public void removeNullReviews();
}
