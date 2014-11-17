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

import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.core.models.Variety;
import org.dakside.hulk.dal.LanguageDAO;
import org.dakside.hulk.dal.sqlite.helper.SQLiteHelper;
import org.dakside.hulk.dal.sqlite.helper.SQLiteRowRetriever;
import org.dakside.utils.ResourceCentre;
import org.dakside.utils.Validator;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
class SqliteLanguageDAO implements LanguageDAO {
    
    private static final Logger logger = Logger.getLogger(SqliteLanguageDAO.class.getName());
    private ResourceCentre rc;
    private SQLiteHelper helper;
    
    SqliteLanguageDAO(SQLiteHelper helper) throws ArgumentException {
        Validator.argumentNotNull(helper);
        this.helper = helper;
        rc = ResourceCentre.getInstance(this);
    }
    
    @Override
    public List<Variety> getAllVarieties() {
        final List<Variety> varieties = new ArrayList<>();
        helper.select("SELECT * FROM variety;", null, new SQLiteRowRetriever() {
            
            @Override
            public void processRow(SQLiteStatement statement) throws SQLiteException {
                String langName = statement.columnString(0);
                String langCode = statement.columnString(1);
                String desc = statement.columnString(2);
                varieties.add(new Variety(langName, langCode, desc));
            }
        });
        return varieties;
    }
    
    private static final String INSERT_VARIETY_QUERY
            = "INSERT INTO variety (langName, langCode, desc) VALUES (?,?,?)";
    
    @Override
    public boolean createVariety(Variety variety) throws ArgumentException {
        Validator.argumentNotNull(variety);
        return helper.execute(INSERT_VARIETY_QUERY, new Object[]{
            variety.getName(), variety.getCode(), variety.getDescription()
        });
    }
    
}
