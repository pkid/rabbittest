package com.sap.ngp.sample.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NEW_SCOPE")
public class TestScope2 implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    private UUID  uUID;
    

    @Column(nullable = false)
    private boolean isInScope ;

   

    public boolean isInScope() {
        return isInScope;
    }

    public void setInScope(boolean isInScope) {
        this.isInScope = isInScope;
    }

    public TestScope2(UUID uUID, boolean isInScope) {
        super();
        this.uUID = uUID;
        this.isInScope = isInScope;
    }

    public TestScope2() {
        super();
    }

    public UUID getuUID() {
        return uUID;
    }

    public void setuUID(UUID uUID) {
        this.uUID = uUID;
    }

   
    
    
   
}
