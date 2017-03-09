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
public abstract class ACabsBuilder<S extends Exception,
                                   B extends Exception,
                                   P,
                                   T extends ACabsBuilder<S,B,P,T> > 
implements ICabsBuilder<S,B,P,T> 
{
    protected IConfigurator<T,S,T> cfg;

    public T applyConfigurator( IConfigurator<T,S,T> cfg ) {
        this.cfg =cfg;
        return (T)this;
    }
    
    @Override
    public T set(String key, String value) throws S {
        if( cfg == null ) {
            throw new IllegalStateException(" cfg not set ");
        }
        return cfg.set((T)this, key, value);
    }

}
