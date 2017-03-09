/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author uriel
 */
public class Sentence extends Annotation {
    public static class Builder extends Annotation.Builder<Sentence,Builder> {
        
        public List<String> getNestedSource() {
            return getProduct().getNestedSource();
        }

        @Override
        public Sentence createProduct() {
            return new Sentence();
        }
        
        public Builder setNestedSource( String ns ) {
            getProduct().nestedSource = new ArrayList<>(Arrays.asList(ns.split(",")));
            return this;
        }
        
    }
    
    private List<String> nestedSource;

    protected Sentence(){}
    
    public List<String> getNestedSource() {
        return nestedSource == null ? Collections.EMPTY_LIST: nestedSource;
    }
    
     @Override
    public String getTypeName(String ver) {
        return "sentence";
    }
}
