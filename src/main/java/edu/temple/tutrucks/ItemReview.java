package edu.temple.tutrucks;
// Generated Feb 15, 2016 6:30:46 PM by Hibernate Tools 4.3.1

/**
     * ItemReview generated by hbm2java
     * @author Nick Dell'Osa
     * @version %PROJECT_VERSION%
     * 
     * Represents a review for an item. It's mapping file is ItemReview.hbm.xml
 */
public class ItemReview extends Review<Item> implements java.io.Serializable {

    private int id;

    /**
     * Empty constructor required by Hibernate
     */
    public ItemReview() {
    }
    /**
     * Returns an item review's ID. Required by Hibernate
     * @return this review's ID
     */
    public int getId() {
        return this.id;
    }
    /**
     * Sets an item review's ID. Required by Hibernate
     * @param id the ID of this item review.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Returns the item in the review. Required by Hibernate
     * @return the item in the review.
     */
    public Item getItem() {
        return this.reviewed;
    }
    /**
     * Sets the item in the review. Required by Hibernate
     * @param i the item in the review.
     */
    public void setItem(Item i) {
        this.reviewed = i;
    }
    
}


