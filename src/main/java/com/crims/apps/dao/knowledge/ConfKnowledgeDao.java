package com.crims.apps.dao.knowledge;

import com.crims.apps.model.knowledge.ConfKnowledge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfKnowledgeDao {
    List<ConfKnowledge> findAll(ConfKnowledge confKnowledge);

    void saveConfKnowledge(ConfKnowledge confKnowledge);

    void deleteConfKnowledge(ConfKnowledge confKnowledge);

    ConfKnowledge findConfKnowledgeByIndexno(ConfKnowledge confKnowledge);

    void updateConfKnowledge(ConfKnowledge confKnowledge);

    void shenheConfKnowledge(ConfKnowledge confKnowledge);
}
