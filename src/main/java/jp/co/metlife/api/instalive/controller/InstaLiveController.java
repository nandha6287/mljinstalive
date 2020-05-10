package jp.co.metlife.api.instalive.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.metlife.api.instalive.model.WebExDataModel;
import jp.co.metlife.api.instalive.model.WebExRestModel;
import jp.co.metlife.api.instalive.model.esign;
import jp.co.metlife.api.instalive.service.WebExService;

@RestController
public class InstaLiveController {
	
	@Autowired
    WebExService webexService;
	private Log log = LogFactory.getLog(InstaLiveController.class);
	@Value("${instalive.base.url}")
	private String instaLiveBasePath;
	
	@PostMapping("/esign")
	public Map<String, Boolean> createSignRequest(@RequestBody esign newSignReq)
	{
		log.info("esign requested for:"+newSignReq.getSignerName());
		//boolean submitSignReq = submitSignRequest(newSignReq);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("esign-requested", true);
	    return response;		
	}
	@GetMapping("/webex/{UUID}")
	public Map<String, String> getWebEx(@PathVariable String UUID)
	{
		log.info("getWebExInfo initialized for:"+UUID);
		Map<String, String> response = new HashMap<String, String>();
		try {
			WebExDataModel webex = webexService.findByMeetingUUID(UUID);
			if (webex==null)
			{
				response.put("result", "nodatafound");
			}
			else
			{
				response.put("guestName", webex.getGuestName());
				response.put("agentName", webex.getAgentName());
				response.put("agentCode", webex.getAgentCode());
				response.put("webexHost", webex.getWebexHost());
				response.put("result", "success");
			}
			//log.info("guestName:"+webex.getGuestName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		
	}
	@PostMapping("/webex")
	public Map<String, String> createWebEx(@RequestBody WebExRestModel webex)
	{
		Operations ops = new Operations();
		log.info("create webex request received:"+webex.getAgentName());
        WebExDataModel webexDb = ops.createWebExRequest(webex);
        Map<String, String> response = null;
        try {
			webexService.save(webexDb);
			response = ops.createWebExResponse(true, webexDb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = ops.createWebExResponse(false, webexDb);
		}
        
	    return response;		
	}

}
