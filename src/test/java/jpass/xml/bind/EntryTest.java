package jpass.xml.bind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryTest {
    private Entry entry;

    @BeforeEach
    public void setup() {
        entry = new Entry();
    }

    @Test
    public void constructionTest() {
        assertEquals(entry.getCreationDate(), entry.getLastModification());
    }

    @Test
    public void titleTest() {
        String title1 = entry.getTitle();
        assertNull(title1);
        String title2 = "Hello";
        entry.setTitle(title2);
        assertEquals(title2, entry.getTitle());
    }

    @Test
    public void urlTest() {
        String url1 = entry.getUrl();
        assertNull(url1);
        String url2 = "www.example.com";
        entry.setUrl(url2);
        assertEquals(url2, entry.getUrl());
    }

    @Test
    public void userTest() {
        String user1 = entry.getUser();
        assertNull(user1);
        String user2 = "user";
        entry.setUser(user2);
        assertEquals(user2, entry.getUser());
    }

    @Test
    public void passwordTest() {
        String pass1 = entry.getUser();
        assertNull(pass1);
        String pass2 = "oooo";
        entry.setPassword(pass2);
        assertEquals(pass2, entry.getPassword());
    }

    @Test
    public void notesTest() {
        String notes1 = entry.getNotes();
        assertNull(notes1);
        String notes2 = "oooo";
        entry.setNotes(notes2);
        assertEquals(notes2, entry.getNotes());
    }

    @Test
    public void datesTest() {
        String creation = entry.getCreationDate();
        assertNotNull(creation);
        String modification = entry.getLastModification();
        assertNotNull(modification);
        String newDate = "08-11-2022";
        entry.setCreationDate(newDate);
        assertEquals(newDate, entry.getCreationDate());
    }

    @Test
    public void entriesTest() {
        Entries entries = new Entries();
        List<Entry> listOfEntries = entries.getEntry();
        assertEquals(0, listOfEntries.size());
    }
}
