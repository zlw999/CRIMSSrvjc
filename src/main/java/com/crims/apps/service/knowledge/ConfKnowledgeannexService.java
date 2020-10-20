package com.crims.apps.service.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledgeannex;

public interface ConfKnowledgeannexService {
    void saveConfKnowledgeannex(ConfKnowledgeannex confKnowledgeannex);

    String findDocumentpathByIndexno(String indexno);
}
