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
package org.dakside.hulk.core.models;

/**
 * Information about a language variety (a language, a dialect, etc.)
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class Variety {

    private int varietyID;
    private String name;
    private String code;
    private String description;
    public static final Variety[] ARRAY = new Variety[0];

    public Variety(int varietyID, String name, String code, String description) {
        this.varietyID = varietyID;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the varietyID
     */
    public int getVarietyID() {
        return varietyID;
    }

    /**
     * @param varietyID the varietyID to set
     */
    public void setVarietyID(int varietyID) {
        this.varietyID = varietyID;
    }

}
