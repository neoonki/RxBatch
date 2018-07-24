package neoonki.task.btc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import neoonki.task.Task;
import neoonki.util.RestAPI;

@SuppressWarnings("serial")
@Service
public class GetBTCInfoTask extends Task implements Serializable {
	private Logger log = Logger.getLogger(this.getClass());
	private String apiURL = "https://api.korbit.co.kr/v1/ticker/detailed";
	private HttpMethod httpMethod = HttpMethod.GET;
	@Autowired
	private RestAPI restAPI;
	
	private String[] currencyList = {
			"btc_krw"
			, "etc_krw"
			, "eth_krw"
			, "xrp_krw"
			, "bch_krw"
			, "ltc_krw"
	};
	
	public GetBTCInfoTask() {
		log.info("init thread");
		setInterval(5000L);
	}
	
	@Override
	@Async("threadPoolTaskExecutor")
	public Future<String> run(String message) throws Exception {
		log.info("run - start");
		Map param = new HashMap();
		param.put("currency_pair", currencyList[0]);
		Map result = restAPI.get(apiURL, httpMethod, param);
		log.info(result.toString());
		log.info("run - end");
		return null;
	}

}
