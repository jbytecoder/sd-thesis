/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uriel
 */
public  class IMpqaFormatParserTest {
    
    //public abstract IMpqaFormatParser create( InputStream in )
    
    private static Object[][] getTestCase1() {
        return new Object[][]{
            {new StringBuilder()
                    .append("<AnnotationSet>")
                    .append("</AnnotationSet>"),
             new Object[][]{
                 {1,"","",5,10,},
                 
             }
            }
        };
    }
    
    public static Object[][] getParameters() {
        return new Object[][]{
            { null,1,"id","sentence",50,100,new Object[]{""} },
            {  }
        };
    }
    
    public void assertEvent( IMpqaFormatParser.IEvent e, Object[] expect) throws MpqaFormatException {
        assertEquals("event.type",e.getType(), expect[0]);
        if( e.getType() == 1 ) {
            IMpqaFormatParser.IAnnotation a = (IMpqaFormatParser.IAnnotation)e;
            assertEquals("ann.id",a.getID(),expect[1]);
            assertEquals("ann.type",a.getAnnotationType(),expect[2]);
            assertEquals("ann.from",a.getContentFrom(),expect[3]);
            assertEquals("ann.to",a.getContentTo(),expect[4]);
            
        }
    }
    
    public InputStream getXml() {
        return new ByteArrayInputStream(new StringBuffer()
                .append("<AnnotationSet>")
                .append("<Annotation Id=\"s\"")
                .append(" Type=\"sentence\" StartNode=\"10\" ")
                .append("EndNode=\"20\" ></Annotation>")
                .append("<Annotation Id=\"s1\"")
                .append(" Type=\"sentence\" StartNode=\"30\" ")
                .append("EndNode=\"40\" ></Annotation>")
                .append("</AnnotationSet>")
                .toString().getBytes());
    }
    
    @Test
    public void testEvents() throws Exception {
       InputStream in = getClass().getClassLoader().getResourceAsStream("one.exp");
       
       
//        IMpqaFormatParser parser = new XmlMpqaParser(getXml());
//        
//        while(parser.next((e)->{
//            try{
//            IMpqaFormatParser.IAnnotation a = (IMpqaFormatParser.IAnnotation)e;
//            System.out.println(a.getID()+" "+a.getAnnotationType()+" "+a.getContentFrom()+" "+a.getContentTo());
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//        })){}
        
    }
    
}
