package com.crims.apps.dao.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledgeannex;
import org.springframework.stereotype.Component;

@Component
public interface ConfKnowledgeannexDao {
    void saveConfKnowledgeannex(ConfKnowledgeannex confKnowledgeannex);

    String findDocumentpathByIndexno(String indexno);
}
