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

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import java.util.ArrayList;
import org.dakside.dao.DAOException;
import org.dakside.exceptions.ArgumentException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SQLiteHelperIT {

    public SQLiteHelperIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    SQLiteHelper instance;

    @Before
    public void setUp() throws DAOException, ArgumentException {
        this.instance = SQLiteTestHelper.getHelper();
    }

    @After
    public void tearDown() {
        this.instance.close();
    }

    /**
     * Test of getConnection method, of class SQLiteHelper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        SQLiteConnection result = instance.getConnection();
        assertNotNull(result);
        assertTrue(result.isOpen());
    }

    /**
     * Test of close method, of class SQLiteHelper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClose() throws Exception {
        System.out.println("close");
        instance.close();
    }

    /**
     * Test of executeScript method, of class SQLiteHelper.<br/>
     * Normally this will be used for creating database structure
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testExecuteScript() throws Exception {
        System.out.println("execute script");
        String query = "DROP TABLE IF EXISTS meta; CREATE TABLE IF NOT EXISTS meta (title, code); INSERT INTO meta VALUES('English', 'en');";
        instance.executeScript(query);
    }

    /**
     * Test of buildQuery method, of class SQLiteHelper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBuildQuery() throws Exception {
        System.out.println("buildQuery");
        String query = "SELECT * FROM meta;";
        SQLiteStatement result = instance.buildQuery(query);
        assertNotNull(result);

        result = instance.buildQuery("SELECT * FROM meta WHERE title=?", new Object[]{"English"});
        assertNotNull(result);
    }

    /**
     * Test of select method, of class SQLiteHelper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        String query = "SELECT title,code FROM meta;";
        final ArrayList<String[]> rows = new ArrayList<>();
        boolean result = instance.select(query, new SQLiteSelectJob() {
            @Override
            public void processRow(SQLiteStatement statement) throws SQLiteException {
                rows.add(new String[]{statement.columnString(0), statement.columnString(1)});
            }

            @Override
            public void bindValue(SQLiteStatement statement) {
            }
        });
        assertTrue(result);
        assertNotNull(rows);
        assertEquals(1, rows.size());
        assertEquals("English", rows.get(0)[0]);
        assertEquals("en", rows.get(0)[1]);

        String value = (String) instance.selectScalar("SELECT code FROM meta WHERE title=?", new Object[]{"English"});
        assertEquals("en", value);
    }

    @Test
    public void testShutdown() throws DAOException, ArgumentException {
        instance.close();
        assertTrue(instance.isClosed());
    }

    public void testExecuteQuery() throws SQLiteException, Exception {
        testExecuteScript();
        SQLiteStatement st = instance.buildQuery("SELECT * FROM meta WHERE title=?", new Object[]{"English"});
        assertTrue(st.hasRow());
        assertEquals("title", st.getColumnName(0));
        assertEquals("code", st.getColumnName(1));
        assertTrue(st.step());
        assertEquals("English", st.columnString(0));
        assertEquals("en", st.columnString(1));
        st.dispose();
        assertTrue(st.isDisposed());
        instance.close();
        assertTrue(instance.isClosed());
    }

    public void testUpdate() throws Exception {
        testExecuteScript();
        instance.execute("UPDATE meta SET code=? WHERE title=?", new Object[]{"eng", "English"});
        
    }

}
