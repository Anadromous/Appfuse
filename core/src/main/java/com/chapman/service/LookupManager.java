package com.chapman.service;

import com.chapman.model.LabelValue;

import java.util.List;

/**
 * Business Service Interface to talk to persistence layer and
 * retrieve values for drop-down choice lists.
 *
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 */
public interface LookupManager {
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllRoles();
    
    /**
     * Retrieves all possible categories from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllCategories();
}
