package com.xuanwu.xtion.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xuanwu.xtion.utils.Assert;


/**
 * 查询参数
 * @author <a href="mailto:liushuaiying@139130.net">Shuaiying.Liu</a>
 * @Data 2015年5月27日
 * @Version 1.0.0
 */
public class QueryParameters {
	
	private Map<String, Object> params = new HashMap<String, Object>();
	
	private Map<String, String> sorts = new HashMap<String, String>();
	
	public QueryParameters() {

	}
	
	private PageInfo page;
	
	/**
	 * 添加一个命名参数
	 * @param key 参数名称
	 * @param value 参数值
	 * @return 当前对象本身
	 */
	public QueryParameters addParam(String key, Object value){
		Assert.notBlank(key);
		if (value == null) {
			return this;
		}
		if (value instanceof String && value.equals("")) {
			return this;
		}
		params.put(key, value);
		return this;
	}
	
    /**
     * 添加一组命名参数
     * @param params 其它参数MAP
     * @return 当前对象本身
     */
	public QueryParameters addParams(Map<String, Object> params){
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String, Object> entry : params.entrySet()){
				addParam(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}
	/**
	 * @description 参数值为空用空的字符串替代
	 * @param params
	 * @return
	 */
	public Map<String, Object> replaceNullWithEmptyStringParams(Map<String, Object> params){
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String, Object> entry : params.entrySet()){
				if(entry.getValue()==null){
					params.put(entry.getKey(), "");
				}
			}
		}
		return params;
	}
	/**
	 * @description 清空为null的参数
	 * @param params
	 * @return
	 */
	public Map<String, Object> clearNullAndEmptyParams(Map<String, Object> params){
		Map<String, Object> reParams = new HashMap<String, Object>();
		if(params != null && !params.isEmpty()){
			for(Map.Entry<String, Object> entry : params.entrySet()){
				if(entry.getValue()!=null&&!"".equals(entry.getValue())){
					reParams.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return reParams;
	}
	
	/**
	 * 添加一个排序参数
	 * @param key 参数名称
	 * @param ascDesc 升序或降序
	 * @return 当前对象本身
	 */
	public QueryParameters addSort(String key, String ascDesc){
		Assert.notBlank(key);
		if (StringUtils.isBlank(ascDesc)) {
			return this;
		}
		if("asc".equalsIgnoreCase(ascDesc)){
			sorts.put(key, "asc");
		} else if ("desc".equalsIgnoreCase(ascDesc)){
			sorts.put(key, "desc");
		}
		return this;
	}
	
	/**
     * 添加一组排序参数
     * @param key 参数名称
     * @param ascDesc 升序或降序
     * @return 当前对象本身
     */
	public QueryParameters addSorts(Map<String, String> sorts){
		if(sorts != null && !sorts.isEmpty()){
			for(Map.Entry<String, String> entry : sorts.entrySet()){
				addSort(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public <T> T getParam(String key, Class<T> clazz) {
		if (params == null) {
			return null;
		}
		if (params.containsKey(key)) {
			try {
				return clazz.cast(params.get(key));
			} catch (Exception ignored) {
			}
		}
		return null;
	}
	
	public Map<String, String> getSorts() {
		return sorts;
	}

    public PageInfo getPage() {
		return page;
	}
    
    public void setPage(PageInfo page) {
		this.page = page;
	}
	
}
