/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa.parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
/**
 *
 * @author uriel
 */
public class XmlMpqaParser implements IMpqaFormatParser {
    
    private XMLStreamReader source;
    private IMpqaFormatParser.IEvent currentEvent = new LiveAnnoation();

    public XmlMpqaParser( Path p ) throws IOException,XMLStreamException {
        source = XMLInputFactory.newFactory().createXMLStreamReader(Files.newInputStream(p));
    }
    public XmlMpqaParser( InputStream in ) throws XMLStreamException {
        source = XMLInputFactory.newFactory().createXMLStreamReader(in);
    }

    @Override
    public boolean next(Consumer<IEvent> action) {
        try{
                while( source.hasNext()) {
                    if( source.next() == XMLStreamConstants.START_ELEMENT &&
                       source.getLocalName().equals("Annotation")){
                       break; 
                    }
                }

                if( source.getEventType() == XMLStreamConstants.START_ELEMENT ) {
                    ((LiveAnnoation)currentEvent).parse(this);
                    action.accept(currentEvent);
                    return true;
                } return false;
        } catch( XMLStreamException se ) {
            try{
                close();
            }catch(Exception e){}
            return false;        
        }
    }

    @Override
    public void close() throws Exception {
        source.close();
    }
    
    private static class LiveAnnoation implements IMpqaFormatParser.IAnnotation {

        private XmlMpqaParser parser;
        private Feature currentFeature = new Feature();

        private String id;
        private String type;
        private int from = -1;
        private int to = -1;        
        
        
        private LiveAnnoation() {}
        
         public LiveAnnoation parse( XmlMpqaParser parser ) {
            for( int a=0;a<parser.source.getAttributeCount();++a ) {
                switch(parser.source.getAttributeLocalName(a)) {
                    case "Id" :
                        id = parser.source.getAttributeValue(a);
                        break;
                    case "Type" :
                        type = parser.source.getAttributeValue(a);
                        break;
                    case "StartNode" :
                        from = Integer.parseInt(
                                    parser.source.getAttributeValue(a));
                        break;
                    case "EndNode" :
                        to = Integer.parseInt(
                                    parser.source.getAttributeValue(a));
                        break;
                }
            }
            return this;
        }
        
        @Override
        public String getID() throws MpqaFormatException {
           if( id == null || id.isEmpty() ) 
               throw MpqaFormatException.missing("annotation", "id");
           return id;
        }

        @Override
        public String getAnnotationType() throws MpqaFormatException {
            if( type == null || type.isEmpty() ) 
               throw MpqaFormatException.missing("annotation", "type");
           return type;
        }

        @Override
        public int getContentFrom() throws MpqaFormatException {
            if( from < 0 ) 
               throw MpqaFormatException.missing("annotation", "from");
           return from;
        }

        @Override
        public int getContentTo() throws MpqaFormatException {
            if( to < 0 ) 
               throw MpqaFormatException.missing("annotation", "to");
           return to;
        }
        

        @Override
        public boolean nextFeature(Consumer<IFeature> action) {
            XMLStreamReader reader = parser.source;
            try{
                while( reader.hasNext() &&
                       reader.next() != XMLStreamConstants.START_ELEMENT &&
                       !reader.getLocalName().equals("Feature")) {}
            }catch(XMLStreamException e) {
               return false;
            }
            
            currentFeature.parseFeature(parser);
            action.accept(currentFeature);
            return true;
        }
        
        @Override
        public int getType() {
            return 1;
        }

        @Override
        public IAnnotation detach() {
            return this;
        }
        
    }
    private static class Feature implements IMpqaFormatParser.IAnnotation.IFeature {

        private String key;
        private String value;

        public Feature() {}
        public Feature(String key, String value) {
            this.key = key;
            this.value = value;
        }
        
        public Feature parseFeature( XmlMpqaParser parser ) {
            key = value = null;
            XMLStreamReader reader = parser.source;
            try{
                while( reader.hasNext() &&
                           reader.next() != XMLStreamConstants.START_ELEMENT &&
                           !reader.getLocalName().equals("Name")) {}
                key = reader.getText();
                while( reader.hasNext() &&
                           reader.next() != XMLStreamConstants.START_ELEMENT &&
                           !reader.getLocalName().equals("Name")) {}
                value = reader.getText();
            }catch( XMLStreamException e ) {
                
            }
                
            return this;
        }
        
        @Override
        public String getFeatureKey() throws MpqaFormatException {
            if( key == null ) {
                throw MpqaFormatException.missing("feature", "key");
            }
            return key;
        }

        @Override
        public String getFeatureValue() throws MpqaFormatException {
            if( value == null ) {
                throw MpqaFormatException.missing("feature", "value");
            }
            return value;
        }

        @Override
        protected Feature clone() throws CloneNotSupportedException {
            return new Feature(key, value);
        }
    }
}
