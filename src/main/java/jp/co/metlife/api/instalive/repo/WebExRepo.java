package jp.co.metlife.api.instalive.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.metlife.api.instalive.model.WebExDataModel;

@Repository
public interface WebExRepo extends CrudRepository<WebExDataModel, Integer>{

	List<WebExDataModel> findByAgentCode(String agentCode);

	WebExDataModel findById(long id);
	
	WebExDataModel findByMeetingUUID(String UUUID);
}
