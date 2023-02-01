package org.example.Dao;

import org.example.Model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarsDAO {
    private static CarsDAO instanse=null;
    private static List<Car> cars=new ArrayList<>();
    static{
        cars.add(new Car(1,"BMW",5000.00));
        cars.add(new Car(2,"AUDI",5500.00));
        cars.add(new Car(3,"TOYOTA",10000.00));

    }
    private CarsDAO(){

    }
    public static CarsDAO getInstance(){
        if(instanse==null){
            instanse=new CarsDAO();
        }
        return instanse;
    }

    public List<Car>all(){
        return cars;
    }

    public int add(Car car){
        int newID=cars.size()+1;
        car.setId(newID);
        cars.add(car);
        return newID;
    }

    public Car getById(int id){
        Car car=new Car(id);
        int pos=cars.indexOf(car);
        if(pos>=0){
            return cars.get(pos);
        }
        return null;

    }

    public boolean update(Car car){
        int pos=cars.indexOf(car);
        if(pos>=0){
            cars.set(pos,car);
            return true;
        }
        return false;

    }
    public boolean delete(int id){
        Car car=new Car(id);
        int pos=cars.indexOf(car);
        if(pos>=0){
            return cars.remove(car);
        }
        return false;



    }

}
