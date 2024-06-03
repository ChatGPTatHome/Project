package tests;

import model.Developer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeveloperTest {

    @Test
    public void testGetAnthony(){
        assertEquals("Anthony", Developer.getAnthony());
    }

    @Test
    public void testGetHai(){
        assertEquals("Hai", Developer.getHai());
    }

    @Test
    public void testGetJeremiah(){
        assertEquals("Jeremiah", Developer.getJeremiah());
    }

    @Test
    public void testGetWindie(){
        assertEquals("Windie", Developer.getWindie());
    }

}
