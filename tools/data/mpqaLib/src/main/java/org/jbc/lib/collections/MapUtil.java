/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbc.lib.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author uriel
 */
public class MapUtil {
    public static <K,V,C extends Collection<V>>
         Map<K,C> wrapValuesIntoSingletons( Map<K,V> map, Supplier<C> ms )
    {
        Map<K,C> r = new HashMap<>();
        for( K k : map.keySet() ) {
          C c = ms.get();
          c.add(map.get(k));
          r.put(k,  c);
        }
        return r;
    } 
    public static <K,V,C extends Collection<V>>
         Map<K,V> unwrapSingletonValues( Map<K,C> map )
    {
        Map<K,V> r = new HashMap<>();
        for( K k : map.keySet() ) {
          r.put(k, map.get(k).isEmpty()?null:map.get(k).iterator().next());
        }
        return r;
    }     
    public static <K,V,C extends Collection<V>> Map<K,C> merge( Map<K,C> m1, 
                                                                Map<K,C> m2) 
    {
        for( K k : m2.keySet() ) {
            C c = m1.get(k);
            if( c == null ) {
                m1.put(k,m2.get(k));
            }  else {
                c.addAll(m2.get(k));
            }
        }
        
        return m1;
    }     
    public static <K,N,V> Map<N,V> remap( Map<K,V> map, Function<K,N> keyMap, Supplier<Map<N,V>> ms ) {
        Map<N,V> res = ms.get();
        
        for( K k : map.keySet() ) {
            res.put(keyMap.apply(k), map.get(k));
        }
        
        return res;
    } 
} 
 