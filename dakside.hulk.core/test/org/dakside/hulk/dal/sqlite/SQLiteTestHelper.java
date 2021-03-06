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
import org.dakside.hulk.dal.sqlite.helper.SQLiteHelper;
import org.dakside.dao.AbstractDAOFactory;
import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.exceptions.ArgumentException;
import org.dakside.utils.FileUtil;
import org.dakside.utils.SystemHelper;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SQLiteTestHelper {

    public static SQLiteHelper getHelper() throws DAOException, ArgumentException {
        return new SQLiteHelper(getConnectionInfo());
    }

    public static void deleteTempDBFile() throws IOException{
        FileUtil.delete(getTempDBFile());
    }
    
    public static String getTempDBFile() {
        String tempDBFile = SystemHelper.getTempDir() + SystemHelper.getPathSeparator() + "testsqlite.db";
        return tempDBFile;
    }

    public static ConnectionInfo getConnectionInfo() throws ArgumentException {

        return new ConnectionInfo(getTempDBFile(), AbstractDAOFactory.SQLITE_DATABASE);
    }
}
