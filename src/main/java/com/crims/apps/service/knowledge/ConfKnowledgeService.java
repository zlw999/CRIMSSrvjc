package com.crims.apps.service.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledge;

import java.util.List;

public interface ConfKnowledgeService {
    List<ConfKnowledge> findAll(ConfKnowledge confKnowledge);

    void saveConfKnowledge(ConfKnowledge confKnowledge);

    void deleteConfKnowledge(ConfKnowledge confKnowledge);

    ConfKnowledge findConfKnowledgeByIndexno(ConfKnowledge confKnowledge);

    void updateConfKnowledge(ConfKnowledge confKnowledge);

    void shenheConfKnowledge(ConfKnowledge confKnowledge);
}
