/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import com.google.common.collect.Range;
import com.google.common.collect.Ranges;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jbc.lib.collections.MapUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author uriel
 */
public class MpqaCorpusTest {
    
    private static class Owner {
        public void throwEx() {
            throw new RuntimeException();
        }
    }
    
    public void runner( Runnable r ) {
        r.run();
    }
    
    
    public void fun() {
        
    }
    
    
    @Test
    public void test() throws Exception {
        MpqaCorpus mc = new MpqaCorpus(new File("/home/uriel/tmp/ED/ds/source/mpqa3.0"));
        AnnotationBrowser<Annotation> ab = mc.manualAnnotationBrowser(mc.getScheme());
        
        ab.setAnnotationExceptionHandler(new AnnotationException.Handler<Annotation>() {
            @Override
            public Annotation handle(AnnotationException cause, Path c1, Object... context) {
                try{
                    return ((IAnnotationScheme<Annotation>)context[1]).buildAnnotation();
                }catch(AnnotationException ae) {
                    ae.printStackTrace();
                }
                return null;
            }
        });
        
       
        mc.printAnnotationProperties();
//        
//        System.out.println(mc.streamDocs().count());
//        System.out.println(ab.streamForAll().count());
//        
//        Map<Path,List<Annotation>> res=  ab.streamForAll().collect(Collectors.groupingBy((a)->a.getDocument()));
//      
//        int t = 0;
//        for( Path key : res.keySet() ) {
//            Map<String,Set<Annotation>> sen =  MapUtil.<Sentence,String,Set<Annotation>>remap(Annotations.mapToSentences(res.get(key)),
//                                                             mc.getContentRetriver(key).compose((Sentence s)->s==null?Ranges.closed(0,0):s.getContentRange()),
//                                                             HashMap::new);
//            t+=sen.size();
//            System.out.println(key.getFileName());
//            for( String s : sen.keySet() ) {
//                System.out.println(s);
//                System.out.println(Annotations.matchSubjective(sen.get(s)));
//            } 
//        }
//        System.out.println(t);
    }
    
}
