package chap06;

public class RecCalculator implements Calculator{

	//재귀 호출을 이용하여 팩토리얼 구하는 기능의 클래스 
	@Override
	public long factorial(long num) {
		if(num ==0) {
			return 1;
		}
		else {
			return num * factorial(num -1);
		}
	}

}
