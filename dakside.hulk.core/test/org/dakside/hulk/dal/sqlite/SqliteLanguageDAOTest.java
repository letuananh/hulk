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
import org.dakside.hulk.core.models.Variety;
import org.dakside.hulk.dal.LanguageDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SqliteLanguageDAOTest extends SqliteDAOTest {

    public SqliteLanguageDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getAllVarieties method, of class SqliteLanguageDAO.
     */
    @Test
    public void testGetAllVarieties() throws Exception {
        setupProject();
        testCreateVariety();
        System.out.println("getAllVarieties");
        LanguageDAO instance = db.getLanguageDAO();
        List<Variety> result = instance.getAllVarieties();
        assertNotNull(result);
        assertTrue("Must have exactly 1 variety", result.size() == 1);
    }

    /**
     * Test of createVariety method, of class SqliteLanguageDAO.
     */
    @Test
    public void testCreateVariety() throws Exception {
        setupProject();
        System.out.println("createVariety");
        Variety variety = new Variety(-1, "English", "en", "English as we know it");
        LanguageDAO instance = db.getLanguageDAO();
        boolean result = instance.createVariety(variety);
        assertEquals(true, result);
    }

}
