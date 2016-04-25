/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

/**
 * An interface to be implemented by all entities that can have tags associated with them.
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 */
public interface Taggable {
    /**
     * Returns the set of tags attached to this entity.
     * @return the set of tags attached to this entity
     */
    public java.util.Set<Tag> getTags();
    /**
     * Attaches one or more tags to this entity.
     * @param t the tag(s) to be attached to this entity
     */
    public void addTags(Tag... t);
    /**
     * Loads all associated tags from the database and attaches them to this entity.
     * @return the set of tags attached to this entity
     */
    public java.util.Set<Tag> loadTags();
}
