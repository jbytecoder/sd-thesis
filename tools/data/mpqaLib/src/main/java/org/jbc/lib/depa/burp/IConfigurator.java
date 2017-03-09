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
public interface IConfigurator<P,E extends Exception,T> {
    T set( P p, String key, String value ) throws E;
}
