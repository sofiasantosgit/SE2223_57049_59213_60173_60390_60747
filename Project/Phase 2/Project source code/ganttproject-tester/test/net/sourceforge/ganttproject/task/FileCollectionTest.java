package net.sourceforge.ganttproject.task;
import net.sourceforge.ganttproject.gui.taskproperties.FilesTableModel;
import net.sourceforge.ganttproject.test.task.TaskTestCase;

public class FileCollectionTest extends TaskTestCase {

    public void testEmptyFilesModel() {
        Task task = createTask();
        FileCollection files = task.getFiles();
        assertEquals(0, files.size());
    }

    public void testAddFile() {
        Task task = createTask();
        FileCollection files = task.getFiles();
        FileImpl file = new FileImpl("test", "test");
        files.addFile(file);
        assertEquals(1, files.size());
        assertEquals(file, files.get(0));
    }

    public void testAddFileToModel() {
        Task task = createTask();
        FileCollection files = task.getFiles();
        FileImpl file = new FileImpl("test", "test");
        FilesTableModel model = new FilesTableModel(files);
        assertEquals(1, model.getRowCount());
        files.addFile(file);
        assertEquals(2, model.getRowCount());
        assertEquals(file.getFileName(), model.getValueAt(0, 0));
    }

    public void testDeleteFile() {
        Task task = createTask();
        FileCollection files = task.getFiles();
        FileImpl file = new FileImpl("test", "test");
        files.addFile(file);
        assertEquals(1, files.size());
        files.deleteFile(file);
        assertEquals(0, files.size());
    }

    public void testDeleteFileFromModel() {
        Task task = createTask();
        FileCollection files = task.getFiles();
        FileImpl file = new FileImpl("test", "test");
        files.addFile(file);
        assertEquals(1, files.size());
        FilesTableModel model = new FilesTableModel(files);
        model.delete(new int[] {0});
        assertEquals(0, files.size());
    }

    public void testAddMultipleTests(){
        Task task1 = createTask();
        FileCollection files1 = task1.getFiles();
        FileImpl file1 = new FileImpl("test1", "test1");
        files1.addFile(file1);
        FilesTableModel model1 = new FilesTableModel(files1);

        Task task2 = createTask();
        FileCollection files2 = task2.getFiles();
        FileImpl file2 = new FileImpl("test2", "test2");
        files2.addFile(file2);
        FilesTableModel model2 = new FilesTableModel(files2);

        Task task3 = createTask();
        FileCollection files3 = task3.getFiles();
        FileImpl file3 = new FileImpl("test3", "test3");
        files3.addFile(file3);
        FilesTableModel model3 = new FilesTableModel(files3);

        assertEquals(file1.getFileName(), model1.getValueAt(0, 0));
        assertEquals(file2.getFileName(), model2.getValueAt(0, 0));
        assertEquals(file3.getFileName(), model3.getValueAt(0, 0));
    }

    public void testDeleteMultipleTests(){
        Task task1 = createTask();
        FileCollection files1 = task1.getFiles();
        FileImpl file1 = new FileImpl("test1", "test1");
        files1.addFile(file1);
        FilesTableModel model1 = new FilesTableModel(files1);

        Task task2 = createTask();
        FileCollection files2 = task2.getFiles();
        FileImpl file2 = new FileImpl("test2", "test2");
        files2.addFile(file2);
        FilesTableModel model2 = new FilesTableModel(files2);

        Task task3 = createTask();
        FileCollection files3 = task3.getFiles();
        FileImpl file3 = new FileImpl("test3", "test3");
        files3.addFile(file3);
        FilesTableModel model3 = new FilesTableModel(files3);

        model1.delete(new int[] {0});
        model2.delete(new int[] {0});
        model3.delete(new int[] {0});

        assertEquals(0, files1.size());
        assertEquals(0, files2.size());
        assertEquals(0, files3.size());
    }




}
