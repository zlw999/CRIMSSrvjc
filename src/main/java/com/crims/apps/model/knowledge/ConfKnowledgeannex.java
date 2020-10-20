package com.crims.apps.model.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfKnowledgeannex implements Serializable{
    private static final long serialVersionUID = 1L;
    private String indexno;
    private Integer documenttype;
    private String documentpath;
    private Integer annexno;


    private String documentname;


}
