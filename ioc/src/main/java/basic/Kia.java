package basic;

public class Kia {
    public Car sell(Money money){
        System.out.println("기아 자동차 돈주면 차를 팝니다." + money.getAmount());
        Car car = new Car("k9");

        System.out.println("기아에서 만든 붕붕이는?" + car.getName());
        return car;
    }
}
