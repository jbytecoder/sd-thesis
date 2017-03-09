/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.cs.mpqa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author uriel
 */
public class MetaData {
    public static class Builder {
        //<editor-fold defaultstate="collapsed" desc="Fields">
        private MetaData product;
        
        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:dd");
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Getters">
        public String getReference() {        
            return product.reference;
        }

        public String getSource() {
            return product.source;
        }

        public String getDescription() {
            return product.description;
        }

        public String getTitle() {
            return product.title;
        }

        public long getDocumentTime() {
            return product.documentTime;
        }

        public long getCreateTime() {
            return product.createTime;
        }

        public String getMediaFile() {
            return product.mediaFile;
        }

        public String getMediaType() {
            return product.mediaType;
        }

        public String getScribe() {
            return product.scribe;
        }

        public Set<String> getTopics() {
            if( product.topics == null ) {
                product.topics = new HashSet<>();
            }
            return product.topics;
        }

        public Set<String> getCountries() {
            if( product.countries == null ) {
                product.countries = new HashSet<>();
            }
            return product.countries;
        }

        public Set<String> getRegions() {
            if( product.regions == null ) {
                product.regions = new HashSet<>();
            }
            return product.regions;
        }

        public Set<String> getSubregions() { 
            if( product.subregions == null ) {
                product.subregions = new HashSet<>();
            }
            return product.subregions;
        }
        
        protected MetaData getProduct() {
            if( product == null ) {
                product = new MetaData();
            }
            return product;
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Setters">
        public Builder setReference(String reference) {
            getProduct().reference = reference;
            return this;
        }
        
        public Builder setSource(String source) {
            getProduct().source = source;
            return this;
        }
        
        public Builder setDescription(String description) {
            getProduct().description = description;
            return this;
        }
        
        public Builder setTitle(String title) {
            getProduct().title = title;
            return this;
        }
        
        public Builder setDocumentTime(long documentTime) {
            getProduct().documentTime = documentTime;
            return this;
        }
        public Builder setDocumentTime(String documentTime) throws ParseException {
            getProduct().documentTime =  parseTime(documentTime);
            return this;
        }
        
        public Builder setCreateTime(long createTime) {
            getProduct().createTime = createTime;
            return this;
        }
        public Builder setCreateTime(String createTime) throws ParseException {
            getProduct().createTime = parseTime(createTime);
            return this;
        }
        
        
        private long parseTime( String time ) throws ParseException {
            if( time.contains("T") ) {
                return dateTimeFormat.parse(time).getTime();
            } else {
                return dateFormat.parse(time).getTime();
            }
        }
        
        public Builder setMediaFile(String mediaFile) {
            getProduct().mediaFile = mediaFile;
            return this;
        }
        
        public Builder setMediaType(String mediaType) {
            getProduct().mediaType = mediaType;
            return this;
        }
        
        public Builder setScribe(String scribe) {
            getProduct().scribe = scribe;
            return this;
        }
        
        public Builder setTopics(Set<String> topics) {
            getProduct().topics = topics;
            return this;
        }
        public Builder addTopic( String topic ) {
            getTopics().add(topic);
            return this;
        }
        public Builder removeTopic( String topic ) {
            getTopics().remove(topic);
            return this;
        }
        
        public Builder setCountries(Set<String> countries) {
            getProduct().countries = countries;
            return this;
        }
        public Builder addCountry( String country ) {
            getCountries().add(country);
            return this;
        }
        public Builder removeCountry( String country ) {
            getCountries().remove(country);
            return this;
        }        
        
        public Builder setRegions(Set<String> regions) {
            getProduct().regions = regions;
            return this;
        }
        public Builder addRegion( String region ) {
            getRegions().add(region);
            return this;
        }
        public Builder removeRegion( String region ) {
            getRegions().remove(region);
            return this;
        }
        
        public Builder setSubregions(Set<String> subregions) {
            getProduct().subregions = subregions;
            return this;
        }
        public Builder addSubRegion( String subregion ) {
            getRegions().add(subregion);
            return this;
        }
        public Builder removeSubRegion( String subregion ) {
            getRegions().remove(subregion);
            return this;
        }
        //</editor-fold>
        
        public MetaData build() {
            MetaData r = product;
            product = null;
            return r;
        }   
    }
    
    private MetaData() {
        // Builder-only constructor
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
        private String reference;
        
        private String source;
        private String description;
        private String title;
        
        private long documentTime;
        private long createTime;
        
        private String mediaFile;
        private String mediaType;
        private String scribe;
        
        private Set<String> topics;
        private Set<String> countries;
        private Set<String> regions;
        private Set<String> subregions;
        
        //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public String getReference() {        
        return reference;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public long getDocumentTime() {
        return documentTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getMediaFile() {
        return mediaFile;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getScribe() {
        return scribe;
    }

    public Set<String> getTopics() {
        return topics==null?Collections.EMPTY_SET:topics;
    }

    public Set<String> getCountries() {
        return countries==null?Collections.EMPTY_SET:countries;
    }

    public Set<String> getRegions() {
        return regions==null?Collections.EMPTY_SET:regions;
    }

    public Set<String> getSubregions() {        
        return subregions==null?Collections.EMPTY_SET:subregions;
    }
    //</editor-fold>    
}
