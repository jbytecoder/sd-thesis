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
public class TargetFrame extends Annotation  {
    public static class Builder extends Annotation.Builder<TargetFrame,Builder>{

        @Override
        public TargetFrame createProduct() {
            return new TargetFrame();
        }

        public List<String> getSTargets() {
            return getProduct().getSTargets();
        }

        public List<String> getETargets() {
            return getProduct().getETargets();
        }
        
        @Override
        public TargetFrame build() {
//            Preconditions.checkState(getProduct().id!=null,"Id must be set");
            return super.build();
        }
        
        public Builder setSTargets( String data ) {
            if( data.equals("none") ) {
                getProduct().sTargets = null;
            } else {
                getProduct().sTargets = new ArrayList(Arrays.asList(data.split(",")));
            }
             return this;
        }
        public Builder setETargets( String data ) {
            if( data.equals("none") ) {
                getProduct().eTargets = null;
            } else {
                getProduct().eTargets = new ArrayList(Arrays.asList(data.split(",")));
            }
             return this;
        }
        
    }
    
    private List<String> sTargets;
    private List<String> eTargets;

    protected  TargetFrame() {}
    
    public List<String> getSTargets() {
        return sTargets == null ? Collections.EMPTY_LIST : sTargets;
    }

    public List<String> getETargets() {
        return eTargets == null ? Collections.EMPTY_LIST : eTargets;
    }
   
    
     @Override
    public String getTypeName(String ver) {
        return "targetFrame";
    }
}
