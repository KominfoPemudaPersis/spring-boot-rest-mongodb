package com.kominfo.halaqohit.springbootdatajpamongodb.repository;

import com.kominfo.halaqohit.springbootdatajpamongodb.entity.Pets;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-data-jpa-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-17
 * Time: 05:48
 * To change this template use File | Settings | File Templates.
 */
public interface PetsRepository extends MongoRepository<Pets, String> {
    Pets findBy_id(ObjectId _id);
}