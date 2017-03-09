package edu.pitt.cs.mpqa.parser;

import java.util.function.Consumer;

/**
 *
 * @author uriel
 */
public interface IMpqaFormatParser extends AutoCloseable {
    public static final int EVENT_ANNOTATION = 1;
    public static final int EVENT_COMMENT = 2;
    
    public interface IEvent<E extends IEvent<E> > {
        int getType();
        E detach(); 
    }
    public interface IAnnotation extends IEvent<IAnnotation> {
         public interface IFeature extends Cloneable {
            String getFeatureKey() throws MpqaFormatException;
            String getFeatureValue() throws MpqaFormatException;
        }
        
        String getID() throws MpqaFormatException;
        String getAnnotationType() throws MpqaFormatException;
        int getContentFrom() throws MpqaFormatException;
        int getContentTo() throws MpqaFormatException;
        
        boolean nextFeature( Consumer<IFeature> action );
    }
    public interface IComment extends IEvent<IComment> {
        String getCommentContent();
    }
    
    boolean next( Consumer<IEvent> action );
}
