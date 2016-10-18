package com.lunjar.ebp.admin.domain.dto;

/**
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/5/20 14:59
 */
public class PartnerDto {
    private String account;
    private String partnerName;
    private String idCard;
    private String headsPhotoUrl;
    private String tailsPhotoUrl;

    @Override
    public String toString() {
        return "PartnerDto{" +
                "account='" + account + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", headsPhotoUrl='" + headsPhotoUrl + '\'' +
                ", tailsPhotoUrl='" + tailsPhotoUrl + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHeadsPhotoUrl() {
        return headsPhotoUrl;
    }

    public void setHeadsPhotoUrl(String headsPhotoUrl) {
        this.headsPhotoUrl = headsPhotoUrl;
    }

    public String getTailsPhotoUrl() {
        return tailsPhotoUrl;
    }

    public void setTailsPhotoUrl(String tailsPhotoUrl) {
        this.tailsPhotoUrl = tailsPhotoUrl;
    }
}
