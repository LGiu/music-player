package br.com.global.service;

import br.com.global.Util.Return;
import br.com.global.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public class ServiceGeneric<U extends Model> {

    private final Class aClass;

    public ServiceGeneric( Class aClass) {
        this.aClass = aClass;
    }

    public Return save(MongoRepository<U, UUID> mongoRepository, U u) {
        u.setId(UUID.randomUUID().toString());
        mongoRepository.save(u);
        return new Return(u);
    }

    public List<U> getAll(MongoRepository<U, UUID> mongoRepository){
        return mongoRepository.findAll();
    }

}
