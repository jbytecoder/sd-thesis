/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author uriel
 */
public class ObjectiveSpechEvent extends Annotation {
    public static class Builder extends Annotation.Builder<ObjectiveSpechEvent,Builder> {

        @Override
        public ObjectiveSpechEvent createProduct() {
            return new ObjectiveSpechEvent();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Getter">
        public List<String> getNestedSource() {
            return getProduct().getNestedSource();
        }
        
        public Uncertainty getUncertainty() {
            return getProduct().uncertainty;
        }
        
        public boolean isImplicit() {
            return getProduct().implicit;
        }
        
        public Uncertainty getObjectiveUncertainty() {
            return getProduct().objectiveUncertainty;
        }
        
        public List<String> getInsubstantial() {
            return getProduct().getInsubstantial();
        }
        //</editor-fold>
        
        @Override
        public ObjectiveSpechEvent build() {
//            Preconditions.checkState(getProduct().id!=null, "Id must be set");
//            Preconditions.checkState(getProduct().targetFrame !=null, "targetFrame must be set");
//            Preconditions.checkState(getProduct().nestedSource!=null&&
//                                     !getProduct().nestedSource.isEmpty(), "nestedSource must be not empty");
            return super.build();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Setters">
        public Builder setNestedSource( String ns ) {
            getProduct().nestedSource = new ArrayList<>(Arrays.asList(ns.split(",")));
            return this;
        }
       
        public Builder setTargetFrame( String tf ) {
            getProduct().targetFrame = tf;
            return this;
        }
        
        public Builder setUncertainty( String ns ) {
            getProduct().uncertainty = Uncertainty.fromString(ns);
            return this;
        }
        public Builder setImplicit( String ns ) {
            getProduct().implicit = ns.length()==1?ns.charAt(0)=='1':Boolean.valueOf(ns);
            return this;
        }
        public Builder setObjectiveUncertainty( String ns ) {
            getProduct().objectiveUncertainty = Uncertainty.fromString(ns);
            return this;
        }
        
        public Builder setInsubstantial( String ns ) {
            getProduct().insubstantial = new ArrayList<>(Arrays.asList(ns.split(",")));
            return this;
        }
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private List<String> nestedSource;
    private String targetFrame;
    
    private Uncertainty uncertainty = Uncertainty.NONE;
    private boolean implicit;
    private Uncertainty objectiveUncertainty = Uncertainty.NONE;
    
    private List<String> insubstantial;
//</editor-fold>

    protected ObjectiveSpechEvent() {
        
    }
    
     @Override
    public String getTypeName(String ver) {
        return "objectvie-speech-event";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public List<String> getNestedSource() {
        return nestedSource == null ? Collections.EMPTY_LIST: nestedSource;
    }
    
    public String getTargetFrame() {
        return targetFrame;
    }
    
    public Uncertainty getUncertainty() {
        return uncertainty;
    }
    
    public boolean isImplicit() {
        return implicit;
    }
    
    public Uncertainty getObjectiveUncertainty() {
        return objectiveUncertainty;
    }
    
    public List<String> getInsubstantial() {
        return insubstantial == null ? Collections.EMPTY_LIST : insubstantial ;
    }
    //</editor-fold>
}
