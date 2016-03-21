package edu.temple.tutrucks;
// Generated Feb 15, 2016 6:30:46 PM by Hibernate Tools 4.3.1

import java.util.Set;
import java.util.HashSet;




/**
 * Tags generated by hbm2java
 * 
 * This class represents the tags that can be attached to any class implementing the Taggable interface. Its mapping file is Tag.hbm.xml
 * 
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 * 
 */
public class Tag implements java.io.Serializable, java.lang.Comparable, Searchable {

     private int id;
     private String tagName;
     private Set<Item> items;
     private Set<Truck> trucks;

     /**
      * Required empty constructor. 
      */
    public Tag() {
    }
    /**
     * Constructor that builds a tag object with just its name. Required by Hibernate.
     * @param tagName the name of this Tag object
     */
    public Tag(String tagName) {
       this.tagName = tagName;
    }
    /**
     * Returns the ID of this tag object. Required by Hibernate.
     * @return the ID of this tag object
     */
    public int getId() {
        return this.id;
    }
    /**
     * Sets the ID of this tag object. Required by Hibernate.
     * @param id the ID of this tag object.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of this tag object. Required by Hibernate.
     * @return the name of this tag object
     */
    public String getTagName() {
        return this.tagName;
    }
    /**
     * Sets the name of this tag object. Required by Hibernate.
     * @param tagName the name of this tag object
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    /**
     * Returns the set of food items associated with this tag. Required by Hibernate.
     * @return the set of food items associated with this tag
     */
    public Set getItems() {
        return this.items;
    }
    /**
     * Sets the set of food items associated with this tag. Required by Hibernate.
     * @param items the set of food items associated with this tag
     */
    public void setItems(Set<Item> items) {
        this.items = items;
    }
    /**
     * Attaches a taggable entity to this tag. 
     * @param t the taggable entity to be associated with this tag
     */
    public void addEntity(Taggable t) {
        if (!t.getTags().contains(this)) t.addTags(this);
        if (t.getClass() == Item.class)
            items.add((Item)t);
        else
            trucks.add((Truck)t);
    }
    /**
     * Returns the set of trucks associated with this tag. Required by Hibernate.
     * @return the set of trucks associated with this tag
     */
    public Set getTrucks() {
        return this.trucks;
    }
    /**
     * Sets the set of trucks associated with this tag. Required by Hibernate.
     * @param trucks the set of trucks associated with this tag
     */
    public void setTrucks(Set<Truck> trucks) {
        this.trucks = trucks;
    }
    /**
     * Returns all entities associated with this tag.
     * @return the set of all entities associated with this tag
     */
    public Set<Taggable> getAllTaggedEntities() {
        Set<Taggable> retval = new HashSet();
        retval.addAll(items);
        retval.addAll(trucks);
        return retval;
    }
    /**
     * Returns the number of entities associated with this tag.
     * @return the number of entities associated with this tag
     */
    public int numEntities() {
        return items.size() + trucks.size();
    }
    /**
     * Compares the tags based on their popularity first, then their name.
     * @param o the object to be compared to this tag
     * @return 1 if this tag is more popular or equally popular and higher in the alphabet; -1 otherwise (since no two tags can be identical, 0 is not a possible value)
     */
    @Override
    public int compareTo(Object o) {
        try {
            Tag t = (Tag) o;
            if (this.numEntities() == t.numEntities())
                return this.getTagName().compareTo(t.getTagName());
            return Integer.compare(this.numEntities(), t.numEntities());
        } catch (ClassCastException cce) {
            return 0;
        }
    }

    @Override
    public String getSearchName() {
        return this.tagName;
    }


}


