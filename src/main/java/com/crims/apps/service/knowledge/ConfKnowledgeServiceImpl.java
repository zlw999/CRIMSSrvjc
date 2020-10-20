package com.crims.apps.service.knowledge;

import com.crims.apps.dao.knowledge.ConfKnowledgeDao;
import com.crims.apps.model.knowledge.ConfKnowledge;
import com.crims.apps.service.knowledge.ConfKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfKnowledgeServiceImpl implements ConfKnowledgeService {
    @Autowired
    private ConfKnowledgeDao confKnowledgeDao;
    @Override
    public List<ConfKnowledge> findAll(ConfKnowledge confKnowledge) {
        return confKnowledgeDao.findAll(confKnowledge);
    }

    @Override
    public void saveConfKnowledge(ConfKnowledge confKnowledge) {
         confKnowledgeDao.saveConfKnowledge(confKnowledge);
    }

    @Override
    public void deleteConfKnowledge(ConfKnowledge confKnowledge) {
        confKnowledgeDao.deleteConfKnowledge(confKnowledge);
    }

    @Override
    public ConfKnowledge findConfKnowledgeByIndexno(ConfKnowledge confKnowledge) {
        return confKnowledgeDao.findConfKnowledgeByIndexno(confKnowledge);
    }

    @Override
    public void updateConfKnowledge(ConfKnowledge confKnowledge) {
        confKnowledgeDao.updateConfKnowledge(confKnowledge);
    }

    @Override
    public void shenheConfKnowledge(ConfKnowledge confKnowledge) {
        confKnowledgeDao.shenheConfKnowledge(confKnowledge);
    }
}
