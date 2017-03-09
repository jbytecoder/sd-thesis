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
public interface IBuilder<P,E extends Exception> {
    P build() throws E;
}
