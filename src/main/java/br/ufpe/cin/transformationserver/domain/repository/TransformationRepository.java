package br.ufpe.cin.transformationserver.domain.repository;

import org.springframework.data.repository.CrudRepository;

import br.ufpe.cin.transformationserver.domain.Transformation;

public interface TransformationRepository extends CrudRepository<Transformation, Long>{

}
