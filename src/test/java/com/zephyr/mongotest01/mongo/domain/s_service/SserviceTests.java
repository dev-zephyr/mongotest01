package com.zephyr.mongotest01.mongo.domain.s_service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.internal.operation.MixedBulkWriteOperation;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SserviceTests {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void findQueryMethod() {

        Date date = new Date(1620183600000L);
        String[] names = {"D2000", "D3000", "D4000"};

        Criteria criteria = new Criteria();

        /*
        *   find({
        *       createdAt : "$date",
        *       name : {$in : [$names]}
        *   }
        * */
        criteria.and("createdAt").is(date);
        criteria.and("name").in(Arrays.asList(names));

        Query query = new Query();
        query.addCriteria(criteria);

        List<CommonEntity> list = mongoTemplate.find(query, CommonEntity.class);    // find 쿼리 결과 받아옴

        for(CommonEntity entity : list) {

            entity.setCF_request(123L);              // 받아온 결과에서 CF값 대입
            entity.setCF_BytesDownloaded(123L);

        }

        for (CommonEntity entity : list) {          // 확인
            System.out.println(entity);
        }

        for(String name : names) {       // find에 없는 dIdx는 새로 insert하기 위해 객체 생성
            CommonEntity e = CommonEntity.builder().name(name).build();
            if(!list.contains(e)) {
                CommonEntity newEntity = CommonEntity.builder()
                        .id(new ObjectId())     // 밑에 upsert 부분에서 _id를 제외했기때문에 자동으로 생성되지 않고 null처리 되므로 직접 생성해줘야한다.
                        .name(name)
                        .createdAt(date)
                        .CF_BytesDownloaded(1000)
                        .CF_request(1000)
                        .build();
                list.add(newEntity);
            }
        }



        BulkOperations bulkOper = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, CommonEntity.class); // 벌크 준비

        for(CommonEntity entity : list) {

            Document doc = new Document();
            mongoTemplate.getConverter().write(entity, doc);    // 엔티티 클래스를 BSON으로 변환
            Query query2 = new Query(Criteria.where("_id").is(entity.getId()));     // DB에 저장된 unique key값(id)로 수정할 타겟 찾음

            Document updateDoc = new Document();
            updateDoc.append("$set", doc);      // update하기 위한 $set 쿼리 설정

            Update update = Update.fromDocument(updateDoc, "_id");      // _id 필드는 수정하면 안되기 때문에 제외
            bulkOper.upsert(query2, update);    // upsert!

        }

        bulkOper.execute();

    }


//    @Test
    public void saveDummies() {

        List<CommonEntity> list = new ArrayList<>();

        JaguarEntity jaguar = new JaguarEntity();
        jaguar.setJ_request(1000L);
        jaguar.setJ_traffic(1000L);

        CommonEntity common = (CommonEntity) jaguar;

        common.setName("D2000");
        common.setCreatedAt(new Date());

        list.add(common);


        StonEntity ston = new StonEntity();
        ston.setNV_request(1000L);
        ston.setNV_traffic(1000L);

        common = (CommonEntity) ston;

        common.setName("D3000");
        common.setCreatedAt(new Date());

        list.add(common);

//        repository.saveAll(list);

    }

}
