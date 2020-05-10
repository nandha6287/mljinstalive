package jp.co.metlife.api.instalive.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import jp.co.metlife.api.instalive.model.WebExDataModel;
import jp.co.metlife.api.instalive.model.WebExRestModel;
import jp.co.metlife.api.instalive.repo.WebExRepo;
import jp.co.metlife.api.instalive.service.WebExService;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
public class Operations {

	@Autowired
    WebExService webexService;
	private final String instaLiveBasePath = "https://instalive.metlife.co.jp/";
	
	private Log log = LogFactory.getLog(Operations.class);
	
	public WebExDataModel createWebExRequest(WebExRestModel webex)
	{
        String randomUUIDString = getAlphaNumericString(7);
        log.info("getAgentCode:"+webex.getAgentCode());
        log.info("getAgentName:"+webex.getAgentName());
        log.info("getGuestName:"+webex.getGuestName());
        log.info("getWebexHost:"+webex.getWebexHost());
        String guestUrl = instaLiveBasePath+randomUUIDString;
        WebExDataModel webexDb = new WebExDataModel(webex.getGuestName(), webex.getAgentName(), webex.getAgentCode(), webex.getWebexHost(),guestUrl, randomUUIDString);
        log.info("guestUrl:"+guestUrl);
		return webexDb;
	}
	public Map<String, String> createWebExResponse(boolean success, WebExDataModel webex)
	{
		Map<String, String> response = new HashMap<>();
		if (success)
		{
			response.put("requestStatus", "success");
			response.put("meetingUUID", webex.getMeetingUUID());
			response.put("customerMessage", "メットライフ生命の"+webex.getAgentName()+"とビデオ会議を開始したいなら、以下のリンクをクリックお願い申し上げます。 "+webex.getGuestUrl());
		}
		else
		{
			response.put("requestStatus", "failed");
		}
		return response;
		
	}
	private String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
	
}
