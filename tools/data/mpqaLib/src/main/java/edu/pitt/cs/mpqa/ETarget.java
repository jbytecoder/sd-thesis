/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

/**
 *
 * @author uriel
 */
public class ETarget extends Annotation {

    public static class Builder extends Annotation.Builder<ETarget,Builder> {

        @Override
        public ETarget createProduct() {
            return new ETarget();
        }
        
    }
    
    @Override
    public String getTypeName(String ver) {
        return "etarget";
    }
    
}
