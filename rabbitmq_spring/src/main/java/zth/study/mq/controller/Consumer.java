package zth.study.mq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zth.study.mq.Producer;

public class Consumer {
	@Autowired
	private Producer producer;
	@Value("#{appConfig['mq.queue']}")
	private String queueId;

	/**
	 * @Description: 消息队列
	 * @Author:
	 * @CreateTime:
	 */
	@ResponseBody
	@RequestMapping("/sendQueue")
	public String testQueue() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", "hello rabbitmq");
			// 注意：第二个属性是 Queue 与 交换机绑定的路由
			producer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "发送完毕";
	}
}
