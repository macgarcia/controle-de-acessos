package com.github.macgarcia.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.github.macgarcia.api.model.Nota;

public interface NotaRepository extends JpaRepositoryImplementation<Nota, Long>{

	@Query("select n from Nota n left join fetch n.historico where n.idIntegracao = :idIntegracao")
	Nota findByIntegracao(@Param("idIntegracao") final Long idIntegracao);
}
