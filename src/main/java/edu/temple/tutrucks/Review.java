/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.Date;
import java.util.Objects;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 * The superclass for all review objects. Contains common traits of reviews.
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 * @param <T> The type of item being reviewed. Must implement the reviewable interface
 */
public abstract class Review<T extends Reviewable> implements Modifiable{
    
     private int id;
     private User user;
     private int reviewStars;
     private String reviewText;
     private Date reviewDate;
     
     /**
      * Default empty constructor.
      */
     public Review() {
         this.id = 0;
     }

     /**
      * Returns the reviewed entity. Required by Hibernate
      * @return the object that is the subject of the review
      */
    public abstract T getReviewed();
    /**
     * Sets the object being reviewed. Required by Hibernate
     * @param reviewed the object being reviewed
     */
    public abstract void setReviewed(T reviewed);
    /**
     * Returns the user who wrote the review. Required by Hibernate
     * @return the user who wrote the review
     */
    public User getUser() {
        return user;
    }
    /**
     * Sets the user who wrote the review. Required by Hibernate
     * @param user the user who wrote the review
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Returns the number of stars in the review. Required by Hibernate
     * @return the number of stars given in the review
     */
    public int getReviewStars() {
        return reviewStars;
    }
    /**
     * Sets the number of stars in the review. Must be between 0 and 10. Required by Hibernate
     * @param reviewStars the number of stars given in the review. Must be between 0 and 10.
     */
    public void setReviewStars(int reviewStars) throws IllegalArgumentException {
        if (reviewStars > 10 || reviewStars < 0) {
            throw new IllegalArgumentException("Review rating must be between 0 and 10");
        }
        this.reviewStars = reviewStars;
    }
    /**
     * Returns the text of the review. Required by Hibernate
     * @return the text of the review
     */
    public String getReviewText() {
        return reviewText;
    }
    /**
     * Sets the text of the review. Required by Hibernate
     * @param reviewText the text of the review
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    /**
     * Returns the date on which the review was written. Required by Hibernate
     * @return the date of the review
     */
    public Date getReviewDate() {
        return reviewDate;
    }
    /**
     * Sets the date of the review. Required by Hibernate
     * @param reviewDate the date of the review
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
    /**
     * Returns a review's ID. Required by Hibernate
     * @return this review's ID
     */
    public Integer getId() {
        return this.id;
    }
    /**
     * Sets a review's ID. Required by Hibernate
     * @param id the ID of this review.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Saves this review object to the database and assigns it an ID value.
     */
    @Override
    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (this.id == 0) {
            Criteria criteria = session.createCriteria(Review.class).setProjection(Projections.max("id"));
            Object result = criteria.uniqueResult();
            int max = result == null ? 0 : (Integer)result;
            this.setId(max+1);
        }
        session.saveOrUpdate(this);
        session.saveOrUpdate(this.getReviewed());
        session.getTransaction().commit();
        session.close();
        this.getUser().save();
    }
    /**
     * Removes this review object from the database.
     */
    @Override
    public void delete() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(this);
        session.saveOrUpdate(this.getReviewed());
        session.saveOrUpdate(this.getUser());
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Review) {
            Review r = (Review) o;
            return this.getClass().equals(r.getClass()) && this.id == r.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.reviewStars;
        hash = 41 * hash + Objects.hashCode(this.reviewText);
        hash = 41 * hash + Objects.hashCode(this.reviewDate);
        return hash;
    }
}
