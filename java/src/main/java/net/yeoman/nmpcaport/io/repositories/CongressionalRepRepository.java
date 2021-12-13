package net.yeoman.nmpcaport.io.repositories;

import net.yeoman.nmpcaport.entities.CongressionalRepEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongressionalRepRepository extends CrudRepository<CongressionalRepEntity, Long> {

    CongressionalRepEntity findByCongressionalRepId(String repId);
    Boolean existsByCongressionalRepId(String repId);

}
