package exam02;

public class Main {
	public static void main(String[] args) {		
		Pet cat = new Proxy(new Cat());
		Pet dog = new Proxy(new Dog());
		
		/*
		 * 개나 고양이가 cry()하면 그 시점 전 , 후에 어떤 기능을 실행하고자 한다.
		 * 1. 공통 기능이 직용될 행심 기능에 대해 인터페이스를 생성
		 * 2. 핵심기능에 해당되는 클래스는 1에서 만든 인터페이스를 구현
		 * 3. 핵심기능을 호출하는 공통기능이 정의된 클래스 작성
		 * */
		
		cat.cry();
		dog.cry();
	}
}
