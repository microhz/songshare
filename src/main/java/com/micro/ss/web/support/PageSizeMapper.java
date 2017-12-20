package com.micro.ss.web.support;

import java.util.List;

/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */
public interface PageSizeMapper<T, E> {
	List<E> limitSelect(T example);
}
