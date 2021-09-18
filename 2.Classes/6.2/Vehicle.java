class Vehicle extends Test{
    public void move() {
    System.out.println("Veiculos podem ser mover!!");
    }
}

class MotorBike extends Vehicle {
    public void move() {
    System.out.println("Moto pode se mover e acelerar tambem!!");
    }
}

class Test {
    public static void main(String[] args) {
    Vehicle vh=new MotorBike();
    vh.move();  
    vh=new Vehicle();
    vh.move();   
    }
}
