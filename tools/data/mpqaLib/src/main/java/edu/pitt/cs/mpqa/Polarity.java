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
public enum Polarity {
    NEUTRAL(true),
    POSITIVE(true),
    NEGATIVE(true),
    BOTH(true),
    UNCERTAIN_NEUTRAL(false),
    UNCERTAIN_POSITIVE(false),
    UNCERTAIN_NEGATIVE(false),
    UNCERTAIN_BOTH(false),
    ;
    
    public static Polarity fromString(String txt) {
        return Enum.valueOf(Polarity.class, txt.trim()
                                               .toUpperCase()
                                               .replace('-', '_'));
    }
    
    private final boolean certain;

    private Polarity(boolean certain) {
        this.certain = certain;
    }
    
    public boolean isIndefinite() {
        switch(this){
            case NEUTRAL:
            case UNCERTAIN_NEUTRAL:
            case BOTH:
            case UNCERTAIN_BOTH:
                return true;
        }
        return false;
    }
    public boolean isPositive() {
        switch(this) {
            case POSITIVE:
            case UNCERTAIN_POSITIVE:
            case BOTH:
            case UNCERTAIN_BOTH:
                return true;
        }
        return false;
    }
    public boolean isNegative() {
        switch(this) {
            case NEGATIVE:
            case UNCERTAIN_NEGATIVE:
            case BOTH:
            case UNCERTAIN_BOTH:
                return true;
        }
        return false;
    }
    public boolean isCertain() {
        return certain;
    }
}
