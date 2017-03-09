/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author uriel
 */
public class AnnotationBrowser<AB> implements IAnnotationBrowser<AB> {
    private final IAnnotationBrowser<AB> impl;

    public AnnotationBrowser(IAnnotationBrowser<AB> impl) {
        this.impl = impl;
    }
    
    public <B extends IAnnotationBrowser<AB> > B getImplementation( Class<B> c ) {
        if( c.isAssignableFrom(impl.getClass()) ) {
            return (B) impl;
        }
        throw new IllegalStateException("Implementation is not of type: "+c.getName());
    } 

    @Override
    public MpqaCorpus getCorpus() {
        return impl.getCorpus();
    }

    @Override
    public IAnnotationScheme<AB> getScheme() {
        return impl.getScheme();
    }

    @Override
    public AnnotationException.Handler<AB> getAnnotationExceptionHandler() {
        return impl.getAnnotationExceptionHandler();
    }

    @Override
    public void setAnnotationExceptionHandler(AnnotationException.Handler<AB> handler) {
        impl.setAnnotationExceptionHandler(handler);
    }
    
    @Override
    public Stream<AB> streamForDocument(Path doc) {
        return impl.streamForDocument(doc);
    }
    
    public List<AB> listForDocument( Path doc ) {
        return streamForDocument(doc).collect(Collectors.toList());
    }
    
    public Stream<AB> streamForAll( Stream<Path> doc ) {
        return doc.flatMap(this::streamForDocument);
    }
    
    public Stream<AB> streamForAll( ) throws IOException {
        return streamForAll(getCorpus().streamDocs());
    }
    
    public List<AB> listAll( Stream<Path> doc ) {
        return streamForAll(doc).collect(Collectors.toList());
    }
    public List<AB> listAll() throws IOException {
        return streamForAll(getCorpus().streamDocs()).collect(Collectors.toList());
    }
    
    
}
