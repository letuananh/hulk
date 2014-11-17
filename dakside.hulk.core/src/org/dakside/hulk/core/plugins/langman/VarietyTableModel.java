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
package org.dakside.hulk.core.plugins.langman;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.dakside.hulk.core.models.Variety;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class VarietyTableModel extends AbstractTableModel {

    private static final String[] columnNames = {"Name", "Code", "Description"};
    private static final Class<?>[] columnTypes = {String.class, String.class, String.class};
    private ArrayList<Variety> varieties;

    public VarietyTableModel() {
        this.varieties = new ArrayList<>();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= 0 && columnIndex < columnTypes.length) {
            return columnTypes[columnIndex];
        } else {
            return null;
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Get number of varieties
     *
     * @return
     */
    public int getRowCount() {
        return varieties.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Variety variety = varieties.get(row);
        switch (col) {
            case 0:
                //should not render data in the model object
                return variety.getName();
            case 1:
                return variety.getCode();
            case 2:
                return variety.getDescription();
            default:
                return null;
        }
    }

    Variety getRowObject(int index) {
        return varieties.get(index);
    }

    Variety[] getAllVarietys() {
        return varieties.toArray(new Variety[0]);
    }

    void clear() {
        varieties.clear();
    }

    void add(Variety action) {
        varieties.add(action);
    }

    void removeAt(int idx) {
        varieties.remove(idx);
    }
}
