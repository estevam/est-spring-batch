package ca.est.batch.cms_user;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.est.entity.CmsUser;
import ca.est.repository.CmsUserRepository;
@Component
public class CmsUserReader implements ItemReader<CmsUser> {

	@Autowired
	private CmsUserRepository cmsUserRepository;
	
	private int count = 0;
    List<CmsUser>listCmsUser;
	public CmsUserReader(){
	}
	
	@Override
	public CmsUser read() throws Exception, UnexpectedInputException,
	ParseException, NonTransientResourceException {
   
		 if(count == 0) {
			 listCmsUser =cmsUserRepository.findAll();
		 }
		if (count < listCmsUser.size()) {
			return listCmsUser.get(count++);
		} else {
			count = 0;
			return null;
		}
	}

}