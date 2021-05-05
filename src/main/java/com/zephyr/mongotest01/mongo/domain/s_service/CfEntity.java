package com.zephyr.mongotest01.mongo.domain.s_service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
public class CfEntity{

    private long CF_request;
    private long CF_BytesDownloaded;
}
