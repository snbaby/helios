package com.seadun.helios.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.HeliosException;

public class Utils {
	public static String httpGet(String url, Map<String, String> params) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		String srtResult = "";
		try {
			URIBuilder builder = new URIBuilder(url);
			params.forEach((key, value) -> {
				builder.setParameter(key, value);
			});
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
					.setConnectionRequestTimeout(5000) // 设置请求超时时间
					.setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
					.build();
			HttpGet httpGet2 = new HttpGet(builder.build());
			httpGet2.setConfig(requestConfig);
			HttpResponse httpResponse = httpCilent.execute(httpGet2);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				srtResult = EntityUtils.toString(httpResponse.getEntity());
			} else {
				throw new HeliosException(HeliosExceptionConstants.INIT_EXCEPTION_CODE,
						HeliosExceptionConstants.INIT_EXCEPTION_MESSAGE,
						HeliosExceptionConstants.INIT_EXCEPTION_HTTP_STATUS);
			}
		} catch (ParseException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new HeliosException(HeliosExceptionConstants.INIT_EXCEPTION_CODE,
					HeliosExceptionConstants.INIT_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.INIT_EXCEPTION_HTTP_STATUS);
		} finally {
			try {
				httpCilent.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new HeliosException(HeliosExceptionConstants.INIT_EXCEPTION_CODE,
						HeliosExceptionConstants.INIT_EXCEPTION_MESSAGE,
						HeliosExceptionConstants.INIT_EXCEPTION_HTTP_STATUS);
			}
		}
		return srtResult;
	}
}
