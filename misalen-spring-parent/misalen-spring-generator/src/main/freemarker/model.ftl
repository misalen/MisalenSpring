package ${packageName}.${type};

import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.annotations.DataAccess;
import org.misalen.db.jpa.base.domain.BaseDomain;

/**
 * 
 * @author DO.VIS
 *
 */
@Entity
@Table
public class ${name.upperCaseFirstName} extends BaseDomain {
<#if sysFormInfo.sysFormFields??>
<#list sysFormInfo.sysFormFields as field>

	@ModelComment("${field.title}")
	@DataAccess(add = ${field.accessAdd?string('true', 'false')}, list = ${field.accessList?string('true', 'false')}, update = ${field.accessUpdate?string('true', 'false')}, search = ${field.accessSearch?string('true', 'false')})
	@Column(nullable = ${field.mandatory?string('false', 'true')})
	private ${field.javaType} ${field.named.lowerCaseFirstName};
	
</#list>
	
<#list sysFormInfo.sysFormFields as field>

	public ${field.javaType} get${field.named.upperCaseFirstName}() {
		return ${field.named.lowerCaseFirstName};
	}

	public void set${field.named.upperCaseFirstName}(${field.javaType} ${field.named.lowerCaseFirstName}) {
		this.${field.named.lowerCaseFirstName} = ${field.named.lowerCaseFirstName};
	}

</#list>
</#if>
}
