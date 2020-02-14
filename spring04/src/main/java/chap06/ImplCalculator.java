package chap06;

public class ImplCalculator implements Calculator{
	
	//for문을 이용하여 팩토리얼을 구하는 클래스 
	@Override
	public long factorial(long num) {
		long result =1;
		for(int i =1; i<=num; i++) {
			result *= i;
		}
		return result;
	}

}
