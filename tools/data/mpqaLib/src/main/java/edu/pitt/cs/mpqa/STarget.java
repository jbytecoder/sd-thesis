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
public class STarget extends Annotation {
    public static class Builder extends Annotation.Builder<STarget,Builder> {
        @Override
        public STarget createProduct() {
            return new STarget();
        }
    }

    @Override
    public String getTypeName(String ver) {
        return "STarget";
    }
    
    
}
