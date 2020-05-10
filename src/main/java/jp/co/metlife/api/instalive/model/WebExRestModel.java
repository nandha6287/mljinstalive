package jp.co.metlife.api.instalive.model;

public class WebExRestModel {

	private String guestName;
    private String agentName;
    private String agentCode;
    private String webexHost;
    private String webexJWT;
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getWebexHost() {
		return webexHost;
	}
	public void setWebexHost(String webexHost) {
		this.webexHost = webexHost;
	}
	public String getWebexJWT() {
		return webexJWT;
	}
	public void setWebexJWT(String webexJWT) {
		this.webexJWT = webexJWT;
	}
}
