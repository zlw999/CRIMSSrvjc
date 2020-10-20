package com.crims.apps.dao.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledgetag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfKnowledgetagDao {
    List<ConfKnowledgetag> findConfKnowledgetag();

    void saveConfKnowledgetag(ConfKnowledgetag confKnowledgetag);
}
