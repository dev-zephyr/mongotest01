package com.zephyr.mongotest01.mongo.domain.s_service;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "s_service")
public class CommonEntity {

    @Id
    private ObjectId id;

    private String name;
    private Date createdAt;


    private long CF_request;
    private long CF_BytesDownloaded;

    @Override
    public boolean equals(Object obj) {     // query 결과에서 받아온 결과와 dIdx를 비교하기 위해 오버라이딩
        if(obj instanceof CommonEntity) {
            CommonEntity input = (CommonEntity) obj;

            if(this.name.equals(input.getName())) {
                return true;
            }
        }
        return false;

    }


}
