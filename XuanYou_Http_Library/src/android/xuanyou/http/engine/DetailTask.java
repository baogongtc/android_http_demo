package android.xuanyou.http.engine;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.utils.HttpRequestUtil;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public abstract class DetailTask extends BaseHttpTask {

	public DetailTask(String URL) {
		super(URL);
	}

	/**
	 * 二、应用详情头部接口 三、应用详情介绍接口 四、应用（资讯/专题/娱乐)评论列表接口
	 * 
	 * @param id
	 */
	public void aquireDetail(String id) {
		acquireBaseDetail(id);
	}

	private void acquireBaseDetail(String id) {
		HttpRequestUtil.requestById(DetailTask.this, id);
	}

	/**
	 * 十九、查询产品评价接口
	 * 
	 * @param appId
	 * @param pageSize
	 * @param pageIndex
	 * @param channelCode
	 */
	public void queryProductComment(String appId, String pageSize, String pageIndex) {
		RequestParams params = new RequestParams();
		params.put("id", appId);
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		HttpRequestUtil.setTaskParamsAndRequest(DetailTask.this, params, HttpMethod.POST);
	}

	@Override
	public RequestHandle execute(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
		
		return null;
	}
	
}
