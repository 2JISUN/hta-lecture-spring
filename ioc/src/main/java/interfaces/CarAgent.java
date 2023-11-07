package interfaces;

public class CarAgent {
    private Hyundai hyundai;

    public CarAgent() {
        hyundai = new Hyundai();

    }

    public void order(){

        Money money = new Money(50000000);
        System.out.println("제네시스 차 가격: "+ money.getAmount());

        Car car = hyundai.sell(money);
        System.out.println("차 이름"+ car.getName());
    }


    
    
}
