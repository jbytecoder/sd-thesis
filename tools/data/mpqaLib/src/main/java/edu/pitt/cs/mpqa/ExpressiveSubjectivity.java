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
public class ExpressiveSubjectivity extends Annotation  {
    public static class Builder extends Annotation.Builder<ExpressiveSubjectivity,Builder> {

        @Override
        public ExpressiveSubjectivity createProduct() {
            return new ExpressiveSubjectivity();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Getters">
        public List<String> getNestedSource() {
            return getProduct().getNestedSource();
        }
        
        public String getTargetFrame() {
            return getProduct().targetFrame;
        }
        
        public Polarity getPolarity() {
            return getProduct().polarity;
        }
        
        public Intensity getIntensity() {
            return getProduct().intensity;
        }
        
        public Uncertainty getNestedSourceUncertainty() {
            return getProduct().nestedSourceUncertainty;
        }
        //</editor-fold>
   
        @Override
        public ExpressiveSubjectivity build() {
//            Preconditions.checkState(getProduct().id!=null, "Id must be provided");
//            Preconditions.checkState(getProduct().targetFrame!=null, "targetFrame must be provided");
//            Preconditions.checkState(getProduct().polarity!=null, "polarity must be provided");
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
        public Builder setPolarity( String ns ) {
            getProduct().polarity = Polarity.fromString(ns);
            return this;
        }
        public Builder setIntensity( String ns ) {
            getProduct().intensity = Intensity.fromString(ns);
            return this;
        }
        public Builder setNestedSourceUncertainty( String ns ) {
            getProduct().nestedSourceUncertainty = Uncertainty.fromString(ns);
            return this;
        }
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private List<String> nestedSource;
    private String targetFrame;
    
    private Polarity polarity;
    
    private Intensity intensity;
    private Uncertainty nestedSourceUncertainty = Uncertainty.NONE;
    //</editor-fold>

    protected ExpressiveSubjectivity() {
        
    }
    
     @Override
    public String getTypeName(String ver) {
        return "expressive-subjectvitiy";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public List<String> getNestedSource() {
        return nestedSource == null ? Collections.EMPTY_LIST: nestedSource;
    }
    
    public String getTargetFrame() {
        return targetFrame;
    }
    
    public Polarity getPolarity() {
        return polarity;
    }
    
    public Intensity getIntensity() {
        return intensity;
    }
    public Uncertainty getNestedSourceUncertainty() {
        return nestedSourceUncertainty;
    }
    //</editor-fold>
}
