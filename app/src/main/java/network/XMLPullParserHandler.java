package network;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.MusicEvent;

/**
 * Created by Administrator on 7/9/2016.
 */
public class XMLPullParserHandler {

    public XMLPullParserHandler() {
    }

    public List<MusicEvent> parse(String xmlStr) {
        List<MusicEvent> musicEvents = new ArrayList<MusicEvent>();
        MusicEvent musicEvent = new MusicEvent();
        String text = "";

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(new StringReader(xmlStr));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("event")) {
                            // create a new instance of employee
                            musicEvent = new MusicEvent();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("event")) {
                            // add employee object to list
                            musicEvents.add(musicEvent);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            musicEvent.setId(text);
                        } else if (tagname.equalsIgnoreCase("title")) {
                            musicEvent.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("description")) {
                            musicEvent.setDescription(text);
                        } else if (tagname.equalsIgnoreCase("url")) {
                            musicEvent.setUrl(text);
                        } else if (tagname.equalsIgnoreCase("start_time")) {
                            musicEvent.setStart_time(text);
                        } else if (tagname.equalsIgnoreCase("venue_id")) {
                            musicEvent.setVenue_id(text);
                        } else if (tagname.equalsIgnoreCase("venue_address")) {
                            musicEvent.setVenue_address(text);
                        } else if (tagname.equalsIgnoreCase("venue_name")) {
                            musicEvent.setVenue_name(text);
                        } else if (tagname.equalsIgnoreCase("latitude")) {
                            musicEvent.setLatitude(Double.parseDouble(text));
                        } else if (tagname.equalsIgnoreCase("longitude")) {
                            musicEvent.setLongitude(Double.parseDouble(text));
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return musicEvents;
    }
}