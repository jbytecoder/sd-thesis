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
public class DirectSubjective extends Annotation {
    public static class Builder extends Annotation.Builder<DirectSubjective,Builder> {

        @Override
        public DirectSubjective createProduct() {
            return new DirectSubjective();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Getter">
        public List<String> getNestedSource() {
            return getProduct().getNestedSource();
        }
        
        public List<String> getAttitudeLinks() {
            return getProduct().getAttitudeLinks();
        }
        
        public Uncertainty getUncertainty() {
            return getProduct().uncertainty;
        }
        
        public boolean isImplicit() {
            return getProduct().implicit;
        }
        
        public Uncertainty getSubjectiveUncertainty() {
            return getProduct().subjectiveUncertainty;
        }
        
        public Intensity getIntensity() {
            return getProduct().intensity;
        }
        
        public Polarity getPolarity() {
            return getProduct().polarity;
        }
        
        public List<String> getInsubstantial() {
            return getProduct().getInsubstantial();
        }
        //</editor-fold>
        
        @Override
        public DirectSubjective build() {
//            Preconditions.checkState(getProduct().id!=null, "Id must be set");
//            Preconditions.checkState(getProduct().nestedSource!=null&&
//                                     !getProduct().nestedSource.isEmpty(), "nestedSource must be not empty");
//            Preconditions.checkState(getProduct().attitudeLinks!=null&&
//                                     !getProduct().attitudeLinks.isEmpty(), "attitudeLinks must be not empty");
            return super.build();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Setters">
        public Builder setNestedSource( String ns ) {
            getProduct().nestedSource = new ArrayList<>(Arrays.asList(ns.split(",")));
            return this;
        }
        public Builder setAttitudeLinks( String ns ) {
            getProduct().attitudeLinks = new ArrayList<>(Arrays.asList(ns.split(",")));
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
        public Builder setSubjectiveUncertainty( String ns ) {
            getProduct().subjectiveUncertainty = Uncertainty.fromString(ns);
            return this;
        }
        public Builder setIntensity( String ns ) {
            getProduct().intensity = Intensity.fromString(ns);
            return this;
        }
        public Builder setPolarity( String ns ) {
            getProduct().polarity = Polarity.fromString(ns);
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
    private List<String> attitudeLinks;
    
    private Uncertainty uncertainty = Uncertainty.NONE;
    private boolean implicit;
    private Uncertainty subjectiveUncertainty = Uncertainty.NONE;;
    private Intensity intensity;
    private Polarity polarity;
    
    private List<String> insubstantial;
    //</editor-fold>
    
    protected DirectSubjective(){}
    
    @Override
    public String getTypeName(String ver) {
        return "direct-subjectvie";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public List<String> getNestedSource() {
        return nestedSource == null ? Collections.EMPTY_LIST: nestedSource;
    }
    
    public List<String> getAttitudeLinks() {
        return attitudeLinks == null ? Collections.EMPTY_LIST : attitudeLinks;
    }
    
    public Uncertainty getUncertainty() {
        return uncertainty;
    }
    
    public boolean isImplicit() {
        return implicit;
    }
    
    public Uncertainty getSubjectiveUncertainty() {
        return subjectiveUncertainty;
    }
    
    public Intensity getIntensity() {
        return intensity;
    }
    
    public Polarity getPolarity() {
        return polarity;
    }
    
    public List<String> getInsubstantial() {
        return insubstantial == null ? Collections.EMPTY_LIST : insubstantial ;
    }
    
    //</editor-fold> 
}
