/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbc.lib.collections;

import com.google.common.collect.Range;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author uriel
 */
public interface RangeMap<K extends Comparable<K>,V> extends Map<Range<K>,V> {
    Collection<Range<K>> getEnclosed( K from, K to );
}
