package com.crims.apps.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfUserOnline implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private String username;
    private Integer loginstate;
    private Integer requestvideocount;
    private String onlinestatu;
    private String ip;
    private Date lastmodifytime;
}
