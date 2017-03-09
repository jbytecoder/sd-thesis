/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.nio.file.Path;
import org.jbc.lib.exception.ExceptionC1Handler;

/**
 *
 * @author uriel
 */
public class AnnotationException extends Exception {
    
    public interface Handler<AB> 
        extends ExceptionC1Handler<AnnotationException,AB,Path> {}
    
    public static <AB> AB panicHandler( AnnotationException ae, 
                                        Path doc, 
                                        Object... context ) { 
        throw new IllegalStateException(ae);
    }

    public static AnnotationException unsupportedType( String type ) {
        return new AnnotationException(
                new StringBuilder("Annotation of type: ")
                        .append(type)
                        .append(" is unsupported.")
                        .toString());
    }
    public static AnnotationException unsupportedProperty( String type,String prop ) {
        return new AnnotationException(
                new StringBuilder("Annotation of type: ")
                        .append(type)
                        .append(" has no property: ")
                        .append(prop)
                        .toString());
    }
    public static AnnotationException unsupportedValue( String type,String prop,String value ){
        return new AnnotationException(
                new StringBuilder("Property: ")
                        .append(prop)
                        .append(" of ")
                        .append(type)
                        .append(" cannot handle value: ")
                        .append(value)
                        .toString());
    }
    public static AnnotationException missingValue( String type, String prop ) {
        return new AnnotationException(
                new StringBuilder("Annotation of type: ")
                        .append(type)
                        .append(" requieres property: ")
                        .append(prop)
                        .append(" to be set.")
                        .toString());
    }
    
    public AnnotationException(String message) {
        super(message);
    }
    
}
