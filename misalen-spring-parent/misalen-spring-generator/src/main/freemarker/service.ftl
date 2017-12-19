package ${packageName}.${type};

import org.misalen.db.jpa.base.service.CustomService;
import ${packageName}.domain.${name.upperCaseFirstName};
import ${packageName}.repository.${name.upperCaseFirstName}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author DO.VIS
 *
 */
@Service
public class ${name.upperCaseFirstName}Service extends CustomService<${name.upperCaseFirstName}, String> {
	@Autowired
	public ${name.upperCaseFirstName}Repository ${name.lowerCaseFirstName}Repository;
	
}
