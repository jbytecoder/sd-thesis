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
public interface IConfigurable<C,E extends Exception> {
    C setup( String key,String value ) throws E;
}
