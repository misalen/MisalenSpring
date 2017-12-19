package org.misalen.generator.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysFormField;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFormFieldRepository extends BaseRepository<SysFormField, String> {

	void deleteByFormId(String primaryKey);

	List<SysFormField> findByFormId(String primaryKey);

}