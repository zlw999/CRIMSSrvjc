package com.crims.apps.service.knowledge;

import com.crims.apps.dao.knowledge.ConfKnowledgetagDao;
import com.crims.apps.model.knowledge.ConfKnowledgetag;
import com.crims.apps.service.knowledge.ConfKnowledgetagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfKnowledgetagServiceImpl implements ConfKnowledgetagService {
    @Autowired
    private ConfKnowledgetagDao confKnowledgetagDao;
    @Override
    public List<ConfKnowledgetag> findConfKnowledgetag() {
        return confKnowledgetagDao.findConfKnowledgetag();
    }

    @Override
    public void saveConfKnowledgetag(ConfKnowledgetag confKnowledgetag) {
        confKnowledgetagDao.saveConfKnowledgetag(confKnowledgetag);
    }
}
