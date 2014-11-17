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
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.dal.ProjectDAO;
import org.dakside.hulk.dal.sqlite.helper.SQLiteHelper;
import org.dakside.utils.FileUtil;
import org.dakside.utils.ResourceCentre;
import org.dakside.utils.Validator;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
class SqliteProjectDAO implements ProjectDAO {

    private static final Logger logger = Logger.getLogger(SqliteProjectDAO.class.getName());

    private ResourceCentre rc;
    private SQLiteHelper helper;

    /**
     *
     * @param helper helper cannot be null
     * @throws ArgumentException
     */
    SqliteProjectDAO(SQLiteHelper helper) throws ArgumentException {
        Validator.argumentNotNull(helper);
        this.helper = helper;
        rc = ResourceCentre.getInstance(this);
    }

    @Override
    public boolean validateProject() {
        return helper.hasTable("meta")
                && helper.hasTable("variety");
    }

    @Override
    public boolean setupProject() {
        try {
            String setupScript = FileUtil.read(this, "resources/setup.sql");
            logger.info(String.format("Setup script = [[[%s]]]", setupScript));
            return helper.executeScript(setupScript);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
