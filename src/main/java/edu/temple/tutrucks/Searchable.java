/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * This interface is implemented by all modules that are searchable. 
 * 
 * @author nickdellosa
 * @version %PROJECT_VERSION%
 */
public interface Searchable {
    
    public static class SearchOrganizer implements Comparable<SearchOrganizer> {
        
        public Searchable searchable;
        public final int priority;
        public final boolean beginsWith;
        
        public static List<Searchable> organize(List list, String searchTerm) {
            ArrayList<SearchOrganizer> aretval = new ArrayList<>();
            for (Object o : list) aretval.add(new SearchOrganizer((Searchable)o, searchTerm));
            Collections.sort(aretval);
            ArrayList<Searchable> retval = new ArrayList<>(aretval.size());
            for (SearchOrganizer so : aretval) retval.add(so.searchable);
            return retval;
        }
        
        public SearchOrganizer(Searchable searchable, String searchTerm) {
            this.searchable = searchable;
            if (searchable.getClass() == Truck.class)
                this.priority = 0;
            else if (searchable.getClass() == Tag.class)
                this.priority = -1;
            else
                this.priority = -2;
            beginsWith = searchable.getSearchName().toLowerCase().indexOf(searchTerm.toLowerCase()) == 0;
        }

        @Override
        public int compareTo(SearchOrganizer o) {
            if (this.beginsWith == o.beginsWith) 
                return Integer.compare(o.priority, this.priority);
            else if (this.beginsWith) 
                return -1;
            else 
                return 1;
        }
    }
    /**
     * Returns the name of this Searchable.
     * @return the name of this Searchable
     */
    public String getSearchName();
}
