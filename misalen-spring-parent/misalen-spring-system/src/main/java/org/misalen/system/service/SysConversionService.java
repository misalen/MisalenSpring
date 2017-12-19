package org.misalen.system.service;

import java.util.Date;

import org.misalen.common.utils.HanyuPinyinHelper;
import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.system.domain.SysConversion;
import org.misalen.system.repository.SysConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author DO·VIS
 *
 */
@Service
public class SysConversionService extends CustomService<SysConversion, String> {
	@Autowired
	public SysConversionRepository sysConversionRepository;

	public SysConversion findByChinese(String chinese) {
		SysConversion conversion = sysConversionRepository.findByChinese(chinese);
		if (conversion == null) {
			String pinyin = HanyuPinyinHelper.toHanyuPinyin(chinese);
			Long countByPnyin = sysConversionRepository.countByPinyinLike(pinyin + "%");
			conversion = new SysConversion();
			conversion.setAddTime(new Date());
			conversion.setChinese(chinese);
			conversion.setPinyin(pinyin + (countByPnyin == 0 ? "" : countByPnyin));
			sysConversionRepository.save(conversion);
		}
		return conversion;
	}
}
