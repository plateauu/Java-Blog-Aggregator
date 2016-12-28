package com.plateauu.jba.service;


import com.plateauu.jba.entity.Item;
import com.plateauu.jba.exception.RssException;
import org.springframework.stereotype.Service;
import rss.ObjectFactory;
import rss.TRss;
import rss.TRssChannel;
import rss.TRssItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RssService {


    public static final int BEGIN_INDEX = 0;
    private static final String SQUARE = "<";
    private static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    List<Item> getItems(File file) throws RssException {
        return getItems(new StreamSource(file));
    }

    List<Item> getItems(String url) throws RssException {
        return getItems(new StreamSource(url));
    }

    private List<Item> getItems(Source source) throws RssException {

        List<Item> itemList = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<TRss> element = unmarshaller.unmarshal(source, TRss.class);
            TRss rss = element.getValue();

            List<TRssChannel> channels = rss.getChannel();
            for (TRssChannel channel : channels) {
                channel.getItem()
                        .stream()
                        .map(this::parseItem)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .forEach(itemList::add);

            }

        } catch (JAXBException e) {
            throw new RssException(e);
        }

        return itemList;
    }

    private Optional<Item> parseItem(TRssItem rssItem) {
        Item item = new Item();
        try {
            item.setTitle(rssItem.getTitle());
            item.setLink(rssItem.getLink());
            parseDescriprion(rssItem, item);
            Date parseDate = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(rssItem.getPubDate());
            item.setPublishedDate(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(item);
    }

    private void parseDescriprion(TRssItem rssItem, Item item) {
        if (rssItem.getDescription().contains(SQUARE)) {
            item.setDescription(rssItem.getDescription().substring(BEGIN_INDEX, rssItem.getDescription().indexOf(SQUARE)));
        } else {
            item.setDescription(rssItem.getDescription());
        }
    }
}
