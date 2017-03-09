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
public interface ExceptionHandler<C extends Throwable,R> {
    R handle( C cause, Object... context );
}
