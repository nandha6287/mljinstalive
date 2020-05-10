package jp.co.metlife.api.instalive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.metlife.api.instalive.model.WebExDataModel;
import jp.co.metlife.api.instalive.repo.WebExRepo;

@Service
public class WebExService {

	@Autowired
    WebExRepo repository;
 
    // Save WebEx entity in the h2 database.
    public void save(WebExDataModel webex) {
        repository.save(webex);
    }
    
    public WebExDataModel findByMeetingUUID(String meetingUUID)
    {
		return repository.findByMeetingUUID(meetingUUID);
    	
    }
}
	