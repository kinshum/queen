package com.queen.core.tool.convert;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.StringValueResolver;

/**
 * 类型 转换 服务，添加了 IEnum 转换
 *
 * @author jensen
 */
public class QueenConversionService extends ApplicationConversionService {
	@Nullable
	private static volatile QueenConversionService SHARED_INSTANCE;

	public QueenConversionService() {
		this(null);
	}

	public QueenConversionService(@Nullable StringValueResolver embeddedValueResolver) {
		super(embeddedValueResolver);
		super.addConverter(new EnumToStringConverter());
		super.addConverter(new StringToEnumConverter());
	}


	/**
	 *
	 * @return
	 */
	public static GenericConversionService getInstance() {
		QueenConversionService sharedInstance = QueenConversionService.SHARED_INSTANCE;
		if (sharedInstance == null) {
			synchronized (QueenConversionService.class) {
				sharedInstance = QueenConversionService.SHARED_INSTANCE;
				if (sharedInstance == null) {
					sharedInstance = new QueenConversionService();
					QueenConversionService.SHARED_INSTANCE = sharedInstance;
				}
			}
		}
		return sharedInstance;
	}

}
