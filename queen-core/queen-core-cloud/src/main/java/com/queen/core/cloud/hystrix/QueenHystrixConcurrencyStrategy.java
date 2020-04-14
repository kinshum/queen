package com.queen.core.cloud.hystrix;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import lombok.AllArgsConstructor;
import com.queen.core.cloud.props.QueenHystrixHeadersProperties;
import org.springframework.lang.Nullable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hystrix传递ThreaLocal中的一些变量
 *
 * @author jensen
 */
@AllArgsConstructor
public class QueenHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	@Nullable
	private final HystrixConcurrencyStrategy existingConcurrencyStrategy;
	@Nullable
	private final QueenHystrixAccountGetter accountGetter;
	private final QueenHystrixHeadersProperties properties;

	@Override
	public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
		return existingConcurrencyStrategy != null
			? existingConcurrencyStrategy.getBlockingQueue(maxQueueSize)
			: super.getBlockingQueue(maxQueueSize);
	}

	@Override
	public <T> HystrixRequestVariable<T> getRequestVariable(
		HystrixRequestVariableLifecycle<T> rv) {
		return existingConcurrencyStrategy != null
			? existingConcurrencyStrategy.getRequestVariable(rv)
			: super.getRequestVariable(rv);
	}

	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
											HystrixProperty<Integer> corePoolSize,
											HystrixProperty<Integer> maximumPoolSize,
											HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
											BlockingQueue<Runnable> workQueue) {
		return existingConcurrencyStrategy != null
			? existingConcurrencyStrategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
			: super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
		return existingConcurrencyStrategy != null
			? existingConcurrencyStrategy.getThreadPool(threadPoolKey, threadPoolProperties)
			: super.getThreadPool(threadPoolKey, threadPoolProperties);
	}

	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		Callable<T> wrapCallable = new QueenHttpHeadersCallable<>(callable, accountGetter, properties);
		return existingConcurrencyStrategy != null
			? existingConcurrencyStrategy.wrapCallable(wrapCallable)
			: super.wrapCallable(wrapCallable);
	}
}