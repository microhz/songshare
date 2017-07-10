package com.micro.ss.web.constants;
/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	/*@Value("com.micro.ss.hot.commentary.limit")
	private Integer hotCommentaryLimit;
	
	@Value("com.micro.ss.recommend.limit")
	private Integer recommendLimit;*/
	
	public Integer getHotCommentaryLimit() {
		// TODO 支持热更新
//		return this.hotCommentaryLimit;
		return 1;
	}

	public Integer getRecommendLimit() {
//		return this.recommendLimit;
		return 1;
	}

}
