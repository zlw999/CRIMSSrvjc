package com.crims.apps.model.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfKnowledgetag implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String knowledgetag;
}
