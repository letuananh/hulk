/*
 * Copyright (C) 2014 Le Tuan Anh <tuananh.ke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.hulk.dal.sqlite;

import java.util.List;
import org.dakside.dao.DAOException;
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.core.models.Project;
import org.dakside.hulk.dal.ProjectDAO;
import org.dakside.utils.FileUtil;
import org.dakside.utils.Validator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SqliteProjectDAOTest extends SqliteDAOTest {

    public SqliteProjectDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Override
    public void setUp() throws DAOException, ArgumentException {
        super.setUp(); 
    }
    
    

    private ProjectDAO getProjectDAO() {
        return db.getProjectDAO();
    }

    /**
     * Test of setupProject method, of class SqliteProjectDAO.
     */
    @Test
    public void testSetupProject() {
        System.out.println("setupProject");
        ProjectDAO instance = getProjectDAO();
        boolean expResult = true;
        boolean result = instance.setupProject();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateProject method, of class SqliteProjectDAO.
     */
    @Test
    public void testValidateProject() {
        testSetupProject();
        System.out.println("validateProject");
        ProjectDAO instance = getProjectDAO();
        boolean expResult = true;
        boolean result = instance.validateProject();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetResource() {
        ProjectDAO instance = getProjectDAO();
        List<String> lines = FileUtil.readLines(instance, "resources/setup.sql");
        assertNotNull(lines);
        assertTrue("Cannot read file", lines.size() > 0);
        String content = FileUtil.read(instance, "resources/setup.sql");
        //System.out.printf("\n---\nContent = [[[%s]]]\n---\n", content);
        assertNotNull(content);
        assertTrue("", content.length() > 0);
    }

    /**
     * Test of getProjectInfo method, of class SqliteProjectDAO.
     */
    @Test
    public void testGetProjectInfo() {
        System.out.println("getProjectInfo");
        setupProject();
        ProjectDAO instance = getProjectDAO();
        Project result = instance.getProjectInfo();
        assertNotNull(result);
        assertTrue("Invalid title", Validator.isValid(result.getTitle()));
        assertTrue("Invalid authors", Validator.isValid(result.getAuthors()));
        assertTrue("Invalid description", Validator.isValid(result.getDescription()));
    }

    /**
     * Test of saveProjectInfo method, of class SqliteProjectDAO.
     */
    @Test
    public void testSaveProjectInfo() throws Exception {
        System.out.println("saveProjectInfo");
        setupProject();
        ProjectDAO instance = getProjectDAO();
        Project info = instance.getProjectInfo();
        String updatedAuthor = "Le Tuan Anh";
        info.setAuthors(updatedAuthor);
        boolean result = instance.saveProjectInfo(info);
        assertTrue(result);
        info = instance.getProjectInfo();
        assertNotNull(info);
        assertEquals(info.getAuthors(), updatedAuthor);
    }

}
