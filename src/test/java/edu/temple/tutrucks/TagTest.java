/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.Set;
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
public class TagTest {
    
    private static Tag realTag;
    private static final String TAGNAME = "vegan";
    
    public TagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        realTag = new Tag();
        realTag.setTagName(TAGNAME);
        realTag.save();
    }
    
    @AfterClass
    public static void tearDownClass() {
        realTag.delete();
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
    public void testAddEntityTruck() {
        Tag fakeTag = new Tag();
        Truck truck = new Truck();
        fakeTag.addEntity(truck);
        assertTrue(fakeTag.getTrucks().contains(truck));
        assertTrue(truck.getTags().contains(fakeTag));
    }
    
    @Test
    public void testAddEntityItem() {
        Tag fakeTag = new Tag();
        Item item = new Item();
        fakeTag.addEntity(item);
        assertTrue(fakeTag.getItems().contains(item));
        assertTrue(item.getTags().contains(fakeTag));
    }
    
    @Test
    public void testGetAllTaggedEntitiesAndNumEntities() {
        Tag fakeTag = new Tag();
        Truck truck = new Truck();
        Item item = new Item();
        fakeTag.addEntity(truck);
        fakeTag.addEntity(item);
        Set<Taggable> entities = fakeTag.getAllTaggedEntities();
        assertEquals(entities.size(), fakeTag.numEntities());
        assertTrue(entities.contains(truck));
        assertTrue(entities.contains(item));
    }
    
    @Test
    public void testSearchTags() {
        assertTrue(Tag.searchTags(TAGNAME).contains(realTag));
    }
    
    @Test
    public void testRetrieveTagDoNotCreate() {
        assertEquals(realTag, Tag.retrieveTag(TAGNAME, false));
    }
    
    @Test
    public void testRetrieveTagCreate() {
        Tag t = Tag.retrieveTag("newtag", true);
        assertEquals(t, Tag.retrieveTag("newtag", false));
        t.delete();
    }   
}
