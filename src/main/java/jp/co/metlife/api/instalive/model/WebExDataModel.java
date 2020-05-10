package jp.co.metlife.api.instalive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEBEX")
public class WebExDataModel {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
	@Column(name="GUEST_NAME")
	private String guestName;
	
	@Column(name="AGENT_NAME")
    private String agentName;
	
	@Column(name="AGENT_CODE")
    private String agentCode;
	
	@Column(name="WEBEX_HOST")
    private String webexHost;
	
	@Column(name="WEBEX_JWT")
    private String webexJWT;

	@Column(name="GUEST_URL")
    private String guestUrl;
	
	@Column(name="MEETING_UUID")
    private String meetingUUID;
    

	// Default constructor.
    public WebExDataModel() {  }
 
    // Parameterized constructor.
    public WebExDataModel(String guestName, String agentName,String agentCode,String webexHost, String webexJWT, String guestUrl, String meetingUUID) {
    	this.guestName = guestName;
        this.agentName = agentName;
        this.agentCode = agentCode;
        this.webexHost = webexHost;
        this.webexJWT = webexJWT;
        this.guestUrl = guestUrl;
        this.meetingUUID = meetingUUID;
    }

    public int getId() {
		return id;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getWebexHost() {
		return webexHost;
	}
	public void setWebexHost(String webexHost) {
		this.webexHost = webexHost;
	}
	public String getGuestUrl() {
		return guestUrl;
	}
	public void setGuestUrl(String guestUrl) {
		this.guestUrl = guestUrl;
	}
	 public String getMeetingUUID() {
			return meetingUUID;
	}

	public void setMeetingUUID(String meetingUUID) {
		this.meetingUUID = meetingUUID;
	}
	
	public String getWebexJWT() {
		return webexJWT;
	}

	public void setWebexJWT(String webexJWT) {
		this.webexJWT = webexJWT;
	}

}
