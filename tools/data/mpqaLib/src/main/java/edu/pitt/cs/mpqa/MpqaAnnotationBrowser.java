/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Stream;
import org.jbc.lib.exception.ExceptionVHandler;

/**
 *
 * @author uriel
 */
public class MpqaAnnotationBrowser<AB> implements IAnnotationBrowser<AB> {

    private final MpqaCorpus corpus;
    private final IAnnotationScheme<AB> scheme;
   
    private AnnotationException.Handler<AB> aeh = AnnotationException::panicHandler;
    private ExceptionVHandler<IOException> ioh = (c,context)->{ throw new IllegalStateException(c); };
   
    public MpqaAnnotationBrowser(MpqaCorpus corpus, IAnnotationScheme<AB> scheme) {
        this.corpus = corpus;
        this.scheme = scheme;
        
    }

    @Override
    public MpqaCorpus getCorpus() {
        return corpus;
    }

    public IAnnotationScheme<AB> getScheme() {
        return scheme;
    }

    @Override
    public AnnotationException.Handler<AB> getAnnotationExceptionHandler() {
        return aeh;
    }
    @Override
    public void setAnnotationExceptionHandler(AnnotationException.Handler<AB> handler) {
        this.aeh = handler;
    }

    public ExceptionVHandler<IOException> getIOExceptionHandler() {
        return ioh;
    }
    public void setIOExceptionHandler( ExceptionVHandler<IOException> handler ) {
        this.ioh = handler;
    }
    
    @Override
    public Stream<AB> streamForDocument(Path doc) {
        try{
        Path an = corpus.getAnnotationPath(doc);
            System.out.println(createParserFor(an).stream().count());
        return createParserFor(an)
                .stream()
                .filter((p)->p.isAnnotation())
                .map(new DocumentAnnotationParser(doc)::parse);
        }
        catch(IOException e) {
//            ioh.handle(e, doc);
            return Stream.empty();
        }
    }
    
    protected MpqaParser createParserFor( Path ann ) throws IOException {
        return new MpqaParser(ann);
    }
    
    protected AB parseAnnotation( Path document, MpqaParser parser ) {
        try {
            scheme.initAnnotation(parser.getType());
            scheme.setDocument(document);
            scheme.setRange(parser.getFrom(), parser.getTo());
            Map<String,String> attrs = parser.getAttributes();

            for( String key : attrs.keySet() ) {
                scheme.setAnnotationProperty(key, attrs.get(key));
            }

            return scheme.buildAnnotation();
        } catch( AnnotationException ae ) {
            return aeh.handle( ae, document, parser, scheme );
        }
    }
    
    
    private class DocumentAnnotationParser {
        private final Path document;

        public DocumentAnnotationParser( Path document ) 
        {
            this.document = document;
        }

        public AB parse( MpqaParser parser ) {
           return parseAnnotation(document, parser);
        } 
    }
}
