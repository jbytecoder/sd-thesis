/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author uriel
 */
public class DefaultAnnotationScheme implements IAnnotationScheme<Annotation> {

    public interface BuilderFactory
    {
        Annotation.Builder<?,?> getBuilder( String type )throws AnnotationException;
    }
    
    private final BuilderFactory builderFactory;
    private Annotation.Builder<?,?> activeBuilder;         

    public DefaultAnnotationScheme(BuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }
     
    @Override
    public void initAnnotation(String type) throws AnnotationException {
        activeBuilder = builderFactory.getBuilder(type);
    }

    @Override
    public void setDocument(Path path) {
        activeBuilder.setDocument(path);
    }

    @Override
    public void setRange(int from, int to) {
        activeBuilder.setContentRange(from, to);
    }

    @Override
    public void setAnnotationProperty(String alias, String value) throws AnnotationException {
//        System.out.println("setProperty: "+alias+" "+value);
        activeBuilder.set(alias, value);
    }

    @Override
    public Annotation buildAnnotation() throws AnnotationException {
        return activeBuilder.build();
    }
    
}
