package com.crims.apps.service.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledgetag;

import java.util.List;

public interface ConfKnowledgetagService {
    List<ConfKnowledgetag> findConfKnowledgetag();

    void saveConfKnowledgetag(ConfKnowledgetag confKnowledgetag);
}
