package android.xuanyou.http.engine;

import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.utils.HttpRequestUtil;

import com.loopj.android.http.RequestParams;

public abstract class ManagerTask extends BaseHttpTask {

	public ManagerTask(String URL) {
		super(URL);
	}

	// ============================升级相关===================================//
	/**
	 * 八-1、客户端应用 升级接口
	 * 
	 * @param imei
	 * @param channelCode
	 * @param marketVersion
	 */
	public void UpdateApps(String imei, String channelCode, String marketVersion) {
		RequestParams params = new RequestParams();
		params.put("imei", imei);
		params.put("channelCode", channelCode);
		params.put("marketVersion", marketVersion);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 八-2、客户端应用 得到升级列表 接口 八-3、 客户端应用 得到小泡 接口
	 * 
	 * @param imei
	 */
	public void acquireClientUpdateResult(String imei) {
		RequestParams params = new RequestParams();
		params.put("imei", imei);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	// ============================收藏相关===================================//

	/**
	 * 二十五. 应用(资讯/娱乐)收藏列表接口
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param suid
	 * @param channelCode
	 * @param type
	 */
	public void collectionResult(int pageSize, int pageIndex, String suid, String channelCode, int type) {
		RequestParams params = new RequestParams();
		params.put("pageSize", pageSize);
		params.put("pageIndex", pageIndex);
		params.put("suid", suid);
		params.put("channelCode", channelCode);
		params.put("type", type);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十一、 我的收藏接口 取消收藏
	 * 
	 * @param suid
	 * @param appId
	 * @param channelCode
	 * @param type
	 */
	public void cancelCollection(String suid, String appId, String channelCode, int type) {
		RequestParams params = new RequestParams();
		params.put("suid", suid);
		params.put("appId", appId);
		params.put("channelCode", channelCode);
		params.put("type", type);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 二十四.收藏接口
	 * 
	 * @param suid
	 * @param channelCode
	 * @param type
	 * @param appId
	 */
	public void collect(String suid, int channelCode, int type, String appId) {
		RequestParams params = new RequestParams();
		params.put("suid", suid);
		params.put("channelCode", channelCode);
		params.put("type", type);
		params.put("appId", appId);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 四十九、应用是否已经收藏接口
	 */
	public void hasCollected(String appId, String channelCode, int type, String suid) {
		RequestParams params = new RequestParams();
		params.put("appId", appId);
		params.put("channelCode", channelCode);
		params.put("type", type);
		params.put("suid", suid);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	// ==========================用户信息、操作 相关==============================//

	/**
	 * 十二、用户修改密码接口
	 * 
	 * @param userId
	 * @param userPwd
	 * @param oldPwd
	 */
	public void modifyPassword(String userId, String userPwd, String oldPwd) {
		RequestParams params = new RequestParams();
		params.put("user.id", userId);
		params.put("user.pwd", userPwd);
		params.put("oldPwd", oldPwd);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十三、用户修改用户名接口
	 * 
	 * @param userId
	 * @param userNickName
	 */
	public void modifyUserName(String userId, String userNickName) {

		RequestParams params = new RequestParams();
		params.put("user.id", userId);
		params.put("user. nickName", userNickName);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十四、用户找回密码接口
	 * 
	 * @param email
	 */
	public void findPassword(String email) {
		RequestParams params = new RequestParams();
		params.put("email", email);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十五、用户登录接口
	 */
	public void login(String account, String password) {
		RequestParams params = new RequestParams();
		params.put("user.account", account);
		params.put("user.pwd", password);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十六、用户注册接口
	 */
	public void regist(String account, String password, String mobile, int type, int regestType) {
		RequestParams params = new RequestParams();
		params.put("user.account", account);
		params.put("user.pwd", password);
		params.put("user.mobile", mobile);
		params.put("user.type", type);
		params.put("regestType", regestType);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	// ==========================推送相关==================================//

	/**
	 * 二十六-1、客户端后台推送是否存在  接口
	 */
	public void isPushExist() {
		RequestParams params = new RequestParams();
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 二十六-2、客户端后台推送接口
	 * 
	 * @param imei
	 * @param packageVersionCode
	 * @param channelCode
	 */
	public void push(String imei, String packageVersionCode, String channelCode) {

		RequestParams params = new RequestParams();
		params.put("imei", imei);
		params.put("packageVersionCode", packageVersionCode);
		params.put("channelCode", channelCode);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);

	}

	/**
	 * 四十一.域名配置路径
	 */
	public void configureDomain() {

		RequestParams params = new RequestParams();
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 五十、版本推送接口
	 */
	public void pushVersionUpdate(String channelCode) {
		RequestParams params = new RequestParams();
		params.put("channelCode", channelCode);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 五十二、地摊吧版本升级
	 * 
	 * @param channelCode
	 * @param name
	 * @param packageVersionCode
	 */
	public void updateTerminal(String channelCode, String name, String packageVersionCode) {
		RequestParams params = new RequestParams();
		params.put("channelCode", channelCode);
		params.put("name", name);
		params.put("packageVersionCode", packageVersionCode);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 四十七、应用详情 下拉推送接口
	 * 
	 * @param imei
	 * @param channelCode
	 * @param versionCode
	 */
	public void acquirePushInDetails(String imei, String channelCode, String versionCode) {
		RequestParams params = new RequestParams();
		params.put("imei", imei);
		params.put("channelCode", channelCode);
		params.put("versionCode", versionCode);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

	/**
	 * 三十、用户修改图片地址接口
	 * 
	 * @param id
	 */
	public void updatePicturesUri(String id) {
		HttpRequestUtil.requestById(ManagerTask.this, id);
	}

	/**
	 * 三十八.客户端得到用户图片 接口
	 * 
	 * @param id
	 */
	public void acquirePictures(String id) {
		HttpRequestUtil.requestById(ManagerTask.this, id);
	}

	/**
	 * 三十七.用户修改 图片 接口
	 * 
	 * @param uid
	 */
	public void modifyPictures(String uid) {
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		HttpRequestUtil.setTaskParamsAndRequest(ManagerTask.this, params, HttpMethod.POST);
	}

}
