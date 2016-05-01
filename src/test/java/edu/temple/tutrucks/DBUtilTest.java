/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import edu.temple.tutrucks.Searchable.SearchOrganizer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nickdellosa
 */
public class DBUtilTest {
    
    public DBUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void searchAllTest() {
        try {
            String searchTerms = "chicken";
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query q = session.createQuery(
                    "from edu.temple.tutrucks.Searchable s where ("
                            + "s.id in (select tr.id from Truck tr where lower(tr.truckName) like '%" + searchTerms + "%') or "
                            + "s.id in (select it.id from Item it where lower(it.itemName) like '%"  + searchTerms + "%') or "
                            + "s.id in (select ta.id from Tag ta where lower(ta.tagName) like '%" + searchTerms + "%'))"
            );
            List l = q.list();
            session.close();
            List<Searchable> testResults = SearchOrganizer.organize(l, searchTerms);
            List<Searchable> results = DBUtils.searchAll(searchTerms, null);
            for (int i=0; i < testResults.size(); i++) assertEquals(testResults.get(i).getSearchName(), results.get(i).getSearchName());
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail();
        }
    }
}
