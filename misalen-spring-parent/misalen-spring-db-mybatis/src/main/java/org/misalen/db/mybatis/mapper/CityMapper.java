package org.misalen.db.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.misalen.db.mybatis.sys.domain.City;

public interface CityMapper {
	@Select("select * from city where id = #{id}")
	City findCityById(@Param("id") String id);
}
