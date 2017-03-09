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
public enum Uncertainty {
    NONE,
    SOMEWHAT,
    VERY;
    public static Uncertainty fromString( String txt ) {
        txt = txt.toUpperCase();
        for( Uncertainty c : values() ) {
            if( txt.contains(c.name()) ) {
                return c;
            }
        }
        return Uncertainty.NONE;
    }
}
