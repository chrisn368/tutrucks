package edu.temple.tutrucks;
// Generated Feb 15, 2016 6:30:46 PM by Hibernate Tools 4.3.1

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Truck generated by hbm2java
 * 
 * This class represents a food truck. Its mapping file is Truck.hbm.xml
 * 
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class Truck implements java.io.Serializable, Reviewable, Taggable, Searchable, Visualizable {

     private int id;
     private String truckName;
     private double latitude;
     private double longitude;
     private Time openingTime;
     private Time closingTime;
     private String avatar;
     private boolean takesCard;
     private List<TruckReview> truckReviews;
     private List<Menu> menus;
     private Set<Tag> tags;

     /**
      * Required empty constructor
      */
    public Truck() {
    }
    /**
     * Returns the ID of this truck. Required by Hibernate
     * @return the ID of this truck
     */
    public int getId() {
        return this.id;
    }
    /**
     * Sets the ID of this truck. Required by Hibernate
     * @param id the ID of this truck
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of this truck. Required by Hibernate
     * @return the name of this truck
     */
    public String getTruckName() {
        return this.truckName;
    }
    /**
     * Sets the name of this truck. Required by Hibernate
     * @param truckName the name of this truck
     */
    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }
    /**
     * Returns the latitude of this truck. Required by Hibernate
     * @return the latitude of this truck
     */
    public double getLatitude() {
        return this.latitude;
    }
    /**
     * Sets the latitude of this truck. Required by Hibernate
     * @param latitude the latitude of this truck
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    /**
     * Returns the longitude of this truck. Required by Hibernate
     * @return the longitude of this truck
     */
    public double getLongitude() {
        return this.longitude;
    }
    /**
     * Sets the longitude of this truck. Required by Hibernate
     * @param longitude the longitude of this truck
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    /**
     * Returns the list of reviews for this truck. Required by Hibernate
     * @return the list of reviews for this truck
     */
    public List getTruckReviews() {
        return this.truckReviews;
    }
    /**
     * Returns the list of menus/food categories at this truck. Required by Hibernate
     * @return the list of menus for this truck.
     */
    public List getMenus() {
        return this.menus;
    }
    /**
     * Adds a review for this truck.
     * @param r the review for this truck
     */
    @Override
    public void addReview(Review r) {
        r.setReviewed(this);
    }
    /**
     * Returns the set of tags attached to this truck. Required by Hibernate
     * @return the set of tags attached to this truck
     */
    @Override
    public Set<Tag> getTags() {
        return this.tags;
    }
    /**
     * Attaches one or more tags to this truck.
     * @param t the tag(s) to be attached to this truck
     */
    @Override
    public void addTags(Tag... t) {
        for (Tag x : t) {
            x.addEntity(this);
        }
    }
    /**
     * Sets the list of truck reviews for this truck. Required by Hibernate
     * @param truckReviews the list of truck reviews for this truck
     */
    public void setTruckReviews(List<TruckReview> truckReviews) {
        this.truckReviews = truckReviews;
        this.removeNullReviews();
    }
    /**
     * Sets the list of menus for this truck. Required by Hibernate
     * @param menus the list of menus for this truck
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    /**
     * Sets the set of tags attached to this truck. Required by Hibernate
     * @param tags the set of tags attached to this truck
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    /**
     * Returns the time this truck opens on normal weekdays. Required by Hibernate
     * @return the time this truck opens on normal weekdays
     */
    public Time getOpeningTime() {
        return openingTime;
    }
    /**
     * Sets the time this truck opens on normal weekdays. Required by Hibernate
     * @param openingTime the time this truck opens on normal weekdays
     */
    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }
    /**
     * Returns the time this truck closes on normal weekdays. Required by Hibernate.
     * @return the time this truck closes on normal weekdays
     */
    public Time getClosingTime() {
        return closingTime;
    }
    /**
     * Sets the time this truck closes on normal weekdays. Required by Hibernate.
     * @param closingTime the time this truck closes on normal weekdays
     */
    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }
    /**
     * Gets whether or not this truck takes credit cards or is cash-only. Required by Hibernate.
     * @return true if this truck takes cards, false if it is cash-only
     */
    public boolean isTakesCard() {
        return this.takesCard;
    }
    /**
     * Sets whether or not this truck takes credit cards or is cash-only. Required by Hibernate.
     * @param takesCard whether or not this truck takes cards or is cash-only
     */
    public void setTakesCard(boolean takesCard) {
        this.takesCard = takesCard;
    }

    @Override
    public String getSearchName() {
        return this.truckName;
    }
    
    @Override
    public String getAvatar() {
        return this.avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**
     * Retrieves a list of all trucks in the database.
     * @return a list of all trucks in the database
     */
    public static List<Truck> getAllTrucks() {
        return searchTrucks("");
    }
    /**
     * Retrieves a list of trucks that match the specified terms.
     * @param criteria the String to match
     * @return a list of trucks that match the specified terms
     */
    public static List<Truck> searchTrucks(String criteria) {
        String terms = criteria.toLowerCase();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Truck" + (terms.isEmpty() ? "" : " where lower(truckName) like '%" + terms + "%'")
        );
        List l = q.list();
        session.close();
        ArrayList<Truck> results = new ArrayList<>(l.size());
        for (Searchable s : Searchable.SearchOrganizer.organize(l, terms)) results.add((Truck)s);
        return results;
    }
    /**
     * Retrieves the Truck with the specified name.
     * @param name the name of the Truck object to retrieve.
     * @return the truck with the specified name
     */
    public static Truck getTruckByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Truck where truckName='" + name + "'"
        );
        Truck retval = (Truck) q.uniqueResult();
        session.close();
        return retval;
    }
    /**
     * Retrieves the truck with the specified id.
     * @param id the id of the Truck object to retrieve.
     * @param loadReviews true if you want to load reviews with this truck object, false otherwise
     * @param loadTags true if you want to load tags with this truck object, false otherwise
     * @return the truck with the specified id
     */
    public static Truck getTruckByID(int id, boolean loadReviews, boolean loadTags) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Truck retval = (Truck) session.get(Truck.class, id);
        if (loadReviews) {
            Hibernate.initialize(retval.getTruckReviews());
            retval.getTruckReviews().size();
            retval.removeNullReviews();
        }
        if (loadTags) {
            Hibernate.initialize(retval.getTags());
            retval.getTags().size();
        }
        session.close();
        return retval;
    }
    
    public static Truck getTruckByID(int id) {
        return Truck.getTruckByID(id, false, false);
    }
    
    @Override
    public int getScore() {
        if (truckReviews.isEmpty())
            return 0;
        double score = 0.0;
        for (TruckReview tr : truckReviews) {
            if (tr!=null){
                //System.out.println(tr.reviewText);
                //System.out.println(tr.reviewStars);
                score += (double)(tr.getReviewStars());
            }
        }
        score /= (double)truckReviews.size();
        //System.out.println(score);
        return (int) Math.round(score);
    }

    @Override
    public Truck loadReviews() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Truck retval = (Truck) session.get(Truck.class, this.getId());
        Hibernate.initialize(retval.getTruckReviews());
        session.getTransaction().commit();
        session.close();
        retval.getTruckReviews().size();
        this.setTruckReviews(retval.getTruckReviews());
        return retval;
    }

    @Override
    public Truck loadTags() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Truck retval = (Truck) session.get(Truck.class, this.getId());
        Hibernate.initialize(retval.getTags());
        session.getTransaction().commit();
        session.close();
        retval.getTags().size();
        this.setTags(retval.getTags());
        return retval;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Truck) {
            Truck t = (Truck) o;
            return this.id == t.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.truckName);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.openingTime);
        hash = 41 * hash + Objects.hashCode(this.closingTime);
        return hash;
    }

    public static List<String> getAllTruckNames() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select truckName from Truck order by truckName ASC");
        List l = q.list();
        session.close();
        return l;
    }
    

    @Override
    public void removeNullReviews() {
        if (this.getTruckReviews() != null) {
            for (int i=0; i < this.getTruckReviews().size(); i++) {
                if (this.getTruckReviews().get(i) == null) this.getTruckReviews().remove(i);
            }
        }
    }

}


