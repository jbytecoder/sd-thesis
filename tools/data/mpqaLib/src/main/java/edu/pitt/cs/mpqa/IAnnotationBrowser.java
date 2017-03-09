/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 *
 * @author uriel
 */
public interface IAnnotationBrowser<AB> {
    MpqaCorpus getCorpus();
    IAnnotationScheme<AB> getScheme();
    Stream<AB> streamForDocument( Path doc );
    AnnotationException.Handler<AB> getAnnotationExceptionHandler();
    void setAnnotationExceptionHandler( AnnotationException.Handler<AB> handler );
}
