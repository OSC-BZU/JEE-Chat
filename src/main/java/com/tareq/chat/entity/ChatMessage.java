/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareq.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tareq
 */
@NamedQueries({
    @NamedQuery(name="ChatMessage.list", query="SELECT cm FROM ChatMessage cm")
})
@Entity
public class ChatMessage implements Serializable {
    
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String message;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = true, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date sentOn;
    
    private String screenName;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

    @JsonProperty
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty
    public Date getSentOn() {
        return sentOn;
    }

    @JsonIgnore
    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    @JsonProperty
    public String getScreenName() {
        return screenName;
    }

    @JsonProperty
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    

}
