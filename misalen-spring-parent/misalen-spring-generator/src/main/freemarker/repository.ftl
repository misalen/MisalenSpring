package ${packageName}.${type};

import org.misalen.db.jpa.base.repository.BaseRepository;
import ${packageName}.domain.${name.upperCaseFirstName};
import org.springframework.stereotype.Repository;
/**
 * 
 * @author DO.VIS
 *
 */
@Repository
public interface ${name.upperCaseFirstName}Repository extends BaseRepository<${name.upperCaseFirstName}, String> {
	
	
}