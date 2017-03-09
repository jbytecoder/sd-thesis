/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa.parser;

/**
 *
 * @author uriel
 */
public class MpqaFormatException extends Exception {
    public static MpqaFormatException detached() {
        return new MpqaFormatException("source is unavailable. detached?");
    }
    public static MpqaFormatException missing( String event, String what ) {
        return new MpqaFormatException(new StringBuilder(event)
                .append(" is missing ")
                .append(what)
                .toString());
    }

    public MpqaFormatException(String message) {
        super(message);
    }
}
