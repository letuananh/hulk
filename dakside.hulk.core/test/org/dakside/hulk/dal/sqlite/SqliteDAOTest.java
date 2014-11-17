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

import java.io.IOException;
import org.dakside.dao.DAOException;
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.dal.DAOFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
@Ignore
public class SqliteDAOTest {

    protected DAOFactory db;

    @Before
    public void setUp() throws DAOException, ArgumentException {
        this.db = DAOFactory.getDAOFactory(SQLiteTestHelper.getConnectionInfo());
    }

    @After
    public void tearDown() throws IOException {
        db.shutdown();
        SQLiteTestHelper.deleteTempDBFile();
    }

    protected void setupProject() {
        if (!db.getProjectDAO().validateProject()) {
            db.getProjectDAO().setupProject();
        }
    }
}
