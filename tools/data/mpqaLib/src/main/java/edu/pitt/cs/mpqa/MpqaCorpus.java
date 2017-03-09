/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jbc.lib.collections.MapUtil;

/**
 *
 * @author uriel
 */
public class MpqaCorpus {
    
    private static final String DOCS_ROOT = "docs";
    private static final String META_ROOT = "meta_anns";
    private static final String MAN_ROOT = "man_anns";
    
    private static final String ANN_FILE = "gateman.mpqa.lre.3.0";
    private static final String SEN_FILE = "gatesentences.mpqa.2.0";
    

    private final Path rootPath;

    public MpqaCorpus(File path) {
        this.rootPath = path.toPath();
    }

    public IAnnotationScheme<Annotation> getScheme() {
        return new DefaultAnnotationScheme(MpqaFormat.forV30Strict()::getBuilderFor); 
    }
    
    public <AB> AnnotationBrowser<AB> manualAnnotationBrowser( IAnnotationScheme<AB> scheme ) 
    {
        return new AnnotationBrowser<>(new MpqaAnnotationBrowser<>(this,scheme));
    }
 
    
    public Path getDocsRoot() {
        return rootPath.resolve(DOCS_ROOT);
    }
    public Path getMetaRoot() {
        return rootPath.resolve(META_ROOT);
    }
    public Path getManRoot() {
        return rootPath.resolve(MAN_ROOT);
    }
    
    public boolean isDoc( Path doc ) {
        try {
            return Files.isRegularFile(doc) &&
                   !Files.isHidden(doc) &&
                   doc.startsWith(getDocsRoot());
        }catch(IOException e) {
            return false;
        }
    }
    
    public Function<Range<Integer>,String> getContentRetriver( Path doc ) {
        try {
            String data = new String(Files.readAllBytes(doc));
            return ( r ) -> {
              return data.substring(r.lowerEndpoint(),r.upperEndpoint());
            };
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return (r)->"";
    }
    public Stream<String> streamSnippetsOf( Path doc, Stream<Range<Integer>> snippets ) {
       return snippets.map(getContentRetriver(doc));
    }
    
    public Stream<Path> streamDocs() throws IOException {
        return Files.walk(getDocsRoot())
                    .filter(this::isDoc);
    } 
    public List<Path> getAllDocs() throws IOException {
        return streamDocs().collect(Collectors.toList()); 
    }
    
    
    public Path getMetaPath( Path doc ) {
        if( !isDoc(doc) ) throw new IllegalArgumentException("Not a doc: "+doc);
        return getMetaRoot().resolve(getDocsRoot().relativize(doc));
    }
    public Path getAnnotationPath( Path doc ) {
        return getManRoot()
                .resolve(getDocsRoot()
                          .relativize(doc))
                .resolve(ANN_FILE);
    }
    public Path getSentencePath( Path doc ) {
        return getManRoot()
                .resolve(getDocsRoot()
                          .relativize(doc))
                .resolve(SEN_FILE);
    }
    
    public Map<String,Map<String,Set<String>>> gatherAnnotationProperties() throws IOException {
        return streamDocs()
                .map((p)->getAnnotationPath(p))
                .flatMap((p)->{
                    try{
                    return new MpqaParser(p).stream();
                    }catch(IOException e){ return Stream.empty(); }
                }).filter((p)->p.isAnnotation())
                .collect(
                  Collectors.toMap(
                          p->p.getType(),
                          p->MapUtil.wrapValuesIntoSingletons(p.getAttributes(),HashSet::new),
                          MapUtil::merge));
    }
    public void printAnnotationProperties( PrintStream out ) throws IOException  {
        Map<String,Map<String,Set<String>>> data = gatherAnnotationProperties();
        List<String> types = new ArrayList(data.keySet());
        Collections.sort(types);
        for( String type : types ) {
            out.print(type);
            out.print(":\n");
            
            Map<String,Set<String>> pp = data.get(type);
            List<String> props = new ArrayList(pp.keySet());
            Collections.sort(props);
            for( String prop : props ) {
                out.print("\t");
                out.print(prop);
                out.print(" = ");
                out.println(pp.get(prop));
            }
        }
        
    }
    public void printAnnotationProperties() {
        try {
            printAnnotationProperties(System.out);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    
    public MetaData getMetaData( Path doc ) throws IOException,ParseException {
        return new MetaDataFactory().parseMetaAnnotation(getMetaPath(doc));
    }
    
    public Stream<MetaData> streamMetaData( Stream<Path> docs, MetaDataFactory mdf ) {
        return docs.map((p)->parseMetaData(mdf, getMetaPath(p)));
    }
    private MetaData parseMetaData( MetaDataFactory mdf, Path p ) {
        try {
            return mdf.parseMetaAnnotation(p);
        } catch( IOException | ParseException pe ) {
            return null;
        }
    }
}
