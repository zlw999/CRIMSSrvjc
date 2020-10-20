package com.crims.apps.model.camerainfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfCamerainfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String deviceid;
    private String devicename;
    private String zname;
    private String zvalue;
}
