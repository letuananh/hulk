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

import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.dao.DAOHelper;
import org.dakside.hulk.dal.DAOFactory;
import org.dakside.hulk.dal.LanguageDAO;
import org.dakside.hulk.dal.ProjectDAO;
import org.dakside.utils.Validator;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class SQLiteDAOFactory extends DAOFactory {

    private SqliteLanguageDAO languageDAO;
    private SqliteProjectDAO projectDAO;
    private ConnectionInfo connectionInfo;

    @Override
    public synchronized LanguageDAO getLanguageDAO() {
        if (languageDAO == null) {
            languageDAO = new SqliteLanguageDAO();
        }
        return languageDAO;
    }

    @Override
    public void shutdown() {
        //Doing nothing
    }

    public SQLiteDAOFactory(ConnectionInfo connectionInfo) throws DAOException {
        DAOHelper.daoArgumentNotNull(connectionInfo, "Connection information must not be null");
        this.connectionInfo = connectionInfo;
    }

    @Override
    public ProjectDAO getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = new SqliteProjectDAO();
        }
        return projectDAO;
    }

}
