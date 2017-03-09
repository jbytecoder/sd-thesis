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
public enum AttitudeType {
    POSITIVE_SENTIMENT(1),
    POSITIVE_ARGUING(1),
    POSITIVE_AGREEMENT(1),
    POSITIVE_INTENTION(1),
    NEGATIVE_SENTIMENT(-1),
    NEGATIVE_ARGUING(-1),
    NEGATIVE_AGREEMENT(-1),
    NEGATIVE_INTENTION(-1),
    SPECULATION(0),
    OTHER(0)
    ;
    
    public static AttitudeType fromString( String txt ) {
        txt = txt.trim().toLowerCase();
        if( txt.startsWith("sentiment") ) {
            if( txt.endsWith("pos") ) {
                return AttitudeType.POSITIVE_SENTIMENT;
            } else if ( txt.endsWith("neg") ) {
                return AttitudeType.NEGATIVE_SENTIMENT;
            } 
        } else if( txt.startsWith("arguing") ) {
            if( txt.endsWith("pos") ) {
                return AttitudeType.POSITIVE_ARGUING;
            } else if ( txt.endsWith("neg") ) {
                return AttitudeType.NEGATIVE_ARGUING;
            } 
        } else if( txt.startsWith("agreement") ) {
            if( txt.endsWith("pos") ) {
                return AttitudeType.POSITIVE_AGREEMENT;
            } else if ( txt.endsWith("neg") ) {
                return AttitudeType.NEGATIVE_AGREEMENT;
            } 
        } else if( txt.startsWith("intention") ) {
            if( txt.endsWith("pos") ) {
                return AttitudeType.POSITIVE_INTENTION;
            } else if ( txt.endsWith("neg") ) {
                return AttitudeType.NEGATIVE_INTENTION;
            } 
        } else if( txt.startsWith("speculation") ) {
             return AttitudeType.SPECULATION ;
        } else if( txt.startsWith("other") ) {
            return AttitudeType.OTHER;
        }
        throw new IllegalArgumentException("Not a AtitudeType: "+txt);
    } 
    
    private int polarity;

    private AttitudeType(int polarity) {
        this.polarity = polarity;
    }
    
    public boolean isPositive() {
        return polarity > 0;
    }
    public boolean isNegative() {
        return polarity < 0;
    }
    
}
