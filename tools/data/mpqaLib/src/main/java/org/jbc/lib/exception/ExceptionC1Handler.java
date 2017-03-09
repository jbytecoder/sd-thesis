/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbc.lib.exception;

/**
 *
 * @author uriel
 */
public interface ExceptionC1Handler<C extends Throwable,R,C1> {
     R handle( C cause, C1 c1,Object... context );
}
