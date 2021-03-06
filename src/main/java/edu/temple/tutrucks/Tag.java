package edu.temple.tutrucks;
// Generated Feb 15, 2016 6:30:46 PM by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Tags generated by hbm2java
 * 
 * This class represents the tags that can be attached to any class implementing the Taggable interface. Its mapping file is Tag.hbm.xml
 * 
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 * 
 */
public class Tag implements java.io.Serializable, java.lang.Comparable, Searchable, Modifiable {

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
       this();
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
    public void addEntity(Truck t) {
        trucks.add(t);
    }
    
    public void addEntity(Item i) {
        items.add(i);
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
        if (this.getItems() != null)
            retval.addAll(this.getItems());
        if (this.getTrucks() != null)
            retval.addAll(this.getTrucks());
        return retval;
    }
    /**
     * Returns the number of entities associated with this tag.
     * @return the number of entities associated with this tag
     */
    public int numEntities() {
        return this.getItems().size() + this.getTrucks().size();
    }
    /**
     * Compares the tags based on their popularity first, then their name.
     * @param o the object to be compared to this tag
     * @return 1 if this tag is more popular or equally popular and higher in the alphabet, 0 if they are the same; -1 otherwise
     */
    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 0;
        } else if (o instanceof Tag) {
            Tag t = (Tag) o;
            if (this.numEntities() > t.numEntities()) {
                return 1;
            } else if (this.numEntities() < t.numEntities()) {
                return -1;
            } else {
                return this.tagName.compareTo(t.tagName);
            }
        }
        return -1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Tag) {
            Tag t = (Tag) o;
            return t.id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.tagName);
        return hash;
    }

    @Override
    public String getSearchName() {
        return this.tagName;
    }
    /**
     * Retrieves a list of tags that match the specified terms.
     * @param criteria the String to match
     * @return a list of tags that match the specified terms
     */
    public static List<Tag> searchTags(String criteria) {
        String terms = criteria.toLowerCase();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Tag where lower(tagName) like '%" + terms + "%'"
        );
        List l = q.list();
        session.close();
        ArrayList<Tag> results = new ArrayList<>(l.size());
        for (Searchable s : Searchable.SearchOrganizer.organize(l, terms)) results.add((Tag)s);
        return results;
    }
    /**
     * Retrieves the tag with the specified name. If the tag does not exist and the createIfDoesNotExist parameter is true, this tag will be created.
     * @param name the name of the tag to retrieve
     * @param createIfDoesNotExist whether or not to create the tag if it does not exist
     * @return the Tag object with the specified name
     */
    public static Tag retrieveTag(String name, boolean createIfDoesNotExist) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Tag where tagName = '" + name + "'"
        );
        Tag retval = (Tag) q.uniqueResult();
        session.close();
        if (retval == null && createIfDoesNotExist) {
            Tag aretval = new Tag(name);
            aretval.save();
            return aretval.loadTaggedEntities();
        } else {
            return retval.loadTaggedEntities();
        }
    }
    /**
     * Saves this tag object to the database and assigns it an ID value.
     */
    @Override
    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(this);
        for (Taggable t : this.getAllTaggedEntities()) session.saveOrUpdate(t);
        session.getTransaction().commit();
        session.close();
    }
    /**
     * Removes this tag object from the database.
     */
    @Override
    public void delete() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(this);
        for (Taggable t : this.getAllTaggedEntities()) session.saveOrUpdate(t);
        session.getTransaction().commit();
        session.close();
    }

    public Tag loadTaggedEntities() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Tag retval = (Tag) session.get(Tag.class, this.getId());
        Hibernate.initialize(retval.getTrucks());
        Hibernate.initialize(retval.getItems());
        session.getTransaction().commit();
        session.close();
        retval.numEntities();
        return retval;
    }
}


