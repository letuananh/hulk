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
package org.dakside.hulk.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.hulk.dal.DAOFactory;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class Configuration {

    private static final Logger logger = Logger.getLogger(Configuration.class.getName());

    private static Configuration instance;

    private ConnectionInfo dbInfo;

    private DAOFactory db;

    private Configuration() {
    }

    public synchronized static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    /**
     * @return the dbInfo
     */
    public ConnectionInfo getDbInfo() {
        return dbInfo;
    }

    /**
     * @param dbInfo the dbInfo to set
     */
    public void setDbInfo(ConnectionInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    /**
     * @return the db
     */
    public DAOFactory getDb() {
        if (getDbInfo() != null && db == null) {
            try {
                db = DAOFactory.getDAOFactory(getDbInfo());
            } catch (DAOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    /**
     * Shutdown the current Db if needed then set db to null
     */
    public synchronized void clearDb() {
        if (this.db != null) {
            this.db.shutdown();
        }
        this.db = null;
    }

}
