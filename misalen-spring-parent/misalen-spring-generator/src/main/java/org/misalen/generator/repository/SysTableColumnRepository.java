package org.misalen.generator.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysTableColumn;
import org.springframework.stereotype.Repository;

@Repository
public interface SysTableColumnRepository extends BaseRepository<SysTableColumn, String> {

	List<SysTableColumn> findByTableId(String primaryKey);

}