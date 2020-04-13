package java.com.queen.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * StorageClient
 *
 * @author jensen
 */
@FeignClient(name = "queen-seata-storage", fallback = StorageClientFallback.class)
public interface IStorageClient {

	/**
	 * 减库存
	 *
	 * @param commodityCode 商品代码
	 * @param count         数量
	 * @return boolean
	 */
	@GetMapping("/storage/deduct")
	int deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);

}
