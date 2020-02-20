package controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	
	/*form과 to를 입력받을때 문자열로 받아 Date타입으로 변환해야하는데
	 * 스프링은  Long이나 int에 대한 변환은 처리할수 있지만 Date타입 변환은 자동으로 되지않기 때문에 어노테이션을 사용.
	 * @DateTimeFormat을 이용하면 pattern값에 따라 Date타입으로 변환해준다.
	 * */
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date to;
	public Date getFrom() {
		return from;
	}
	public Date getTo() {
		return to;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	

}
