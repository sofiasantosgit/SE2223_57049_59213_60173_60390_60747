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
  private Task myTask;
  private FilesTableModel myModel;
  private JTable myTable;

  private JTable getTable() {
    return myTable;
  }

  public JPanel getComponent() {
    myModel = new FilesTableModel(myTask);
    myTable = new JTable(myModel);
    UIUtil.setupTableUI(myTable);
    UIUtil.setupTableUI(getTable());

    setUpPredecessorComboColumn(FilesTableModel.MyColumn.FILE_NAME.getTableColumn(getTable()), getTable());

    AbstractTableAndActionsComponent<TaskDependency> tableAndActions = new AbstractTableAndActionsComponent<TaskDependency>(
        getTable()) {
      @Override
      protected void onAddEvent() {
        getTable().editCellAt(myModel.getRowCount() - 1, DependencyTableModel.MyColumn.TASK_NAME.ordinal());
      }

      @Override
      protected void onDeleteEvent() {
        if (myTable.isEditing()) {
          myTable.getCellEditor().stopCellEditing();
        }
        myModel.delete(getTable().getSelectedRows());
      }

      @Override
      protected TaskDependency getValue(int row) {
        List<TaskDependency> dependencies = myModel.getDependencies();
        return (row >= 0 && row < dependencies.size()) ? dependencies.get(row) : null;
      }
    };

    return CommonPanel.createTableAndActions(myTable, tableAndActions.getActionsComponent());
  }

  public void init(Task task) {
    myTask = task;
  }

  public void commit() {
    if (myTable.isEditing()) {
      myTable.getCellEditor().stopCellEditing();
    }
    myModel.commit();
  }

  private Task getTask() {
    return myTask;
  }

  private void setUpPredecessorComboColumn(TableColumn predecessorColumn, final JTable predecessorTable) {
    final JComboBox<DependencyTableModel.TaskComboItem> comboBox = new JComboBox<>();

    Task[] possiblePredecessors = getTaskManager().getAlgorithmCollection().getFindPossibleDependeesAlgorithm().run(
        getTask());

    int maxDigits = 0;
    for (Task next : possiblePredecessors) {
      comboBox.addItem(new DependencyTableModel.TaskComboItem(next));
      maxDigits = Math.max(maxDigits, (int) Math.log10(next.getTaskID()));
    }
    final int maxWidth = (maxDigits + 1) * 10;

    comboBox.setRenderer(new DefaultListCellRenderer() {
      private JLabel myId = new JLabel();
      private JXLabel myLabel = new JXLabel();
      private JPanel myBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 3));

      {
        myId.setEnabled(false);
        myId.setHorizontalAlignment(SwingConstants.RIGHT);
        myBox.add(myId);
        myBox.add(myLabel);
        myBox.setOpaque(true);
      }

      @Override
      public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        DependencyTableModel.TaskComboItem item = (DependencyTableModel.TaskComboItem) value;
        TaskManager taskManager = item.myTask.getManager();
        JComponent superResult = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (index == -1) {
          return superResult;
        }
        myId.setPreferredSize(new Dimension(maxWidth, 10));
        myId.setFont(superResult.getFont().deriveFont(superResult.getFont().getSize() * 0.85f));
        myId.setText(String.valueOf(item.myTask.getTaskID()));
        myLabel.setText(item.myTask.getName());
        myLabel.setBorder(BorderFactory.createEmptyBorder(0,
            10 * (taskManager.getTaskHierarchy().getDepth(item.myTask) - 1),
            0, 0));
        myBox.setBorder(superResult.getBorder());
        myBox.setBackground(superResult.getBackground());
        myBox.setForeground(superResult.getForeground());
        return myBox;
      }
    });
    comboBox.setEditable(false);
    predecessorColumn.setCellEditor(new DefaultCellEditor(comboBox));
  }

  private TaskManager getTaskManager() {
    return getTask().getManager();
  }
}
