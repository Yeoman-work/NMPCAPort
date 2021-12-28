package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongressionalRepRepository extends CrudRepository<CongressionalRepEntity, Long> {

    List<CongressionalRepEntity> findAll();
    CongressionalRepEntity findByCongressionalRepId(String repId);
    Boolean existsByCongressionalRepId(String repId);

}
