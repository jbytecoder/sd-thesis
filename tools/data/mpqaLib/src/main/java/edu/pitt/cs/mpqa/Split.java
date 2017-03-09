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
public class Split extends Annotation {

    public static class Builder extends Annotation.Builder<Split,Builder> {
        @Override
        public Split createProduct() {
            return new Split();
        }   
    }
    
    @Override
    public String getTypeName(String ver) { return "split"; }
    
}
