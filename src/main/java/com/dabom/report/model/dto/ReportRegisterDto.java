package com.dabom.report.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRegisterDto<T> {
    private String reason;
    private Integer memberId;
    private T targetId;
    private String targetType;

}
