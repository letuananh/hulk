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
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.dao.DAOHelper;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SQLiteHelper {

    private final ConnectionInfo connectionInfo;
    private SQLiteConnection singletonConnection;

    public SQLiteHelper(ConnectionInfo connectionInfo) throws DAOException {
        DAOHelper.daoArgumentNotNull(connectionInfo, "Invalid connection");
        this.connectionInfo = connectionInfo;
    }

    public SQLiteConnection getConnection() throws SQLiteException {
        if (singletonConnection == null || !singletonConnection.isOpen()) {
            singletonConnection = new SQLiteConnection(new File(connectionInfo.getConnectionString()));
            singletonConnection.open();
        }
        return singletonConnection;
    }

    /**
     * Shutdown database
     */
    public void close() {
        try {
            if (this.singletonConnection != null) {
                this.singletonConnection.dispose();
            }
        } catch (Exception e) {
        } finally {
            this.singletonConnection = null;
        }
    }

    public SQLiteStatement buildQuery(String query) {
        try {
            SQLiteConnection conn = getConnection();
            return conn.prepare(query);
        } catch (SQLiteException ex) {
            Logger.getLogger(SQLiteHelper.class.getName()).log(Level.SEVERE, "Cannot build query", ex);
            return null;
        }
    }

    public SQLiteStatement buildQuery(String query, Object[] args) {
        try {
            SQLiteStatement statement = this.buildQuery(query);
            bindArguments(statement, args);
            return statement;
        } catch (SQLiteException | DAOException ex) {
            Logger.getLogger(SQLiteHelper.class.getName()).log(Level.SEVERE, "Cannot build query", ex);
            return null;
        }
    }

    public boolean select(String query, SQLiteSelectJob job) {
        try {
            SQLiteStatement statement = buildQuery(query);
            job.bindValue(statement);
            while (statement.step()) {
                job.processRow(statement);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void bindArguments(SQLiteStatement statement, Object[] args) throws SQLiteException, DAOException {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof String) {
                    statement.bind(i + 1, (String) arg);
                } else if (arg instanceof Double) {
                    statement.bind(i + 1, (Double) arg);
                } else if (arg instanceof Integer) {
                    statement.bind(i + 1, (Integer) arg);
                } else if (arg instanceof Long) {
                    statement.bind(i + 1, (Long) arg);
                } else if (arg instanceof byte[]) {
                    statement.bind(i + 1, (byte[]) arg);
                } else {
                    throw new DAOException("Unsupported type");
                }
            }
        }
    }

    public Object selectScalar(String query, Object[] args) {
        try {
            SQLiteStatement statement = buildQuery(query);
            bindArguments(statement, args);
            if (statement.step() && statement.columnCount() == 1) {
                return statement.columnValue(0);
            }
        } catch (SQLiteException | DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean select(String query, Object[] args, SQLiteRowRetriever job) {
        try {
            SQLiteStatement statement = buildQuery(query);
            bindArguments(statement, args);
            while (statement.step()) {
                job.processRow(statement);
            }
            return true;
        } catch (SQLiteException | DAOException e) {
            return false;
        }
    }

    public boolean execute(String query, Object[] args) {
        try {
            SQLiteStatement statement = buildQuery(query);
            bindArguments(statement, args);
            statement.stepThrough();
            return true;
        } catch (SQLiteException | DAOException e) {
            return false;
        }
    }

    /**
     * Execute a single statement
     *
     * @param query
     * @param job
     * @return
     */
    public boolean execute(String query, SQLiteJob job) {
        try {
            SQLiteStatement statement = buildQuery(query);
            if (job != null) {
                job.bindValue(statement);
            }
            statement.stepThrough();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void executeScript(String script) throws SQLiteException {
        getConnection().exec(script);
    }

    boolean isClosed() {
        return this.singletonConnection == null;
    }

}
