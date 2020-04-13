package com.queen.core.tool.support;

import com.queen.core.tool.utils.Exceptions;

import java.util.Objects;
import java.util.function.Function;

/**
 * 当 Lambda 遇上受检异常
 *
 * @author jensen
 */
public class Try {

	public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.apply(t);
			} catch (Exception e) {
				throw Exceptions.unchecked(e);
			}
		};
	}

	@FunctionalInterface
	public interface UncheckedFunction<T, R> {
		/**
		 * 调用
		 * @param t t
		 * @return R
		 * @throws Exception Exception
		 */
		R apply(T t) throws Exception;
	}
}
