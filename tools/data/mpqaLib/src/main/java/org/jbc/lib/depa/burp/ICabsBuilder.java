/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbc.lib.depa.burp;

/**
 *
 * @author uriel
 */
public interface ICabsBuilder<S extends Exception,
                              B extends Exception,
                              P,
                              T extends ICabsBuilder<S,B,P,T> > 
extends IBuilder<P,B> 
{
    T set( String key, String value ) throws S;
}
