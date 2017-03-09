/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author uriel
 */

public class MpqaParserTest {

    
    private MpqaParser parser;
   
    
    private MpqaParser parserFor( String... lines ) {
        StringBuilder sb = new StringBuilder();
        for( String s : lines ) {
            sb.append(s).append("\n");
        }
        
        return new MpqaParser(new ByteArrayInputStream(sb.toString().getBytes()));
    }
    
    @Test
    public void testAfterClose() {
        parser = parserFor();
        parser.close();
        try{
            parser.next();
            fail("next() called on closed input");
        }catch( IOException e ) {
            
        }
        assertEquals("steram() after close",parser.stream().count(),0);
        try{
            parser.next();
            fail("next() called on closed input");
        }catch( IOException e ) {
            
        }
        try{
            parser.getType();
            fail("getType() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getID();
            fail("getID() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getFrom();
            fail("getFrom() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getTo();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getRange();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getComment();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.getAttributes();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.isAnnotation();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        try{
            parser.isComment();
            fail("getTo() called before next()");
        }catch( IllegalStateException e ) {
            
        }
        
    } 
    
    @Test
    public void testEmpty() throws IOException {
        parser = parserFor();
        assertEquals("first row",false,parser.next());
    }
    
    
}
