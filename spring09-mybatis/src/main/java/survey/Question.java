package survey;

import java.util.Collections;
import java.util.List;

//질문제목과 답변 옵션을 저장할수 있다. 
public class Question {
	private String title; //질문제목
	private List<String> options;  //답변옵션
	
	public Question(String title, List<String> options) {
		super();
		this.title = title;
		this.options = options;
	}
	public Question(String title) {
		this(title,Collections.<String>emptyList());
	}
	public String getTitle() {
		return title;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public boolean isChoice() {
		return options != null && !options.isEmpty(); 
		//답변 옵션이 null이 아니고 , List가 하나라도 있다면 true반환
	}
	

}
