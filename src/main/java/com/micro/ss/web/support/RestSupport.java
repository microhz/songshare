package com.micro.ss.web.support;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.ss.web.enums.ResultEnum;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public abstract class RestSupport extends LoggerSupport {
	
	protected ObjectMapper objectMapper = new ObjectMapper();

	protected String ok() {
		Result result = new Result(ResultEnum.SUCCESS);
		return parseResult(result);
	}

	protected <T> String ok(Object data) {
		return parseResult(new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data));
	}

	protected String fail(String msg) {
		if (StringUtils.isNotBlank(msg)) {
			return parseResult(new Result(ResultEnum.ERROR.getCode(), msg));
		}
		return parseResult(new Result(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg()));
	}

	protected String fail(Integer code, String msg) {
		Integer c = code != null ? code : ResultEnum.ERROR.getCode();
		String m = StringUtils.isNotBlank(msg) ? msg : ResultEnum.ERROR.getMsg();
		return parseResult(new Result(c, m));
	}

	protected String fail() {
		return parseResult(new Result(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg()));
	}

	private String parseResult(Result result) {
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			// TODO record log
		}
		return null;
	}

	public class Result {
		private Integer code;
		private String msg;
		private Object data;

		public Result(ResultEnum resultEnum) {
			this.code = resultEnum.getCode();
			this.msg = resultEnum.getMsg();
		}

		Result(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public Result(Integer code, String msg, Object data) {
			this.code = code;
			this.msg = msg;
			this.data = data;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

	}
}
