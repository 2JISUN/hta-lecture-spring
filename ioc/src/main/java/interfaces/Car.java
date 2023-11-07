package interfaces;

public class Car {
    private String name;

    public Car(){

    }

    public Car(String name){
        this.name = name;
    }

    public String getName(){
        return this.name; // name 변수의 값을 반환

    }

    public void setName(String name) {
        this.name = name;
    }
}
