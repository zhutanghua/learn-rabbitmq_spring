package zth.study.mq;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public class SendMsgTest {
	@Autowired
	private Producer producer;
	@Value("${mq.queue}")
	private String queueId; // mq_zth

	@Test
	public void sendMsg() throws Exception {
		while (true) {
			Thread.sleep(300);
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("data", "hello rabbitmq");
				// 注意：第二个属性是 Queue 与 交换机绑定的路由
				producer.sendQueue(queueId + "_exchange", queueId + "_key", map);
				// producer.sendQueue(exchange_key, queue_key, object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("发送完毕！");
		}
	}

	@Test
	public void receiveMsg() throws Exception {
	}
}
