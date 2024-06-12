package _3_java_core.multithreading_network.lesson_1.workshop.pattern.decorator;

/**
 * Паттерн Декоратор - шаблон проектирования,
 * предназначенный для динамического подключения к объекту дополнительного поведения.
 * Паттерн Декоратор представляет гибкую альтернативу практике создания подклассов
 * с целью расширения функциональности.
 */

abstract class Car {
    String name = "Unnamed car";

    //для перечисления дополнительных опций
    public String getInfo() {
        return name;
    }

    //для подсчёта окончательной цены автомобиля
    public abstract int getPrice();
}

/**
 * декораторы должны относиться к тому же типу, что и декарируемые объекты
 */

abstract class Decorator extends Car {
    public abstract String getInfo();
}

class AudiA3 extends Car {
    public AudiA3() {
        name = "Audi A3";
    }

    @Override
    public int getPrice() {
        return 10_000;
    }
}

class AudiA4 extends Car {
    public AudiA4() {
        name = "Audi A4";
    }

    public int getPrice() {
        return 15_000;
    }
}

/**
 * реализуем тот же интерфейс или абстрактный класс, что и декарируемый компонент
 * содержит компонент (ссылка на компонент хранится в переменной экземпляра)
 * может расширить состояние компонента или добавить новые методы
 */

class GPS extends Decorator {
    Car car;
    public GPS (Car car) {
        this.car = car;
    }

    public String getInfo() {
        return car.getInfo() + " + GPS";
    }

    public int getPrice() {
        return car.getPrice() + 1500;
    }
}

class AirCondition extends Decorator {
    Car car;
    public AirCondition (Car car) {
        this.car = car;
    }

    //возможность динамически подключить новую функциональсть объектам.
    public String getInfo() {
        return car.getInfo() + "+ Air Condition";
    }

    public int getPrice() {
        return car.getPrice() + 1000;
    }
}

class Example {
    public static void main(String[] args) {
        Car car1 = new AudiA3();
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        car1 = new GPS(car1);
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        car1 = new AirCondition(car1);
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        Car car2 = new AirCondition(new GPS(new AudiA4()));
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());
    }
}
