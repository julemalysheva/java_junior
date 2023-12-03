package org.example.lec01;


public class Main {
    public static void main(String[] args) {
//        PlainInterface plainInterface = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x + y);
//            }
//        };


//          PlainInterface plainInterface = (x, y) -> x + y;
          PlainInterface plainInterface = Integer::sum;//аналогично

          //если нужно больше одной строки кода, тогда в фигурные скобки и return
//        PlainInterface plainInterface = (x, y) -> {
//            return String.valueOf(x + y);
//        };

//        PlainInterface plainInterface1 = (x, y) -> Integer.compare(x, y);
        PlainInterface plainInterface1 = Integer::compare;
        //интерфейс функц-й, метод compare используется как объект, т.е. java понимает, что надо подставить туда переданный метод.

        System.out.println(plainInterface.action(5, 3));
        System.out.println(plainInterface1.action(1, 5));

    }
}