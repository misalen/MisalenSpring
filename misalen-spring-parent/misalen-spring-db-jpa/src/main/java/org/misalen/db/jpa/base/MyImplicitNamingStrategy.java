package org.misalen.db.jpa.base;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class MyImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		return super.toIdentifier(addUnderscores(stringForm), buildingContext);
	}

	public static String addUnderscores(String name) {
		StringBuilder buf = new StringBuilder(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toUpperCase(Locale.ROOT);
	}
}
