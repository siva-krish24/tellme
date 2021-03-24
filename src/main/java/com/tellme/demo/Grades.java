package com.tellme.demo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Document(collection = "grades")
public class Grades {
    private static final long serialVersionUID = 1L;
    private  String MODEL;
    private  String VARIANT;
    private  String ENGINE;
    private  String CUSTOMERNAME;
    private  String ADDRESS1;
    private  String ADDRESS2;
    private  String ADDRESS3;
    private  String CITY;
    private  String STATE;
    private  String CONTACT;
    private  String HYPO;

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getVARIANT() {
        return VARIANT;
    }
    public void setVARIANT(String VARIANT) {
        this.VARIANT = VARIANT;
    }

    public String getENGINE() {
        return ENGINE;
    }
    public void setENGINE(String ENGINE) {
        this.ENGINE = ENGINE;
    }

    public String getCUSTOMERNAME() {
        return CUSTOMERNAME;
    }
    public void setCUSTOMERNAME(String CUSTOMERNAME) {
        this.CUSTOMERNAME = CUSTOMERNAME;
    }

    public String getADDRESS1() {
        return ADDRESS1;
    }

    public void setADDRESS1(String ADDRESS1) {
        this.ADDRESS1 = ADDRESS1;
    }

    public String getADDRESS2() {
        return ADDRESS2;
    }
    public void setADDRESS2(String ADDRESS2) {
        this.ADDRESS2 = ADDRESS2;
    }
    public String getADDRESS3() {
        return ADDRESS3;
    }
    public void setADDRESS3(String ADDRESS3) {
        this.ADDRESS3 = ADDRESS3;
    }

    public String getCITY() {
        return CITY;
    }
    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getSTATE() {
        return STATE;
    }
    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getCONTACT() {
        return CONTACT;
    }
    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getHYPO() {
        return HYPO;
    }
    public void setHYPO(String HYPO) {
        this.HYPO = HYPO;
    }

    public Grades(){}

    public Grades(String MODEL, String VARIANT, String ENGINE, String CUSTOMERNAME, String ADDRESS1, String ADDRESS2,
                String ADDRESS3, String CITY, String STATE, String CONTACT, String HYPO){
        this.MODEL = MODEL;
        this.VARIANT  = VARIANT;
        this.ENGINE = ENGINE;
        this.CUSTOMERNAME = CUSTOMERNAME;
        this.ADDRESS1 = ADDRESS1;
        this.ADDRESS2 = ADDRESS2;
        this.ADDRESS3 = ADDRESS3;
        this.CITY = CITY;
        this.STATE = STATE;
        this.CONTACT = CONTACT;
        this.HYPO = HYPO;

    }

}