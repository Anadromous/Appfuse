/**
 * 
 */
package com.chapman.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chapman.model.Category;

/**
 * @author <a href="mailto:pchapman@easystreet.net">Peter Chapman</a>
 *
 */
public class CategoryDaoTest extends BaseDaoTestCase {

	@Autowired
	private CategoryDao dao;
	
	@Test
    public void testGetCategoryInvalid() throws Exception {
        Category category = dao.getCategoryByName("badcategoryname");
        assertNull(category);
    }

    @Test
    public void testGetCategory() throws Exception {
        Category category = dao.getCategoryByName("Food");
        assertNotNull(category);
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Category category = dao.getCategoryByName("Food");
        category.setDescription("test descr");
        dao.save(category);
        flush();
        category = dao.getCategoryByName("test descr");
        assertEquals("test descr", category.getDescription());
    }

    @Test
    public void testAddAndRemoveCategory() throws Exception {
        Category category = new Category();
        category.setDescription("testcategory");
        dao.save(category);
        flush();
        category = dao.getCategoryByName("testcategory");
        assertNotNull(category.getDescription());
        dao.removeCategory("testcategory");
        flush();
        category = dao.getCategoryByName("testcategory");
        assertNull(category);
    }
    
    @Test
    public void testGetAllCategories() throws Exception{
    	List<Category> categories = dao.getAll();
    	assertTrue(categories.size() > 2);
    }

/*    @Test
    public void testFindByNamedQuery() {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("name", Constants.USER_ROLE);
        List<Category> categories = dao.findByNamedQuery("findCategoryByName", queryParams);
        assertNotNull(categories);
        assertTrue(categories.size() > 0);
    }*/
}
