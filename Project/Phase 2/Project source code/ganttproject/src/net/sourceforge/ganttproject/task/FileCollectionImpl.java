/*
GanttProject is an opensource project management tool.
Copyright (C) 2011 GanttProject Team

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
package net.sourceforge.ganttproject.task;

import net.sourceforge.ganttproject.resource.HumanResource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileCollectionImpl implements FileCollection {

  private final Map<String, File> files = new HashMap<>();


  @Override
  public Collection<File> getFiles() {
    return files.values();
  }

  @Override
  public void addFile(File file) {

    files.put(file.getFileName(), file);

  }

  @Override
  public void deleteFile(File file) {

    files.remove(file.getFileName());

  }
}
