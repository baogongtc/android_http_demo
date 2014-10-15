package android.xuanyou.http.engine;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.utils.HttpRequestUtil;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public abstract class CommonTask extends BaseHttpTask {

	public CommonTask(String URL) {
		super(URL);

	}

	/**
	 * 获取标签下数据通用接口
	 * 
	 * @param id
	 */
	public void acquireCommonTabById(String id) {
		HttpRequestUtil.requestById(CommonTask.this, id);
	}

	/**
	 * 获取列表数据通用接口
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param columnId
	 */
	public void acquireTabList(int pageSize, int pageIndex, String columnId, String IMEI) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("columnId", columnId);
		params.put("IMEI", IMEI);
		HttpRequestUtil.setTaskParamsAndRequest(CommonTask.this, params, HttpMethod.GET);
	}

	@Override
	public RequestHandle execute(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
		
		return null;
	}

}
