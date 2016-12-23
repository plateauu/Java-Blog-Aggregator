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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class RssService {


    private static final String SQUARE = "<";
    private static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    List<Item> getItems(File file) throws RssException {
        return getItems(new StreamSource(file));
    }

    List<Item> getItems(String url) throws RssException {
        return getItems(new StreamSource(url));
    }

    private List<Item> getItems(Source source) throws RssException {

        List<Item> itemList =new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<TRss> element = unmarshaller.unmarshal(source, TRss.class);
            TRss rss = element.getValue();

            List<TRssChannel> channels = rss.getChannel();
            for (TRssChannel channel : channels) {
                List<TRssItem> items = channel.getItem();
                for (TRssItem rssItem : items) {
                    Item item = new Item();
                    item.setTitle(rssItem.getTitle());
                    item.setLink(rssItem.getLink());

                    if (rssItem.getDescription().contains(SQUARE)) {
                        item.setDescription(rssItem.getDescription().substring(0, rssItem.getDescription().indexOf(SQUARE)));
                    } else {
                        item.setDescription(rssItem.getDescription());
                    }

                    Date parseDate = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(rssItem.getPubDate());
                    item.setPublishedDate(parseDate);

                    //TODO convert Date to LocalDateTime
//                     LocalDateTime parseDate = LocalDateTime.parse(rssItem.getPubDate(), DateTimeFormatter.RFC_1123_DATE_TIME);
                    itemList.add(item);

                }
            }
//
//            rss.getChannel().stream()
//                    .map(TRssChannel::getItem)
//                    .
//                    .map(rssItem -> {
//                        Item item = new Item();
//                        item.setTitle(rssItem.getTitle());
//                        item.setLink(rssItem.getLink());
//                        item.setDescription(rssItem.getDescription());
//                        Date parseDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate());
//                        item.setPublishedDate(parseDate);
//                        itemList.add(item);
//
//                    });


        } catch (JAXBException | ParseException e) {
            throw new RssException(e);
        }
        return itemList;
    }
}
