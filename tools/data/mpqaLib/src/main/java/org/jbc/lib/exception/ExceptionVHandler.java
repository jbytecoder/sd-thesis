/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbc.lib.exception;

/**
 *
 * @author uriel
 * @param <C> throwable type to be handeled
 */
public interface ExceptionVHandler< C extends Throwable > {
    void handle( C cause, Object... context );
}
