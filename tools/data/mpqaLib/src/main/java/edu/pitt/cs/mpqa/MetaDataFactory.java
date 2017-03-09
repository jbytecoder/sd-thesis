/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author uriel
 */
public class MetaDataFactory {

    private final MetaData.Builder builder = new MetaData.Builder();
    
    private void includeAttribute( String key,String value ) throws ParseException  {
        switch(key) {
            case "meta_reference":
                builder.setReference(value);
                break;
            case "meta_source":
                builder.setSource(value);
                break;
            case "meta_description":
                builder.setDescription(value);
                break;
            case "meta_title":
                builder.setTitle(value);
                break;
            case "meta_document_time":
                builder.setDocumentTime(value);
                break;
            case "meta_create_time":
                builder.setCreateTime(value);
                break;
            case "meta_media_file":
                builder.setMediaFile(value);
                break;
            case "meta_media_type":
                builder.setMediaType(value);
                break;
            case "meta_scribe":
                builder.setScribe(value);
                break;
            case "meta_topic":
                builder.addTopic(value);
                break;
            case "meta_country":
                builder.addCountry(value);
                break;
            case "meta_region":
                builder.addRegion(value);
                break;
            case "meta_subregion":
                builder.addSubRegion(value);
                break;    
                
        }
    }

    public MetaData parseMetaAnnotation( Path path )
    throws IOException,
           ParseException 
    {
        try( MpqaParser parser = new MpqaParser(path) ) {
            while(parser.next()) {
                if( parser.isAnnotation() ) {
                    Map<String,String> att = parser.getAttributes();
                    for( String key : att.keySet() ) {
                        includeAttribute(key, att.get(key));
                    }
                }
            }
        }
       
        return builder.build();
    }
}
