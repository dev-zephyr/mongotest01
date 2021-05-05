package com.zephyr.mongotest01.mongo.domain.s_service;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString(callSuper=true)
public class StonEntity extends CommonEntity{

    private long NV_request;
    private long NV_traffic;

}
