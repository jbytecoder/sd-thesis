/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import org.jbc.lib.depa.burp.IConfigurator;

/**
 *
 * @author uriel
 */
public class MpqaFormat {
    
    public static MpqaFormat forV30Strict() {
        return new MpqaFormat("3.0", true);
    }
    
    private final String version;
    private final boolean strict;

    private MpqaFormat(String version, boolean strict) {
        this.version = version;
        this.strict = strict;
    }
   
    private void ensureType( String type, String exp ) throws AnnotationException
    {
        if( !type.equals(exp) ) {
            throw AnnotationException.unsupportedType(type);
        }
    }
    
    private void ensureTypeProperty( String type, 
                                     String p, 
                                     String exp ) throws AnnotationException {

        if( !p.equals(exp) ) {
            throw AnnotationException.unsupportedProperty(type, p);
        }
    }
    
    /**
     * Resolve type name ( respecting version ) into builder.
     * 
     * Supported types:
     * <table border="2" >
     * <tr><th>Type Name</th>
     *     <th>Type</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>agent</td>
     *     <td>{@link Agent}</td>
     *     <td>ag*</td>   
     * </tr>
     * <tr><td>expressive-subjectivity</td>
     *     <td>{@link ExpressiveSubjectivity}</td>
     *     <td>e*</td>
     * </tr>
     * <tr><td>direct-subjective</td>
     *     <td>{@link DirectSubjective}</td>
     *     <td>d*</td>
     * </tr>
     * <tr><td>objective-speech-event</td>
     *     <td>{@link ObjectiveSpechEvent}</td>
     *     <td>o*</td>
     * </tr>
     * <tr><td>attitude</td>
     *     <td>{@link Attitude}</td>
     *     <td>at*</td>
     * </tr>
     * <tr><td>targetFrame</td>
     *     <td>{@link TargetFrame}</td>
     *     <td>t*</td>
     * </tr>
     * <tr><td>starget</td>
     *     <td>Unsupported</td>
     *     <td>s*</td>
     * </tr>
     * </table>
     * 
     * @param type specification of requested type
     * @param ver specification of format version in which this
     *            type schould be recognizable  
     * @return builder for type, with {@link IConfigurator} applied
     */     
    public Annotation.Builder<?,?> getBuilderFor( String type,
                                                  String ver ) 
                                                throws AnnotationException  
    {
       type = type.trim().toLowerCase();
       switch(type.charAt(0)) {
           case 'a' :
               switch(type.charAt(1)) {
                   case 'g' :
                       if(strict) ensureType(type,"agent");
                       return new Agent.Builder()
                               .applyConfigurator(getAgentCfg(ver));
                   case 't' :
                       if(strict) ensureType(type,"attitude"); 
                      return new Attitude.Builder()
                              .applyConfigurator(getAttitudeCfg(ver));
                   default:
                       throw AnnotationException.unsupportedType(type);
               }
           case 'e':
               switch(type.charAt(1)) {
                   case 'x' :
                       if(strict) ensureType(type,"expressive-subjectivity"); 
                            return new ExpressiveSubjectivity.Builder()
                                    .applyConfigurator(getExpressiveSubjectivityCfg(ver));
                   case 't' :
                        if(strict) ensureType(type,"etarget"); 
                            return new ETarget.Builder()
                                    .applyConfigurator(getETargetCfg(ver));
               }
               
           case 'd':
               if(strict) ensureType(type,"direct-subjective"); 
               return new DirectSubjective.Builder()
                       .applyConfigurator(getDirectSubjectiveCfg(ver));
           case 'o':
               if(strict) ensureType(type,"objective-speech-event"); 
               return new ObjectiveSpechEvent.Builder()
                       .applyConfigurator(getObjectiveSpechEventCfg(ver));
           case 't':
               if(strict) ensureType(type,"targetframe"); 
               return new TargetFrame.Builder()
                       .applyConfigurator(getTargetFrameCfg(ver));
           case 's':
               switch( type.charAt(1) ) {
                   case 't':
                       if(strict) ensureType(type,"starget");
                       return new STarget.Builder()
                                .applyConfigurator(getSTargetCfg(ver));
                   case 'e':
                       if(strict) ensureType(type,"sentence");
                       return new Sentence.Builder()
                                .applyConfigurator(getSentenceCfg(ver));
                   case 'p':
                       if(strict) ensureType(type,"split");
                       return new Split.Builder()
                                .applyConfigurator(getSplitCfg(ver));    
               }
               
                   
           default: throw AnnotationException.unsupportedType(type);
       }
    }
    
    public Annotation.Builder<?,?> getBuilderFor( String type ) 
                                                throws AnnotationException  
    {
        return getBuilderFor(type,version);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Agent">
     private interface AgentCfg
     extends IConfigurator<Agent.Builder,
             AnnotationException,
             Agent.Builder>{}

     private AgentCfg getAgentCfg(String ver) {
         return this::agent;
     }

     /**
      * Configures properties of {@link Agent} type.
      * 
      * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link Agent.Builder#setId(java.lang.String)}</td>
     *     <td>i*</td>
     * </tr>
     * <tr><td>nested-source</td>
     *     <td>{@link Agent.Builder#setNestedSource(java.lang.String) }</td>
     *     <td>n*</td>
     * </tr>
     * <tr><td>agent-uncertain</td>
     *     <td>{@link Agent.Builder#setUncertain(java.lang.String) }</td>
     *     <td>a*</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */
     public Agent.Builder agent( Agent.Builder b,
             String key,
             String value )
             throws AnnotationException
     {
         key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 if(strict) {
                     ensureTypeProperty(b.getTypeName(version), 
                                        key,
                                        "id");
                 }
                 return b.setId(value);
             case 'n' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), 
                                         key, 
                                         "nested-source");
                  }
                 return b.setNestedSource(value);
             case 'a' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), 
                                         key, 
                                         "agent-uncertain");
                  }
                 return b.setUncertain(value);
             default :
                 throw AnnotationException.unsupportedProperty(
                         b.getTypeName(version),
                         key);
         }
     }
    //</editor-fold>
     
    //<editor-fold defaultstate="collapsed" desc="ExpressiveSubjectivity">
     private interface ExpressiveSubjectivityCfg
     extends IConfigurator<ExpressiveSubjectivity.Builder,
             AnnotationException,
             ExpressiveSubjectivity.Builder>{}
         
    private ExpressiveSubjectivityCfg getExpressiveSubjectivityCfg(String ver)
    {
        return this::expressiveSubjectivity;
    }
    
    /**
      * Configures properties of {@link Agent} type.
      * 
      * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setId(java.lang.String)}</td>
     *     <td>i.{2,2}</td>
     * </tr>
     * <tr><td>nested-source</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setNestedSource(java.lang.String)}</td>
     *     <td>n.{,13}</td>
     * </tr>
     * <tr><td>polarity</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setPolarity(java.lang.String)  }</td>
     *     <td>p*</td>
     * </tr>
     * <tr><td>targetFrame</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setTargetFrame  }</td>
     *     <td>t*</td>
     * </tr>
     * <tr><td>nested-source-uncertain</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setNestedSourceUncertainty(java.lang.String)}</td>
     *     <td>n.{14,}</td>
     * </tr>
     * <tr><td>intensity</td>
     *     <td>{@link ExpressiveSubjectivity.Builder#setIntensity(java.lang.String) }</td>
     *     <td>i.{3,}</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */
    public ExpressiveSubjectivity.Builder expressiveSubjectivity( 
            ExpressiveSubjectivity.Builder b,
            String key,
            String value )
            throws AnnotationException
    {
        key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 if( key.length() <= 2 ) {
                    if(strict) {
                        ensureTypeProperty(b.getTypeName(version), key, "id");
                    }
                    return b.setId(value);
                 } else {
                     if(strict) {
                        ensureTypeProperty(b.getTypeName(version), key, "intensity");
                    }
                    return b.setIntensity(value);
                 }
             case 'n' :
                 if( key.length() <= 13 ) {
                    if(strict) {
                        ensureTypeProperty(b.getTypeName(version), key, "nested-source");
                    }
                   return b.setNestedSource(value);
                 } else {
                     if(strict) {
                        ensureTypeProperty(b.getTypeName(version), key, "nested-source-uncertain");
                    }
                     return b.setNestedSourceUncertainty(value);
                 }
             case 'p' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "polarity");
                  }
                 return b.setPolarity(value);
             case 't' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "targetFrame");
                  }
                 return b.setTargetFrame(value);    
             default :
                 throw AnnotationException.unsupportedProperty(b.getTypeName(version), key);
         }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DirectSubjective">
     private interface DirectSubjectiveCfg
     extends IConfigurator<DirectSubjective.Builder,
             AnnotationException,
             DirectSubjective.Builder>{}
                    
                
     private DirectSubjectiveCfg getDirectSubjectiveCfg(String ver)
     {
         return this::directSubjective;
     }
    
    /**
      * Configures properties of {@link DirectSubjective} type.
      * 
      * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link DirectSubjective.Builder#setId(java.lang.String)}</td>
     *     <td>id</td>
     * </tr>
     * <tr><td>nested-source</td>
     *     <td>{@link DirectSubjective.Builder#setNestedSource(java.lang.String) }</td>
     *     <td>n*</td>
     * </tr>
     * <tr><td>attitude-link</td>
     *     <td>{@link DirectSubjective.Builder#setAttitudeLinks(java.lang.String)}</td>
     *     <td>at*</td>
     * </tr>
     * <tr><td>annotation-uncertain</td>
     *     <td>{@link DirectSubjective.Builder#setUncertainty(java.lang.String)}</td>
     *     <td>an*</td>
     * </tr>
     * <tr><td>implicit</td>
     *     <td>{@link DirectSubjective.Builder#setImplicit(java.lang.String)}</td>
     *     <td>im*</td>
     * </tr>
     * <tr><td>subjective-uncertain</td>
     *     <td>{@link DirectSubjective.Builder#setSubjectiveUncertainty(java.lang.String) </td>
     *     <td>s*</td>
     * </tr>
     * <tr><td>intensity</td>
     *     <td>{@link DirectSubjective.Builder#setIntensity(java.lang.String)</td>
     *     <td>in.{,7}</td>
     * </tr>
     * <tr><td>expression-intensity</td>
     *     <td>{@link DirectSubjective.Builder#set}</td>
     *     <td>e*</td>
     * </tr>
     * <tr><td>polarity</td>
     *     <td>{@link DirectSubjective.Builder#setPolarity(java.lang.String) }</td>
     *     <td>p*</td>
     * </tr>
     * <tr><td>insubstantial</td>
     *     <td>{@link DirectSubjective.Builder#setInsubstantial(java.lang.String)  }</td>
     *     <td>in.{7,}</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */ 
    public DirectSubjective.Builder directSubjective( 
            DirectSubjective.Builder b,
            String key,
            String value )
            throws AnnotationException
    {
         key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 switch(key.charAt(1)) {
                     case 'd':
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "id");
                        }
                        return b.setId(value);   
                     case 'm':
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "implicit");
                        }
                        return b.setImplicit(value); 
                     case 'n':
                         if( key.length() <= 9 ) {
                             if(strict) {
                                ensureTypeProperty(b.getTypeName(version), key, "intensity");
                             }
                            return b.setIntensity(value); 
                         } else {
                            if(strict) {
                              ensureTypeProperty(b.getTypeName(version), key, "insubstantial");
                            }
                            return b.setInsubstantial(value); 
                         }
                 }
                
             case 'n' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "nested-source");
                  }
                 return b.setNestedSource(value);
             case 'a' :
                 switch(key.charAt(9)) {
                     case 'l' :
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "attitude-link");
                        }
                        return b.setAttitudeLinks(value);
                     case 'n':
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "annotation-uncertain");
                        }
                        return b.setUncertainty(value);
                    case 't':
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "attitude-type");
                        }
                        return b.setUncertainty(value);
                 }
             case 's':
                 if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "subjective-uncertain");
                  }
                 return b.setSubjectiveUncertainty(value);
             case 'e':    
                 return b;
             case 'p':
                 if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "polarity");
                  }
                 return b.setPolarity(value);
             default :
                 throw AnnotationException.unsupportedProperty(b.getTypeName(version), key+" gen");
         }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ObjectiveSpechEvent">
    private interface ObjectiveSpechEventCfg
    extends IConfigurator<ObjectiveSpechEvent.Builder,
            AnnotationException,
            ObjectiveSpechEvent.Builder>{}
                    
                
    private ObjectiveSpechEventCfg getObjectiveSpechEventCfg(String ver)
    {
        return this::objectiveSpechEvent;
    }

    /**
      * Configures properties of {@link ObjectiveSpechEvent} type.
      * 
      * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setId(java.lang.String)}</td>
     *     <td>id</td>
     * </tr>
     * <tr><td>nested-source</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setNestedSource(java.lang.String) }</td>
     *     <td>n*</td>
     * </tr>
     * <tr><td>targetFrame</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setTargetFrame(java.lang.String)  }</td>
     *     <td>t*</td>
     * </tr>
     * <tr><td>annotation-uncertain</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setTargetFrame(java.lang.String)  }</td>
     *     <td>a*</td>
     * </tr>
     * <tr><td>implicit</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setTargetFrame(java.lang.String)  }</td>
     *     <td>im*</td>
     * </tr>
     * <tr><td>objective-uncertain</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setTargetFrame(java.lang.String)  }</td>
     *     <td>o*</td>
     * </tr>
     * <tr><td>insubstantial</td>
     *     <td>{@link ObjectiveSpechEvent.Builder#setTargetFrame(java.lang.String)  }</td>
     *     <td>in*</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */
    public ObjectiveSpechEvent.Builder objectiveSpechEvent( 
            ObjectiveSpechEvent.Builder b,
            String key,
            String value )
            throws AnnotationException
    {
        key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 switch(key.charAt(1)) {
                     case 'd' :
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "id");
                        }
                        return b.setId(value);
                     case 'm' :
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "implicit");
                        }
                        return b.setImplicit(value);
                     case 'n' :
                        if(strict) {
                            ensureTypeProperty(b.getTypeName(version), key, "insubstantial");
                        }
                        return b.setInsubstantial(value);    
                 }
             case 'n' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "nested-source");
                  }
                 return b.setNestedSource(value);
             case 't' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "targetFrame");
                  }
                 return b.setTargetFrame(value);    
             case 'a' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "annotation-uncertain");
                  }
                 return b.setUncertainty(value);
             case 'o' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), key, "objective-uncertain");
                  }
                 return b.setObjectiveUncertainty(value);    
             default :
                 throw AnnotationException.unsupportedProperty(b.getTypeName(version), key);
         }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Attitude">
     private interface AttitudeCfg
     extends IConfigurator<Attitude.Builder,
             AnnotationException,
             Attitude.Builder>{}
                    
                
     private AttitudeCfg getAttitudeCfg(String ver)
     {
         return this::attitude;
     }

    /**
     * Configures properties of {@link Attitude} type.
     * 
     * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link Attitude.Builder#setId(java.lang.String)}</td>
     *     <td>i.</td>
     * </tr>
     * <tr><td>attitude-type</td>
     *     <td>{@link Attitude.Builder#setType(java.lang.String)}</td>
     *     <td>a.{,12}</td>
     * </tr>
     * <tr><td>targetFrame</td>
     *     <td>{@link Attitude.Builder#setTargetFrame(java.lang.String)}</td>
     *     <td>t*</td>
     * </tr>
     * <tr><td>attitude-uncertain</td>
     *     <td>{@link Attitude.Builder#setUncertainty(java.lang.String)}</td>
     *     <td>a.{13,}</td>
     * </tr>
     *  <tr><td>inferred</td>
     *     <td>{@link Attitude.Builder#setInfered(java.lang.String)}</td>
     *     <td>i.{1,}</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */ 
    public Attitude.Builder attitude( 
            Attitude.Builder b,
            String key,
            String value )
            throws AnnotationException
    {
       key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 if( key.length() <= 2 ) {
                    if(strict) {
                        ensureTypeProperty(b.getTypeName(version), 
                                           key,
                                           "id");
                    }
                    return b.setId(value); 
                 } else {
                    if(strict) {
                        ensureTypeProperty(b.getTypeName(version), 
                                           key,
                                           "inferred");
                 }
                 return b.setInfered(value);
                 }
             case 't' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), 
                                         key, 
                                         "targetFrame");
                  }
                 return b.setTargetFrame(value);
             case 'a' :
                 if( key.length() <= 13 ) {
                    if(strict) {
                        ensureTypeProperty(b.getTypeName(version), 
                                           key, 
                                           "attitude-type");
                    }
                   return b.setType(value);
                 } else {
                     if(strict) {
                        ensureTypeProperty(b.getTypeName(version), 
                                           key, 
                                           "attitude-uncertain");
                    }
                     return b.setAttitudeUncertainty(key);
                 }
             default :
                 throw AnnotationException.unsupportedProperty(
                         b.getTypeName(version),
                         key);
         }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="TargetFrame">
    private interface TargetFrameCfg
            extends IConfigurator<TargetFrame.Builder, AnnotationException, TargetFrame.Builder> {
    }
    
    private TargetFrameCfg getTargetFrameCfg(String ver) {
        return this::targetFrame;
    }
    
    /**
     * Configures properties of {@link TargetFrame} type.
     * 
     * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>id</td>
     *     <td>{@link TargetFrame.Builder#setId(java.lang.String)}</td>
     *     <td>i.</td>
     * </tr>
     * <tr><td>sTarget-link</td>
     *     <td>{@link TargetFrame.Builder#setSTargets(java.lang.String)}</td>
     *     <td>s*</td>
     * </tr>
     * <tr><td>newETarget-link</td>
     *     <td>{@link TargetFrame.Builder#setETargets(java.lang.String) }</td>
     *     <td>n*</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */ 
    public TargetFrame.Builder targetFrame(
            TargetFrame.Builder b,
            String key,
            String value)
            throws AnnotationException {
         key = key.trim().toLowerCase();
         switch(key.charAt(0)) {
             case 'i' :
                 if(strict) {
                     ensureTypeProperty(b.getTypeName(version), 
                                        key,
                                        "id");
                 }
                 return b.setId(value);
             case 's' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), 
                                         key, 
                                         "sTarget-link");
                  }
                 return b.setSTargets(value);
             case 'n' :
                  if(strict) {
                      ensureTypeProperty(b.getTypeName(version), 
                                         key, 
                                         "newETarget-link");
                  }
                 return b.setETargets(value);
             default :
                 throw AnnotationException.unsupportedProperty(
                         b.getTypeName(version),
                         key);
         }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Sentence">
    private interface SentenceCfg
            extends IConfigurator<Sentence.Builder, AnnotationException, Sentence.Builder> {
    }
    
    private SentenceCfg getSentenceCfg(String ver) {
        return this::sentence;
    }
    
    /**
      * Configures properties of {@link Sentence} type.
      * 
      * Supported properties names:
     * <table border="2" >
     * <tr><th>Key</th>
     *     <th>Setter</th>
     *     <th>Pattern</th>
     * </tr>
     * <tr><td>nested-source</td>
     *     <td>{@link Agent.Builder#setNestedSource(java.lang.String) }</td>
     *     <td>n*</td>
     * </tr>
     * </table>
      * 
      * @param b the configured builder
      * @param key property specifier
      * @param value desiered value
      * @return parameter b
      * @throws AnnotationException 
      */
    public Sentence.Builder sentence(
            Sentence.Builder b,
            String key,
            String value)
            throws AnnotationException {
        if( key.equals("nested-source") ) {
            return b.setNestedSource(value);
        }
        throw AnnotationException.unsupportedProperty(b.getTypeName(version), key);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="STarget">
    private interface STargetCfg
            extends IConfigurator<STarget.Builder, AnnotationException, STarget.Builder> {
    }
    
    private static STargetCfg getSTargetCfg(String ver) {
        return MpqaFormat::starget;
    }
    
    public static STarget.Builder starget(
            STarget.Builder b,
            String key,
            String value)
            throws AnnotationException {
        return b;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ETarget">
    private interface ETargetCfg
            extends IConfigurator<ETarget.Builder, AnnotationException, ETarget.Builder> {
    }
    
    private static ETargetCfg getETargetCfg(String ver) {
        return MpqaFormat::etarget;
    }
    
    public static ETarget.Builder etarget(
            ETarget.Builder b,
            String key,
            String value)
            throws AnnotationException {
        return b;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Split">
    private interface SplitCfg
            extends IConfigurator<Split.Builder, AnnotationException, Split.Builder> {
    }
    
    private static SplitCfg getSplitCfg(String ver) {
        return MpqaFormat::split;
    }
    
    public static Split.Builder split(
            Split.Builder b,
            String key,
            String value)
            throws AnnotationException {
        return b;
    }
    //</editor-fold>
}
