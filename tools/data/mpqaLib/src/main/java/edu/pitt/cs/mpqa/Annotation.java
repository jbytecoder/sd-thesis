/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import com.google.common.collect.Range;
import com.google.common.collect.Ranges;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.jbc.lib.depa.burp.ACabsBuilder;

/**
 *
 * @author uriel
 */
public abstract class Annotation {
    public static abstract class Builder<P extends Annotation ,
                                         B extends Builder<P,B>> 
    extends ACabsBuilder<AnnotationException, AnnotationException, P, B>
    {
        private P product;

        protected P getProduct() {
            if( product == null ) {
                product = createProduct();
            }
            return product;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Getters">
        public Path getDocument() {
            return product.getDocument();
        }
        
        public String getId() {
            return product.getId();
        }
        
        public Range<Integer> getContentRange() {
            return product.getContentRange();
        }
        //</editor-fold>
        
        public String getTypeName( String ver ) {
            return product.getTypeName(ver);
        }
       
        public abstract P createProduct();
        public P build() {
            return product;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Settters">
        public B setDocument( Path p ) {
            getProduct().document = p;
            return (B) this;
        }
        public B setId( String id ) {
            getProduct().id = id;
            return (B) this;
        }
        public B setContentRange( Range<Integer> cr ) {
            getProduct().contentRange = cr;
            return (B) this;
        }
        public B setContentRange( int from, int to ) {
            return setContentRange(Ranges.closed(from, to));
        }
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    protected Path document;
    
    protected String id;
    protected Range<Integer> contentRange;
    //</editor-fold>
    
    protected Annotation(){}
    
    public abstract String getTypeName( String ver );
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Path getDocument() {
        return document;
    }
    
    public String getId() {
        return id;
    }
    
    public Range<Integer> getContentRange() {
        return contentRange;
    }
    //</editor-fold>
}
