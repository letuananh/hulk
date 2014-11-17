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
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import org.dakside.duck.helpers.EasyCellRenderer;
import org.dakside.hulk.core.models.Variety;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class VarietyTable extends JTable {

    /**
     * Format table
     */
    public void format() {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.getColumnModel().getColumn(1).getCellRenderer();
        if (renderer == null) {
            renderer = new DefaultTableCellRenderer();
            this.getColumnModel().getColumn(0).setCellRenderer(new EasyCellRenderer());
            this.getColumnModel().getColumn(1).setCellRenderer(renderer);
            this.getColumnModel().getColumn(2).setCellRenderer(new EasyCellRenderer());
            this.getColumnModel().getColumn(3).setCellRenderer(new EasyCellRenderer());
            this.getColumnModel().getColumn(4).setCellRenderer(new EasyCellRenderer());

        }
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public Variety[] getSelectedVarietys() {
        int[] indexes = getSelectedRows();
        Variety[] trx = new Variety[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            trx[i] = getVarietyAt(indexes[i]);
        }

        return trx;
    }

    /**
     * Get the selected transaction from model (address translated)
     * @param rowIndex
     * @return
     */
    public Variety getVarietyAt(int rowIndex) {
        int innerIndex = rowIndex;
        RowSorter sorter = getRowSorter();
        if (sorter != null) {
            innerIndex = sorter.convertRowIndexToModel(rowIndex);
        }
        return getCustomModel().getRowObject(innerIndex);
    }

    Variety[] getAllVarietys() {
        VarietyTableModel model = (VarietyTableModel) getModel();
        return model.getAllVarietys();
    }

    public void load(Variety[] actions) {
        VarietyTableModel model = (VarietyTableModel) getModel();
        model.clear();
        System.out.println("Found actions: " + actions.length);
        for (Variety action : actions) {
            model.add(action);
        }
        //Create table sorter object
        if (getRowSorter() == null) {
            TableRowSorter<VarietyTableModel> sorter = new TableRowSorter<VarietyTableModel>();
            sorter.setModel(model);

            //previous sortkey or current sortkey
            ArrayList<SortKey> keys = new ArrayList<SortKey>();
            keys.add(new SortKey(0, SortOrder.DESCENDING)); //first column = trx date
            sorter.setSortKeys(keys);
            sorter.sort();
            //set sorter to table
            this.setRowSorter(sorter);
        }
        model.fireTableDataChanged();
    }

    /**
     * Remove selected transaction row on the table
     */
    void removeSelected() {
        int[] rowIndexes = getSelectedRows();
        VarietyTableModel model = (VarietyTableModel) getModel();
        RowSorter sorter = getRowSorter();

        if (sorter != null) {
            for (int i = rowIndexes.length - 1; i >= 0; i--) {
                rowIndexes[i] = sorter.convertRowIndexToModel(rowIndexes[i]);
            }
        }
        //sort the indexes and remove from the max value
        Arrays.sort(rowIndexes);
        for (int i = rowIndexes.length - 1; i >= 0; i--) {
            model.removeAt(rowIndexes[i]);
        }
        //repaint table

        getCustomModel().fireTableDataChanged();

        this.clearSelection();
        this.repaint();
    }

    Variety[] getVarietys() {
        VarietyTableModel model = (VarietyTableModel) getModel();
        return model.getAllVarietys();
    }

    public VarietyTableModel getCustomModel() {
        if (super.getModel() instanceof VarietyTableModel) {
            return (VarietyTableModel) super.getModel();
        } else {
            return null;
        }
    }
}
