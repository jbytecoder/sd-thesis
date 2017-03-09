/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author uriel
 */
public class Annotations {
    public static boolean matchSubjective( Collection<Annotation> al ) {
        return al.stream().filter((a)->{
            if( a instanceof DirectSubjective ) {
                DirectSubjective ds = (DirectSubjective)a;
                if(ds.getIntensity()==null) return false;
                return ds.getInsubstantial().isEmpty() && ds.getIntensity().compareTo(Intensity.LOW)>0;
            }
            if( a instanceof ExpressiveSubjectivity ) {
                ExpressiveSubjectivity es = (ExpressiveSubjectivity)a;
                if( es.getIntensity() == null ) return false;
                return es.getIntensity().compareTo(Intensity.LOW)>0;
            }
            return false;
        }).count() > 0;
    }
    public static Map<Sentence,Set<Annotation>> mapToSentences( List<Annotation> al ) {
       List<Sentence> sentences = al.stream()
                                    .filter((a)->a instanceof Sentence)
                                    .map((a)->(Sentence)a)
                                    .sorted(( s1, s2 )-> {
                                        return s1.getContentRange()
                                                 .lowerEndpoint()-
                                               s2.getContentRange()
                                                  .lowerEndpoint();})
                                    .collect(Collectors.toList());
       
       return al.stream()
                .collect(
                   Collectors.toMap((a)->findEnclosing(sentences, a), 
                                    (a)->new HashSet(Collections.singleton(a)),
                                    (s1,s2)->{s1.addAll(s2);return s1;}));

    }
    private static Sentence findEnclosing( List<Sentence> sl, Annotation a ) {
        for( Sentence s : sl ) {
            if( s.getContentRange().encloses(a.getContentRange()) ) {
                return s;
            }
        }
        return null;
    }
}
