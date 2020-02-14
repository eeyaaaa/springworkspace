package exam02;

public class Proxy implements Pet{
	Pet pet;
	
	public Proxy(Pet pet) {
		this.pet = pet;		
	}
	@Override
	public void cry() {
		System.out.println("개냥이 공통기능 (before)");
		pet.cry();
		System.out.println("개냥이 공통기능 (after)");
	}

}
