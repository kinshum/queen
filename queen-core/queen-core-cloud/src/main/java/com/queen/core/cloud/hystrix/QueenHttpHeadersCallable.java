package com.queen.core.cloud.hystrix;

import com.queen.core.cloud.props.QueenHystrixHeadersProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * HttpHeaders hystrix Callable
 *
 * @param <V> 泛型标记
 * @author jensen
 */
public class QueenHttpHeadersCallable<V> implements Callable<V> {
	private final Callable<V> delegate;
	@Nullable
	private HttpHeaders httpHeaders;

	public QueenHttpHeadersCallable(Callable<V> delegate,
									@Nullable QueenHystrixAccountGetter accountGetter,
									QueenHystrixHeadersProperties properties) {
		this.delegate = delegate;
		this.httpHeaders = QueenHttpHeadersContextHolder.toHeaders(accountGetter, properties);
	}

	@Override
	public V call() throws Exception {
		if (httpHeaders == null) {
			return delegate.call();
		}
		try {
			QueenHttpHeadersContextHolder.set(httpHeaders);
			return delegate.call();
		} finally {
			QueenHttpHeadersContextHolder.remove();
			httpHeaders.clear();
			httpHeaders = null;
		}
	}
}
