package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드
//    public void order(String name, int price){
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제
//    }
        public int order(String name, int price){
            System.out.println("name = " + name + " price = " + price);
            return price;
       } // 위의 코드는 숫자가 공유됨 공유되지않도록 만들어 줘야댐
    //진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자

//    public int getPrice(){
//        return price;
//    }
}
