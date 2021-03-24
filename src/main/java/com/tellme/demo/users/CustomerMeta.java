package com.tellme.demo.users;

import java.util.HashMap;
import java.util.Map;



public class CustomerMeta {
 private String id;
private String mODEL;
private String vARIANT;
private String eNGINCHAS;
private String cUSTOMERNAME;
private String aDDRESS1;
private String aDDRESS2;
private String aDDRESS3;
private String cITY;
private String sTATE;
private String cONTACT;
private String hYPO;
    public CustomerMeta(String mODEL, String vARIANT, String eNGINCHAS, String cUSTOMERNAME, String aDDRESS1, String aDDRESS2, String aDDRESS3, String cITY, String sTATE, String cONTACT, String hYPO) {

        this.mODEL = mODEL;
        this.vARIANT = vARIANT;
        this.eNGINCHAS = eNGINCHAS;
        this.cUSTOMERNAME = cUSTOMERNAME;
        this.aDDRESS1 = aDDRESS1;
        this.aDDRESS2 = aDDRESS2;
        this.aDDRESS3 = aDDRESS3;
        this.cITY = cITY;
        this.sTATE = sTATE;
        this.cONTACT = cONTACT;
        this.hYPO = hYPO;
    }

public CustomerMeta(String id, String mODEL, String vARIANT, String eNGINCHAS, String cUSTOMERNAME, String aDDRESS1, String aDDRESS2,
                    String aDDRESS3, String cITY, String sTATE, String cONTACT, String hYPO) {
        this.id = id;
    this.mODEL = mODEL;
    this.vARIANT = vARIANT;
    this.eNGINCHAS = eNGINCHAS;
    this.cUSTOMERNAME = cUSTOMERNAME;
    this.aDDRESS1 = aDDRESS1;
    this.aDDRESS2 = aDDRESS2;
    this.aDDRESS3 = aDDRESS3;
    this.cITY = cITY;
    this.sTATE = sTATE;
    this.cONTACT = cONTACT;
    this.hYPO = hYPO;

    }

    public CustomerMeta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMODEL() {
return mODEL;
}

public void setMODEL(String mODEL) {
this.mODEL = mODEL;
}

public String getVARIANT() {
return vARIANT;
}

public void setVARIANT(String vARIANT) {
this.vARIANT = vARIANT;
}

public String getENGINCHAS() {
return eNGINCHAS;
}

public void setENGINCHAS(String eNGINCHAS) {
this.eNGINCHAS = eNGINCHAS;
}

public String getCUSTOMERNAME() {
return cUSTOMERNAME;
}

public void setCUSTOMERNAME(String cUSTOMERNAME) {
this.cUSTOMERNAME = cUSTOMERNAME;
}

public String getADDRESS1() {
return aDDRESS1;
}

public void setADDRESS1(String aDDRESS1) {
this.aDDRESS1 = aDDRESS1;
}

public String getADDRESS2() {
return aDDRESS2;
}

public void setADDRESS2(String aDDRESS2) {
this.aDDRESS2 = aDDRESS2;
}

public String getADDRESS3() {
return aDDRESS3;
}

public void setADDRESS3(String aDDRESS3) {
this.aDDRESS3 = aDDRESS3;
}

public String getCITY() {
return cITY;
}

public void setCITY(String cITY) {
this.cITY = cITY;
}

public String getSTATE() {
return sTATE;
}

public void setSTATE(String sTATE) {
this.sTATE = sTATE;
}

public String getCONTACT() {
return cONTACT;
}

public void setCONTACT(String cONTACT) {
this.cONTACT = cONTACT;
}

public String getHYPO() {
return hYPO;
}

public void setHYPO(String hYPO) {
this.hYPO = hYPO;
}
public void setDefaultValues(){
     if(this.id==null) this.id="NA";
    if(this.mODEL==null) this.mODEL= "NA";
    if(this.vARIANT==null) this.vARIANT = "NA";
    if(this.eNGINCHAS==null) this.eNGINCHAS= "NA";
    if(this.cUSTOMERNAME==null) this.cUSTOMERNAME="NA";
    if(this.aDDRESS1==null) this.aDDRESS1 = "NA";
    if(this.aDDRESS2==null) this.aDDRESS2 = "NA";
    if(this.aDDRESS3==null) this.aDDRESS3 = "NA";
    if(this.cITY==null) this.cITY = "NA";
    if(this.sTATE==null) this.sTATE = "NA";
    if(this.cONTACT==null) this.cONTACT = "NA";
    if(this.hYPO==null) this.hYPO = "NA";

}

}