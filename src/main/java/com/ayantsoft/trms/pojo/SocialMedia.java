package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class SocialMedia implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7344574536209710168L;
	
	private String alternateEmail;
    private String email;
    private String facebook;
    private String fax;
    private String linkedin;
    private String msn;
    private String skype;
    private String twitter;
    private String personalWebSite;
    
    
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getPersonalWebSite() {
		return personalWebSite;
	}
	public void setPersonalWebSite(String personalWebSite) {
		this.personalWebSite = personalWebSite;
	}
}
