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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.dao.DAOHelper;
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.dal.DAOFactory;
import org.dakside.hulk.dal.LanguageDAO;
import org.dakside.hulk.dal.ProjectDAO;
import org.dakside.hulk.dal.sqlite.helper.SQLiteHelper;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SQLiteDAOFactory extends DAOFactory {

    private static final Logger logger = Logger.getLogger(SQLiteDAOFactory.class.getName());

    private SqliteLanguageDAO languageDAO;
    private SqliteProjectDAO projectDAO;
    private final ConnectionInfo connInfo;
    private final SQLiteHelper helper;

    @Override
    public synchronized LanguageDAO getLanguageDAO() {
        if (languageDAO == null) {
            try {
                languageDAO = new SqliteLanguageDAO(helper);
            } catch (ArgumentException ex) {
                Logger.getLogger(SQLiteDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return languageDAO;
    }

    @Override
    public void shutdown() {
        this.helper.close();
        this.languageDAO = null;
        this.projectDAO = null;
    }

    public SQLiteDAOFactory(ConnectionInfo connectionInfo) throws DAOException {
        DAOHelper.daoArgumentNotNull(connectionInfo, "Connection information must not be null");
        this.connInfo = connectionInfo;
        this.helper = new SQLiteHelper(this.connInfo);
    }

    @Override
    public ProjectDAO getProjectDAO() {
        if (projectDAO == null) {
            try {
                projectDAO = new SqliteProjectDAO(helper);
            } catch (ArgumentException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return projectDAO;
    }

}
