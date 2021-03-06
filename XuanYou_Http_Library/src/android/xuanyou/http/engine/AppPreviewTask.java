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

public abstract class AppPreviewTask extends BaseHttpTask {

	public AppPreviewTask(String URL) {
		super(URL);

	}
	
	/**
	 * 获取游戏分类列表数据通用接口
	 * @param pageSize
	 * @param pageIndex
	 * @param columnId
	 */
	public void acquireCategoryAppList(int pageSize, int pageIndex, String categoryId, String IMEI) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("categoryId", categoryId);
		params.put("IMEI", IMEI);
		HttpRequestUtil.setTaskParamsAndRequest(AppPreviewTask.this, params, HttpMethod.GET);
	}
	
	
	/**
	 * 获取游戏分支列表数据通用接口
	 * @param pageSize
	 * @param pageIndex
	 * @param columnId
	 */
	public void acquireCategoryIdList(int pageSize, int pageIndex, String columnId,String categoryId, String IMEI) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("columnId", columnId);
		params.put("peripheralsType", categoryId);
		params.put("IMEI", IMEI);
		HttpRequestUtil.setTaskParamsAndRequest(AppPreviewTask.this, params, HttpMethod.GET);
	}
	
	/**
	 * 专题AppList
	 * @param pageSize
	 * @param pageIndex
	 * @param specialTopicId
	 * @param IMEI
	 */
	public void acquireSubjuctList(int pageSize, int pageIndex, String specialTopicId, String IMEI) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("specialTopicId", specialTopicId);
		params.put("IMEI", IMEI);
		HttpRequestUtil.setTaskParamsAndRequest(AppPreviewTask.this, params, HttpMethod.GET);
	}
	
	/**
	 * 专题AppList
	 * @param pageSize
	 * @param pageIndex
	 * @param specialTopicId
	 * @param IMEI
	 */
	public void acquireSubjuctCategoryList(int pageSize, int pageIndex, String specialTopicId,String categoryId,  String IMEI) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("specialTopicId", specialTopicId);
		params.put("peripheralsType", categoryId);
		params.put("IMEI", IMEI);
		HttpRequestUtil.setTaskParamsAndRequest(AppPreviewTask.this, params, HttpMethod.GET);
	}

	@Override
	public RequestHandle execute(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
		return null;
	}

}
