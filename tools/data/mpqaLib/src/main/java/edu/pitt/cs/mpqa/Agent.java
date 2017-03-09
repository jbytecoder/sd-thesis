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
public class Agent extends Annotation {
    public static class Builder extends Annotation.Builder<Agent,Builder> {
        
        //<editor-fold defaultstate="collapsed" desc="Getters">
        public List<String> getNestedSource() {
            return getProduct().getNestedSource();
        }
        
        public Uncertainty getUncertainty() {
            return getProduct().uncertainty;
        }
        //</editor-fold>

        @Override
        public Agent createProduct() {
            return new Agent();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Setters">
        public Builder setNestedSource( String ns ) {
            getProduct().nestedSource = new ArrayList<>(Arrays.asList(ns.split(",")));
            return this;
        }
        
        public Builder setUncertain( String value ) {
            getProduct().uncertainty = Uncertainty.fromString(value);
            return this;
        }
        
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private List<String> nestedSource;
    private Uncertainty uncertainty = Uncertainty.NONE;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public List<String> getNestedSource() {
        return nestedSource == null ? Collections.EMPTY_LIST: nestedSource;
    }
    
    public Uncertainty getUncertainty() {
        return uncertainty;
    }
    
//</editor-fold>

    @Override
    public String getTypeName(String ver) {
        return "agent";
    }
    
    //<editor-fold defaultstate="collapsed" desc="API">
    public boolean isUncertain() {
        return uncertainty!=Uncertainty.NONE;
    }
    //</editor-fold>
}
