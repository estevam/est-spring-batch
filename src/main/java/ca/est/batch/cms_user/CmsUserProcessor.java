package ca.est.batch.cms_user;

import org.springframework.batch.item.ItemProcessor;

import ca.est.entity.CmsUser;

public class CmsUserProcessor implements ItemProcessor<CmsUser, CmsUser> {

	@Override
	public CmsUser process(CmsUser data) throws Exception {
		
		//mudar obj
		return data;
	}

}