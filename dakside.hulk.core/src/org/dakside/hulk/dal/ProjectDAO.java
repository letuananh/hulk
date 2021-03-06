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
package org.dakside.hulk.dal;

import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.core.models.Project;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public interface ProjectDAO {

    /**
     * Validate a project struture<br/>
     * Check tables, relations, etc.
     *
     * @return true if all required tables & relations are available
     */
    boolean validateProject();

    /**
     * Setup a project if needed
     *
     * @return true if the project has been setup successfully
     */
    boolean setupProject();

    /**
     * Get project information (title, author, description, etc.)
     *
     * @return null if error was raised
     */
    Project getProjectInfo();

    boolean saveProjectInfo(Project info) throws ArgumentException;
}
