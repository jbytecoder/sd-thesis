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
public enum Intensity {
    NEUTRAL,
    LOW,
    MEDIUM,
    HIGH,
    EXTREME;
    
    public static Intensity fromString(String txt) {
        return Enum.valueOf(Intensity.class, txt.trim().toUpperCase());
    }
}
