package ca.est.batch.cms_user;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import ca.est.entity.CmsUser;

public class CmsUserWriter implements ItemWriter<CmsUser> {
	private static final Logger log = LogManager.getLogger(CmsUserWriter.class);
	@Override
	public void write(List<? extends CmsUser> cmsUser) throws Exception {
		log.info(cmsUser.toString());
	}

}