class Vehicle{
    public void move(){
    System.out.println(“Vehicles can move!!”);
    }
}

class MotorBike extends Vehicle{
    public void move(){
    System.out.println(“MotorBike can move and accelerate too!!”);
    }
}

class Test{
    public static void main(String[] args){
    Vehicle vh=new MotorBike();
    vh.move();    // prints MotorBike can move and accelerate too!!
    vh=new Vehicle();
    vh.move();    // prints Vehicles can move!!
    }
}
