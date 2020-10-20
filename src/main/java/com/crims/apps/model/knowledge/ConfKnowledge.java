package com.crims.apps.model.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfKnowledge implements Serializable {
    private static final long serialVersionUID = 1L;
    private String indexno;
    private String title;
    private String devicetype;
    private String faulttype;
    private String faultdsp;
    private String faultreason;
    private String solution;
    private Integer hasDoc;
    private String createuserid;
    private String createuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private String reviewuserid;
    private String reviewuser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewtime;
    private Integer reviewstatus;
    private String reviewdsp;
    private Integer validstatus;
    private String label;
    private Integer usenum;

    private String documentpath;
    private String device;
    private String devicename;
    private String devicechildname;
    private String item;
    private String itemChild;
    private String documentname;
}
