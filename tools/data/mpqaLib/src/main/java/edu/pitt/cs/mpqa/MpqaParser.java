/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author uriel
 */
public class MpqaParser implements AutoCloseable {
    
    //<editor-fold defaultstate="collapsed" desc="Patterns">
    private static Pattern SUBSTITUTION_PATTERN;
    private static Pattern getSubstitutionPattern() {
        if( SUBSTITUTION_PATTERN == null ) {
            SUBSTITUTION_PATTERN = Pattern.compile(new StringBuilder()
                    .append("[a-zA-Z0-9")
                    .append(Pattern.quote("-"))
                    .append("]*")
                    .append(Pattern.quote("="))
                    .append(Pattern.quote("\""))
                    .append(".*?")
                    .append(Pattern.quote("\""))
                    .toString());
        }
        return SUBSTITUTION_PATTERN;
    }
    
    private static Pattern VALUE_PATTERN;
    private static Pattern getValuePattern() {
        if( VALUE_PATTERN == null ) {
            VALUE_PATTERN = Pattern.compile(new StringBuilder()
            .append(Pattern.quote("\""))
            .append(".*?")
            .append(Pattern.quote("\""))        
            .toString());
        }
        return VALUE_PATTERN;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final BufferedReader source ;
    
    // === CONTEXT Fields ===
    private String[] fields;
    private int[] range;
    private Map<String,String> attributes;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MpqaParser( File f ) throws IOException {
        this(f.toPath());
    }
    public MpqaParser( Path p ) throws IOException {
        source = Files.newBufferedReader(p);
    }
    public MpqaParser( InputStream in ) {
        source = readerFor(in);
    }
    protected static BufferedReader readerFor( InputStream is ) {
        return new BufferedReader(new InputStreamReader(is));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="API">
    public boolean next() throws IOException {
        clearFormerState();
        
        String line = source.readLine();
        
        if( line == null || line.isEmpty() ) {
            return false;
        }
        if( line.startsWith("#") ) {
            fields = new String[]{line};
            return true;
        } else {
            fields = line.split("\t");
            return true;
        }
        // return false;
    }
    private void clearFormerState() {
        fields = null;
        range=null;
        attributes=null;
    }
    
    @Override
    public void close() {
        clearFormerState();
        try {
            source.close();
        }catch(IOException e) {
            throw new IllegalStateException("Cannot close source",e);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Element access API">
    public boolean isComment() {
        if( fields == null ) {
            throw new IllegalStateException(" call next first ");
        }
        return fields[0].startsWith("#");
    }
    public boolean isAnnotation() {
        if( fields == null ) {
            throw new IllegalStateException(" call next first ");
        }
        return fields.length > 2;
    }
    
    public String debug() {
        if( fields == null ) {
            throw new IllegalStateException("call next first");
        }
        return Stream.of(fields).collect(Collectors.joining("|"));
    }
    public String getComment() {
        if(isComment()) {
            return fields[0];
        }
        throw new IllegalStateException("Not an comment!");
    }
    
    public String getID() {
        if( isAnnotation() ) {
            return fields[0];
        }
        throw new IllegalStateException("Not an annotation");
    }
    
    public int getFrom() {
        return getRange()[0];
    }
    public int getTo() {
        return getRange()[1];
    }
    public int[] getRange() {
        if( isAnnotation() ) {
            if( range == null ) {
                parseRange();
            }
            return range;
        }
        throw new IllegalStateException("Not an annotation");
    }
    private void parseRange() {
        String[] r = fields[1].split(",");
        range = new int[2];
        range[0] = Integer.parseInt(r[0]);
        range[1] = Integer.parseInt(r[1]);
    }
    
    public String getType() {
        if( isAnnotation() ) {
            return fields[2];
        }
        throw new IllegalStateException(" not an annotation ");
    }
    
    public Map<String,String> getAttributes() {
        if( isAnnotation() ) {
            if( attributes == null ) {
                parseAttributes();
            }
            
            return attributes;
        }
        throw new IllegalStateException("Not an annotation");
    }
    private void parseAttributes() {
        if( fields.length == 3 ) {
            attributes = Collections.EMPTY_MAP;
            return;
        }
        attributes = new HashMap<>();
        if( fields.length >= 5 ) {
            attributes.put(fields[3], fields[4]);
            return;
        }
        // atributes.length == 4
        Matcher subs = getSubstitutionPattern().matcher(fields[3]);
        Matcher val = getValuePattern().matcher(fields[3]);
        
        while( subs.find() ) {
            if(val.find(subs.start())) {
                attributes.put(
                        fields[3].substring(
                                subs.start(),
                                val.start()-1),
                        fields[3].substring(val.start()+1,val.end()-1));
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Stream API">
    public Stream<MpqaParser> stream() {
        return StreamSupport.stream(new StreamSource(),false);
    }
    
    private class StreamSource implements Spliterator<MpqaParser> {
        
        @Override
        public boolean tryAdvance(Consumer<? super MpqaParser> action) {
            try {
                boolean succ = next();
                if( succ ) {
                    action.accept(MpqaParser.this);
                } else {
                    close();
                }
                return succ;
            } catch( IOException e ) {
                return false;
            }
        }
        
        @Override
        public Spliterator<MpqaParser> trySplit() {
            return null;
        }
        
        @Override
        public long estimateSize() {
            return Long.MAX_VALUE;
        }
        
        @Override
        public int characteristics() {
            return Spliterator.IMMUTABLE |
                    Spliterator.NONNULL ;
        }
    }
    
//</editor-fold>
}
