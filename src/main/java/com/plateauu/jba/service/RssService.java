package com.plateauu.jba.service;


import com.plateauu.jba.entity.Item;
import com.plateauu.jba.exception.RssException;
import rss.ObjectFactory;
import rss.TRss;
import rss.TRssChannel;
import rss.TRssItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RssService {


    public List<Item> getItems(Source source) throws RssException {

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
                    item.setDescription(rssItem.getDescription());
                    Date parseDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate());
                    item.setPublishedDate(parseDate);
                    itemList.add(item);

                }
            }
        } catch (JAXBException | ParseException e) {
            throw new RssException(e);
        }
        return itemList;
    }
}
