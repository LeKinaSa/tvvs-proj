package jpass.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import jpass.xml.bind.Entries;
import jpass.xml.bind.Entry;

public class DataModelTest {
    @Test
    public void modelTest() {
        DataModel data1 = DataModel.getInstance();
        DataModel data2 = DataModel.getInstance();
        assertEquals(data1, data2);
    }

    @Test
    public void filenameTest() {
        DataModel data = DataModel.getInstance();
        assertNull(data.getFileName());
        String filename = "config.txt";
        data.setFileName(filename);
        assertEquals(filename, data.getFileName());
    }

    @Test
    public void modifiedTest() {
        DataModel data = DataModel.getInstance();
        assertFalse(data.isModified());
        data.setModified(true);
        assertTrue(data.isModified());
        data.clear();
        assertFalse(data.isModified());
    }

    @Test
    public void passwordTest() {
        DataModel data = DataModel.getInstance();
        assertNull(data.getPassword());
        byte[] pass = "oooo".getBytes();
        data.setPassword(pass);
        byte[] result = data.getPassword();
        assertEquals(pass.length, result.length);
        assertTrue(Arrays.equals(pass, result));
    }

    @Test
    public void clearTest() {
        DataModel data = DataModel.getInstance();
        String filename = "config.txt";
        boolean modified = true;
        byte[] pass = "oooo".getBytes();
        data.setFileName(filename);
        data.setModified(modified);
        data.setPassword(pass);

        assertEquals(filename, data.getFileName());
        assertEquals(modified, data.isModified());
        assertEquals(pass.length, data.getPassword().length);
        assertTrue(Arrays.equals(pass, data.getPassword()));
    
        data.clear();
        
        assertEquals(null, data.getFileName());
        assertFalse(data.isModified());
        assertTrue(Arrays.equals(null, data.getPassword()));
        assertEquals(0, data.getEntries().getEntry().size());
    }

    public Entries getTestEntries() {
        Entries entries = new Entries();
        Entry entry1, entry2, entry3, entry4, entry5;
        
        entry1 = new Entry();
        entry1.setTitle("entry1");
        entry1.setUrl("www.entry1.com");
        entry1.setUser("user1");
        entry1.setPassword("1111");

        entry2 = new Entry();
        entry2.setTitle("entry2");
        entry2.setUrl("www.entry2.com");
        entry2.setUser("user2");
        entry2.setPassword("2222");

        entry3 = new Entry();
        entry3.setTitle("entry3");
        entry3.setUrl("www.entry3.com");
        entry3.setUser("user3");
        entry3.setPassword("3333");

        entry4 = new Entry();
        entry4.setTitle("entry4");
        entry4.setUrl("www.entry4.com");
        entry4.setUser("user4");
        entry4.setPassword("4444");

        entry5 = new Entry();
        entry5.setTitle("entry5");
        entry5.setUrl("www.entry5.com");
        entry5.setUser("user5");
        entry5.setPassword("5555");

        entries.getEntry().add(entry1);
        entries.getEntry().add(entry2);
        entries.getEntry().add(entry3);
        entries.getEntry().add(entry4);
        entries.getEntry().add(entry5);
        return entries;
    }

    @Test
    public void entriesTest() {
        DataModel data = DataModel.getInstance();
        Entries entries = getTestEntries();

        data.setEntries(entries);
        assertEquals(entries, data.getEntries());

        List<String> expectedTitles = Arrays.asList("entry1", "entry2", "entry3", "entry4", "entry5");
        assertEquals(expectedTitles, data.getTitles());
        
        assertEquals(0, data.getEntryIndexByTitle("entry1"));
    
        assertEquals(entries.getEntry().get(0), data.getEntryByTitle("entry1"));
        assertNull(data.getEntryByTitle("this entry doesn\'t exist"));
    }
}
