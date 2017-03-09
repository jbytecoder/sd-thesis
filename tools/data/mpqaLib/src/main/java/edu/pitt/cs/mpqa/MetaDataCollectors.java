/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author uriel
 */
public class MetaDataCollectors {
    public static <T> List<T> mergeList( List<T> l1, List<T> l2 ) {
      List<T> r = new ArrayList<>();
      if(l1!=null) r.addAll(l1);
      if(l2!=null) r.addAll(l2);
      return r;
    }
    public static <T,V> Map<T,List<V>> mergeMaps( Map<T,List<V>> m1, Map<T,List<V>> m2 ) {
        for( T key : m2.keySet() ) {
            m1.put(key, mergeList(m1.get(key), m2.get(key)));
        }
        return m1;
    }
    public static Collector<MetaData,?,Map<String,List<MetaData>>> byTopic() {
        return Collector.of( HashMap::new,
                             MetaDataCollectors::includeMetaData,
                             MetaDataCollectors::mergeMaps);
    }
    private static void includeMetaData( Map<String,List<MetaData>> map, MetaData md ) {
        if( md == null ) return;
        for( String topic : md.getTopics() ) {
            if( topic == null ) continue;
            List<MetaData> list =  map.get(topic);
            if( list == null ) map.put(topic, list=new ArrayList<>());
            list.add(md);
        }
    }
    
}
