package com.crims.apps.service.knowledge;

import com.crims.apps.dao.knowledge.ConfKnowledgeannexDao;
import com.crims.apps.model.knowledge.ConfKnowledgeannex;
import com.crims.apps.service.knowledge.ConfKnowledgeannexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfKnowledgeannexServiceImpl implements ConfKnowledgeannexService {
    @Autowired
    private ConfKnowledgeannexDao confKnowledgeannexDao;

    @Override
    public void saveConfKnowledgeannex(ConfKnowledgeannex confKnowledgeannex) {
        confKnowledgeannexDao.saveConfKnowledgeannex(confKnowledgeannex);
    }

    @Override
    public String findDocumentpathByIndexno(String indexno) {
        return confKnowledgeannexDao.findDocumentpathByIndexno(indexno);
    }
}
