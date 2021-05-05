package com.zephyr.mongotest01.mongo.domain.s_service;

import lombok.*;

@Setter
@Getter
@ToString(callSuper=true)
public class JaguarEntity extends CommonEntity{

    private long J_request;
    private long J_traffic;
}
