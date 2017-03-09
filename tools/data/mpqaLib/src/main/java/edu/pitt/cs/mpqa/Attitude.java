/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import com.google.common.base.Preconditions;

/**
 *
 * @author uriel
 */
public class Attitude extends Annotation  {
    public static class Builder extends Annotation.Builder<Attitude,Builder> {

        //<editor-fold defaultstate="collapsed" desc="Getters">
        public AttitudeType getType() {
            return getProduct().type;
        }
        
        public String getTargetFrame() {
            return getProduct().targetFrame;
        }
        
        public Uncertainty getUncertainty() {
            return getProduct().uncertainty;
        }
        
        public boolean isInfered() {
            return getProduct().infered;
        }
        //</editor-fold>

        @Override
        public Attitude createProduct() {
            return new Attitude();
        }
       
        @Override
        public Attitude build() {
//            Preconditions.checkState(getProduct().id!=null,"Id must be not null");
//            Preconditions.checkState(getProduct().type!=null,"Type must be not null");
//            Preconditions.checkState(getProduct().targetFrame!=null,"targetFrame must be not null");
            return super.build();
        }
        
        //<editor-fold defaultstate="collapsed" desc="Setters">
        public Builder setType( String att ) {
            getProduct().type = AttitudeType.fromString(att);
            return this;
        }
        public Builder setTargetFrame( String tf ) {
            getProduct().targetFrame = tf;
            return this;
        }
        public Builder setUncertainty( String un ) {
            getProduct().uncertainty = Uncertainty.fromString(un);
            return this;
        }
        public Builder setAttitudeUncertainty( String un ) {
            getProduct().uncertainty = Uncertainty.fromString(un);
            return this;
        }
        public Builder setInfered( String ns ) {
            getProduct().infered = ns.length()==1?ns.charAt(0)=='1':Boolean.valueOf(ns);
            return this;
        }
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    AttitudeType type;
    private String targetFrame;
    
    private Uncertainty uncertainty= Uncertainty.NONE;
    private boolean infered;
//</editor-fold>

    protected Attitude() {}

    @Override
    public String getTypeName(String ver) {
        return "attitude";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public AttitudeType getType() {
        return type;
    }
    
    public String getTargetFrame() {
        return targetFrame;
    }
    
    public Uncertainty getUncertainty() {
        return uncertainty;
    }
    
    public boolean isInfered() {
        return infered;
    }
    //</editor-fold>
    
    
}
