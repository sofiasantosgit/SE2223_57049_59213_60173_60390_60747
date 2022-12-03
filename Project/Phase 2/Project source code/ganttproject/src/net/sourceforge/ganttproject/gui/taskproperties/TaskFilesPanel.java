/*
GanttProject is an opensource project management tool. License: GPL3
Copyright (C) 2010 Dmitry Barashev

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.sourceforge.ganttproject.gui.taskproperties;

import net.sourceforge.ganttproject.gui.AbstractTableAndActionsComponent;
import net.sourceforge.ganttproject.gui.UIUtil;
import net.sourceforge.ganttproject.task.File;
import net.sourceforge.ganttproject.task.FileCollection;
import net.sourceforge.ganttproject.task.FileImpl;
import net.sourceforge.ganttproject.task.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;

/**
 * UI component in a task files dialog: a table with task predecessors
 *
 * @author Pedro Grilo
 * @author Guilherme Fernandes
 */
public class TaskFilesPanel {
    private FileCollection myFileCollection;
    private FilesTableModel myModel;
    private JTable myTable;
    private final JFileChooser fileChooser = new JFileChooser();

    private final Task task;

    public TaskFilesPanel(Task task, FileCollection fileCollection) {
        myFileCollection = fileCollection;
        this.task = task;
    }

    private JTable getTable() {
        return myTable;
    }

    public JPanel getComponent() {
        myModel = new FilesTableModel(myFileCollection);
        myTable = new JTable(myModel);
        UIUtil.setupTableUI(myTable);
        UIUtil.setupTableUI(getTable());

        AbstractTableAndActionsComponent<File> tableAndActions = new AbstractTableAndActionsComponent<File>(
                getTable()) {
            @Override
            protected void onAddEvent() {

                System.out.println("Add file");
                int returnVal = fileChooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    FileImpl fileImpl = new FileImpl(file.getName(), file.getAbsolutePath());
                    myModel.addFile(fileImpl);
                }

            }

            @Override
            protected void onDeleteEvent() {
                if (myTable.isEditing()) {
                    myTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Delete file");
                System.out.println(myTable.getSelectedRow());
                myModel.delete(getTable().getSelectedRows());
            }

            @Override
            protected File getValue(int row) {
                File file = myFileCollection.get(row);
                myTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            int selectedRow = myTable.getSelectedRow();
                            try {
                                Desktop.getDesktop().open(new java.io.File((String) myTable.getValueAt(selectedRow, 1)));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                return file;
            }
        };

        return CommonPanel.createTableAndActions(myTable, tableAndActions.getActionsComponent());
    }

    public void init(FileCollection fileCollection) {
        myFileCollection = fileCollection;
    }

}
