/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.nio.file.Path;
import java.util.Map;

/**
 *
 * @author uriel
 * @param <AB> - Annotation base class if any
 */
public interface IAnnotationScheme<AB> {
    void initAnnotation( String type ) throws AnnotationException;
    void setDocument( Path path );
    void setRange( int from, int to );
    void setAnnotationProperty( 
            String alias,
            String value 
    ) throws AnnotationException;
    AB buildAnnotation() throws AnnotationException;
}
