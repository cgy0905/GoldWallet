package com.enternityfintech.goldcard.business.possess.model;

import java.io.Serializable;

/**
 * Created by cgy
 * 2018/7/4  15:48
 */
public class PossessBean  implements Serializable{


    private int imageId;
    private String code;
    private String business;
    private String vault;

    public PossessBean(int imageId, String code, String business,String vault) {
        this.imageId = imageId;
        this.code = code;
        this.business = business;
        this.vault = vault;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getVault() {
        return vault;
    }

    public void setVault(String vault) {
        this.vault = vault;
    }
}
