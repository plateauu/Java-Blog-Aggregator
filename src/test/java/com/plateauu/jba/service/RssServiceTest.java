package com.plateauu.jba.service;

import com.plateauu.jba.entity.Item;
import com.plateauu.jba.exception.RssException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

//TODO change into Spock
public class RssServiceTest {

    private RssService rssService;

    @Before
    public void setUp() {
        rssService = new RssService();
    }

    @Test
    public void test_1() throws RssException {
        List<Item> items = rssService.getItems(new File("/home/plateauu/java/idea/Java-blog-aggr/src/main/resources/com/plateauu/jba/service/javavids.xml"));
        assertEquals(10, items.size());
        Item firstItem = items.get(0);
        assertEquals("How to solve Source not found error during debug in Eclipse", firstItem.getTitle());
        assertEquals("22 Jun 2014 22:35:49", new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
    }
}