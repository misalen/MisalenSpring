package org.misalen.core.wxpay.sdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * 交易保障
 */
public class WechatPayReport {

	public static class ReportInfo {

		/**
		 * 布尔变量使用int。0为false， 1为true。
		 */

		// 基本信息
		private String version = "v0";
		private String sdk = "wxpay java sdk v1.0";
		private String uuid; // 交易的标识
		private long timestamp; // 上报时的时间戳，单位秒
		private long elapsedTimeMillis; // 耗时，单位 毫秒

		// 针对主域名
		private String firstDomain; // 第1次请求的域名
		private boolean primaryDomain; // 是否主域名
		private int firstConnectTimeoutMillis; // 第1次请求设置的连接超时时间，单位 毫秒
		private int firstReadTimeoutMillis; // 第1次请求设置的读写超时时间，单位 毫秒
		private int firstHasDnsError; // 第1次请求是否出现dns问题
		private int firstHasConnectTimeout; // 第1次请求是否出现连接超时
		private int firstHasReadTimeout; // 第1次请求是否出现连接超时

		public ReportInfo(String uuid, long timestamp, long elapsedTimeMillis, String firstDomain,
				boolean primaryDomain, int firstConnectTimeoutMillis, int firstReadTimeoutMillis,
				boolean firstHasDnsError, boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
			this.uuid = uuid;
			this.timestamp = timestamp;
			this.elapsedTimeMillis = elapsedTimeMillis;
			this.firstDomain = firstDomain;
			this.primaryDomain = primaryDomain;
			this.firstConnectTimeoutMillis = firstConnectTimeoutMillis;
			this.firstReadTimeoutMillis = firstReadTimeoutMillis;
			this.firstHasDnsError = firstHasDnsError ? 1 : 0;
			this.firstHasConnectTimeout = firstHasConnectTimeout ? 1 : 0;
			this.firstHasReadTimeout = firstHasReadTimeout ? 1 : 0;
		}

		@Override
		public String toString() {
			return "ReportInfo{" + "version='" + version + '\'' + ", sdk='" + sdk + '\'' + ", uuid='" + uuid + '\''
					+ ", timestamp=" + timestamp + ", elapsedTimeMillis=" + elapsedTimeMillis + ", firstDomain='"
					+ firstDomain + '\'' + ", primaryDomain=" + primaryDomain + ", firstConnectTimeoutMillis="
					+ firstConnectTimeoutMillis + ", firstReadTimeoutMillis=" + firstReadTimeoutMillis
					+ ", firstHasDnsError=" + firstHasDnsError + ", firstHasConnectTimeout=" + firstHasConnectTimeout
					+ ", firstHasReadTimeout=" + firstHasReadTimeout + '}';
		}

		/**
		 * 转换成 csv 格式
		 *
		 * @return
		 */
		public String toLineString(String key) {
			String separator = ",";
			Object[] objects = new Object[] { version, sdk, uuid, timestamp, elapsedTimeMillis, firstDomain,
					primaryDomain, firstConnectTimeoutMillis, firstReadTimeoutMillis, firstHasDnsError,
					firstHasConnectTimeout, firstHasReadTimeout };
			StringBuffer sb = new StringBuffer();
			for (Object obj : objects) {
				sb.append(obj).append(separator);
			}
			try {
				String sign = WechatPayUtil.HMACSHA256(sb.toString(), key);
				sb.append(sign);
				return sb.toString();
			} catch (Exception ex) {
				return null;
			}

		}

	}

	private static final String REPORT_URL = "http://report.mch.weixin.qq.com/wxpay/report/default";
	// private static final String REPORT_URL = "http://127.0.0.1:5000/test";

	private static final int DEFAULT_CONNECT_TIMEOUT_MS = 6 * 1000;
	private static final int DEFAULT_READ_TIMEOUT_MS = 8 * 1000;

	private LinkedBlockingQueue<String> reportMsgQueue = null;
	private WechatPayConfig config;
	private ExecutorService executorService;

	private volatile static WechatPayReport INSTANCE;

	private WechatPayReport(final WechatPayConfig config) {
		this.config = config;
		reportMsgQueue = new LinkedBlockingQueue<String>(config.getReportQueueMaxSize());

		// 添加处理线程
		executorService = Executors.newFixedThreadPool(config.getReportWorkerNum(), new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = Executors.defaultThreadFactory().newThread(r);
				t.setDaemon(true);
				return t;
			}
		});

		if (config.shouldAutoReport()) {
			for (int i = 0; i < config.getReportWorkerNum(); ++i) {
				executorService.execute(new Runnable() {
					public void run() {
						while (true) {
							// 先用 take 获取数据
							try {
								StringBuffer sb = new StringBuffer();
								String firstMsg = reportMsgQueue.take();
								String msg = null;
								sb.append(firstMsg); // 会阻塞至有消息
								int remainNum = config.getReportBatchSize() - 1;
								for (int j = 0; j < remainNum; ++j) {
									// msg = reportMsgQueue.poll(); // 不阻塞了
									msg = reportMsgQueue.take();
									if (msg == null) {
										break;
									} else {
										sb.append("\n");
										sb.append(msg);
									}
								}
								// 上报
								WechatPayReport.httpRequest(sb.toString(), DEFAULT_CONNECT_TIMEOUT_MS,
										DEFAULT_READ_TIMEOUT_MS);
							} catch (Exception ex) {
							}
						}
					}
				});
			}
		}

	}

	/**
	 * 单例，双重校验，请在 JDK 1.5及更高版本中使用
	 *
	 * @param config
	 * @return
	 */
	public static WechatPayReport getInstance(WechatPayConfig config) {
		if (INSTANCE == null) {
			synchronized (WechatPayReport.class) {
				if (INSTANCE == null) {
					INSTANCE = new WechatPayReport(config);
				}
			}
		}
		return INSTANCE;
	}

	public void report(String uuid, long elapsedTimeMillis, String firstDomain, boolean primaryDomain,
			int firstConnectTimeoutMillis, int firstReadTimeoutMillis, boolean firstHasDnsError,
			boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
		long currentTimestamp = WechatPayUtil.getCurrentTimestamp();
		ReportInfo reportInfo = new ReportInfo(uuid, currentTimestamp, elapsedTimeMillis, firstDomain, primaryDomain,
				firstConnectTimeoutMillis, firstReadTimeoutMillis, firstHasDnsError, firstHasConnectTimeout,
				firstHasReadTimeout);
		String data = reportInfo.toLineString(config.getKey());
		if (data != null) {
			reportMsgQueue.offer(data);
		}
	}
	/**
	 * http 请求
	 * 
	 * @param data
	 * @param connectTimeoutMs
	 * @param readTimeoutMs
	 * @return
	 * @throws Exception
	 */
	private static String httpRequest(String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
		BasicHttpClientConnectionManager connManager;
		connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build(), null, null, null);
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();

		HttpPost httpPost = new HttpPost(REPORT_URL);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs)
				.setConnectTimeout(connectTimeoutMs).build();
		httpPost.setConfig(requestConfig);

		StringEntity postEntity = new StringEntity(data, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 "); // TODO:
																	// 很重要，用来检测
																	// sdk
																	// 的使用情况，要不要加上商户信息？
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		return EntityUtils.toString(httpEntity, "UTF-8");
	}

}
