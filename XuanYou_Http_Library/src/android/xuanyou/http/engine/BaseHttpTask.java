/**
 * Copyright 2014 xuanyou Inc. All rights reserved.
 * - Powered by Team GOF. -
 */

package android.xuanyou.http.engine;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import android.util.Log;
import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.interf.Postable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * @ClassName: BaseHttpTask
 * @Description: 请求基类
 * @author jinsongliu
 * @date 2014-9-15
 * 
 */

public abstract class BaseHttpTask implements Postable {

	public static AsyncHttpClient client = new AsyncHttpClient();

	private static String LOG_TAG = "BaseHttpTask";

	private String URL;

	private RequestParams params;

	AsyncHttpResponseHandler responseHandler;

	private HttpMethod method;

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public BaseHttpTask(String URL) {
		this.URL = URL;
	}

	@Override
	public AsyncHttpClient getAsyncHttpClient() {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		return client;
	}

	@Override
	public void setAsyncHttpClient(AsyncHttpClient client) {
		BaseHttpTask.client = client;
	}

	// public abstract Object parser(byte[] response);

	public <T> void request() {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		responseHandler = getResponseHandler();
		execute(client, getURL(), params, responseHandler);
		System.out.println("url:" + getURL());

	}

	public String getURL() {
		return URL;
	}

	public RequestParams getRequestParams() {
		if (params == null) {
			params = new RequestParams();
		}
		return params;
	}

	public void setRequestParams(RequestParams params) {
		this.params = params;
	}

	@Override
	public boolean isRequestBodyAllowed() {
		return false;
	}

	@Override
	public boolean isRequestHeadersAllowed() {
		return false;
	}

	//TODO add default url
	@Override
	public String getDefaultURL() {
		return "";
	}

	public void setResponseHandler(AsyncHttpResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}

	@Override
	public abstract AsyncHttpResponseHandler getResponseHandler();

	protected final void debugHeaders(String TAG, Header[] headers) {
		if (headers != null) {
			Log.d(TAG, "Return Headers:");
			StringBuilder builder = new StringBuilder();
			for (Header h : headers) {
				String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
				Log.d(TAG, _h);
				builder.append(_h);
				builder.append("\n");
			}
		}
	}

	protected final void debugStatusCode(String TAG, int statusCode) {
		String msg = String.format(Locale.US, "Return Status Code: %d", statusCode);
		Log.d(TAG, msg);
	}

	protected final void debugThrowable(String TAG, Throwable t) {
		if (t != null) {
			Log.e(TAG, "AsyncHttpClient returned error", t);
		}
	}

	protected final void debugResponse(String TAG, String response) {
		if (response != null) {
			Log.d(TAG, "Response data:");
			Log.d(TAG, response);
		}
	}

	@Override
	public HttpEntity getRequestEntity() {
		String bodyText;
		if (isRequestBodyAllowed() && (bodyText = getBodyText()) != null) {
			try {
				return new StringEntity(bodyText);
			} catch (UnsupportedEncodingException e) {
				Log.e("SampleParentActivity", "cannot create String entity", e);
			}
		}
		return null;
	}

	private String getBodyText() {

		return null;
	}

	@Override
	public void execute(AsyncHttpClient client, String URL, RequestParams params, ResponseHandlerInterface responseHandler) {

		if (getMethod() == null || getMethod() == HttpMethod.GET) {
			client.get(URL, params, responseHandler);
			return;
		}
		if (getMethod() == HttpMethod.POST) {
			client.post(URL, params, responseHandler);
			return;
		}
	}
}
