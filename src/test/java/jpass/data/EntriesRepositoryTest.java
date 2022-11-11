package jpass.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import jpass.xml.bind.Entries;
import jpass.xml.bind.Entry;

public class EntriesRepositoryTest {
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
    public void noKeyTest() {
        Entries entries = getTestEntries();
        String filename = "no_key_test_file.txt";

        EntriesRepository repo = EntriesRepository.newInstance(filename);
        try {
            repo.writeDocument(entries);
        }
        catch (DocumentProcessException | IOException e) {
            fail();
        }

        Entries result = null;
        try {
            result = repo.readDocument();
        }
        catch (DocumentProcessException | IOException e) {
            fail();
        }

        assertEquals(entries.getEntry().size(), result.getEntry().size());

        for (int i = 0; i < entries.getEntry().size(); i++) {
            Entry expected = entries.getEntry().get(i);
            Entry actual = entries.getEntry().get(i);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void keyTest() {
        Entries entries = getTestEntries();
        String filename = "key_test_file.txt";
        byte[] key = "oooo0000oooo0000oooo0000oooo0000".getBytes();

        EntriesRepository repo = EntriesRepository.newInstance(filename, key);
        try {
            repo.writeDocument(entries);
        }
        catch (DocumentProcessException | IOException e) {
            System.out.println(e.getMessage());
            fail();
        }

        Entries result = null;
        try {
            result = repo.readDocument();
        }
        catch (DocumentProcessException | IOException e) {
            fail();
        }

        assertEquals(entries.getEntry().size(), result.getEntry().size());

        for (int i = 0; i < entries.getEntry().size(); i++) {
            Entry expected = entries.getEntry().get(i);
            Entry actual = entries.getEntry().get(i);
            assertEquals(expected, actual);
        }
    }
}
