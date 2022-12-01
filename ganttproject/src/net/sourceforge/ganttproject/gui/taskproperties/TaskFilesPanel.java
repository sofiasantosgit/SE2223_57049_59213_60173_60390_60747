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
import net.sourceforge.ganttproject.task.FileCollection;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskManager;
import net.sourceforge.ganttproject.task.dependency.TaskDependency;
import net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint;
import net.sourceforge.ganttproject.task.dependency.constraint.FinishFinishConstraintImpl;
import net.sourceforge.ganttproject.task.dependency.constraint.FinishStartConstraintImpl;
import net.sourceforge.ganttproject.task.dependency.constraint.StartFinishConstraintImpl;
import net.sourceforge.ganttproject.task.dependency.constraint.StartStartConstraintImpl;
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * UI component in a task files dialog: a table with task predecessors
 *
 * @author  Pedro Grilo
 * @author Guilherme Fernandes
 */
public class TaskFilesPanel {
  private FileCollection myFileCollection;
  private FilesTableModel myModel;
  private JTable myTable;

  private JTable getTable() {
    return myTable;
  }

  public JPanel getComponent() {
    myModel = new FilesTableModel(myFileCollection);
    myTable = new JTable(myModel);
    UIUtil.setupTableUI(myTable);
    UIUtil.setupTableUI(getTable());

    AbstractTableAndActionsComponent<TaskDependency> tableAndActions = new AbstractTableAndActionsComponent<TaskDependency>(
        getTable()) {
      @Override
      protected void onAddEvent() {

        System.out.println("Add file");
      }

      @Override
      protected void onDeleteEvent() {
        if (myTable.isEditing()) {
          myTable.getCellEditor().stopCellEditing();
        }
        myModel.delete(getTable().getSelectedRows());
      }
    };

    return CommonPanel.createTableAndActions(myTable, tableAndActions.getActionsComponent());
  }

  public void init(FileCollection fileCollection) {
    myFileCollection = fileCollection;
  }

  private FileCollection getFiles() {
    return myFileCollection;
  }

}
