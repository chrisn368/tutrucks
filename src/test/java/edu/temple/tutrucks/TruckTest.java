/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.mockito.Mockito.*;

/**
 *
 * @author michn_000
 */
public class TruckTest {
    private TruckReview review;
    private Tag tag;
    private Truck truck;
    private Set<Truck> truckSet;
    private List<Tag> tagList;
    private static User realUser;
    private static Tag realTag;
    
    
    @BeforeClass
    public static void setup() {
        realUser = User.createUser("trucktest@test.com", "password", false, null, null, null);
        realTag = Tag.retrieveTag("truck test tag", true);
    }
    
    @AfterClass
    public static void tearDown() {
        realUser.delete();
        realTag.delete();
    }
    
    @Before
    public void setUpMock() {
        review = mock(TruckReview.class);   
        tag = mock(Tag.class);
        truck = new Truck();
        truckSet = new HashSet();       
        tagList = new ArrayList();
        for (int i =0; i < 5; i++) {
            Tag tempTag = mock(Tag.class);
            tagList.add(tempTag);
        }
    }
    
    @Test
    public void testMockCreation(){
        assertNotNull(review);
        assertNotNull(tag);
        assertNotNull(truck);
        assertNotNull(truckSet);
        assertNotNull(tagList);
    }
    
    @Test
    public void testGetScore() {
        TruckReview r1 = new TruckReview();
        TruckReview r2 = new TruckReview();
        r1.setReviewStars(10);
        r2.setReviewStars(0);
        r1.setTruck(truck);
        r2.setTruck(truck);
        List<TruckReview> reviewList = new ArrayList<>(2);
        reviewList.add(r1);
        reviewList.add(r2);
        truck.setTruckReviews(reviewList);
        assertEquals(5, truck.getScore());
    }
    
    
    //INTEGRATION TESTING
    
    @Test
    public void testGetTrucksByID() {
        Truck realTruck = Truck.getTruckByID(1, true, true);
        assertTrue(realTruck.getId() > 0);
        assertTrue(realTruck.getTruckName() != null);
        assertTrue(realTruck.getMenus() != null);
        assertTrue(realTruck.getTruckReviews() != null);
        assertTrue(realTruck.getTags() != null);
    }
    
    @Test
    public void testSearchTrucks() {
        String searchTerms = "c";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(
                "from Truck where truckName like '%" + searchTerms + "%'"
        );
        List l = q.list();
        session.close();
        List<Searchable> testResults = Searchable.SearchOrganizer.organize(l, searchTerms);
        List<Truck> results = Truck.searchTrucks(searchTerms);
        for (int i = 0; i < testResults.size(); i++) {
            assertEquals(testResults.get(i).getSearchName(), results.get(i).getSearchName());
        }
    }

    @Test
    public void testAddReviewIntegration() {
        Truck realTruck = Truck.getTruckByID(2);
        Review realTruckReview = new TruckReview();
        realTruckReview.setReviewText("review text");
        realTruckReview.setReviewDate(new Date());
        realTruckReview.setReviewStars(2);
        realTruckReview.setReviewed(realTruck);
        realTruckReview.setUser(realUser);
        realTruckReview.save();
        realTruck = realTruck.loadReviews();
        assertEquals(1, realTruck.getTruckReviews().size());
        assertTrue(realTruck.getTruckReviews().contains(realTruckReview));
        realTruckReview.delete();
    }
    
    
    @Test
    public void testAddTagIntegration() {
        Truck realTruck = Truck.getTruckByID(1);
        realTag.addEntity(realTruck);
        realTag.save();
        assertTrue(realTruck.loadTags().getTags().contains(realTag));
    }
    
    @Test
    public void testAddMultipleTagsIntegration() {
        Tag tag1 = Tag.retrieveTag("truck test tag 1", true);
        Tag tag2 = Tag.retrieveTag("truck test tag 2", true);
        Truck realTruck = Truck.getTruckByID(1);
        realTruck.addTags(tag1, tag2);
        tag1.save();
        tag2.save();
        Truck rt = realTruck.loadTags();
        assertTrue(rt.getTags().contains(tag1));
        assertTrue(rt.getTags().contains(tag2));
        tag1.delete();
        tag2.delete();
    }
    
    @Test
    public void testLoadReviews() {
        Truck realTruck = Truck.getTruckByID(1, false, false);
        TruckReview realFakeReview = new TruckReview();
        realFakeReview.setTruck(realTruck);
        realFakeReview.setUser(realUser);
        realFakeReview.setReviewDate(new Date());
        realFakeReview.setReviewStars(5);
        realFakeReview.setReviewText("fake review");
        realFakeReview.save();
        assertTrue(realTruck.loadReviews().getTruckReviews().contains(realFakeReview));
        realFakeReview.delete();
    }
    
    @Test
    public void testLoadTruckByName() {
        int truckID = 2;
        Truck currentTruck = Truck.getTruckByID(truckID);
        Truck currentTruck2 = Truck.getTruckByName(currentTruck.getSearchName());
        assertEquals(currentTruck, currentTruck2);
    }
    
    @Test
    public void testEqualsIntegration() {
        Truck realTruck = Truck.getTruckByID(1);
        assertTrue(realTruck.equals(Truck.getTruckByID(1)));
        assertFalse(realTruck.equals(Truck.getTruckByID(2)));
        Object testObject = new Object();
        assertFalse(realTruck.equals(testObject));
    }
    
    @Test
    public void testAllTruckNames() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select truckName from Truck order by truckName ASC");
        List l = q.list();
        session.close();
        List<String> lreal = Truck.getAllTruckNames();
        assertEquals(l.size(), lreal.size());
        for (int i=0; i < l.size(); i++) assertEquals(l.get(i), lreal.get(i));
    }
}

