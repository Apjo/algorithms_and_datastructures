## Class

A template(or a blueprint) used to create many objects. groups related data, and actions together. Attributes of a class represents state or data of an object. Methods(functions inside a class) represent the behavior or actions the object can perform

```
class Car:
    def __init__(self, make, model):
        self.__make=make
        self.__model = model
        self.__odometer_reading = 0
```

## Object
An instance of the class. Each object gets its own copy of the data defined in the class,shares the same structure & behavior, and operates independently of every other object of that same class
```
volkwagen_passat = Car("Volkswagen", "Passat")
```

## Enums
Used for defining fixed set of named constants. Are type-safe, can only take one value out of a predefined set of options. Helps improve code readbility,  enables compiler checks, thereby reducing bugs.

### simple enum
```
from enum import Enum
class VehicleType(Enum):
    CAR="car"
    BIKE="bike"
    TRUCK="truck"
#using it in code
vehicle_truck = VehicleType.TRUCK
if vehicle == VehicleType.BIKE:
....
```

### Enums with properties and values
```
from enum import Enum
class Coin(Enum):
    PENNY=1
    NIKCEL=5
    DIME=10
    QUARTER=25

    def __init__(self, value):
        self.coin_value=value
    def get_value(self):
        return self.coin_value
#using it in code
total = Coin.PENNY.get_value() + Coin.QUARTER.get_value()
```

## Interface

A contract that defines any implementing class must provide. It specifies a set of behaviors that a class agrees to implement, but leaves the details of those behaviors up to each implementation
An interface defines the "what", and class(es) define the "how". This enables polymorphism, and prevents decoupling.

```
from abc import ABC, abstractmethod

class Vehicle(ABC):
    @abstractmethod
    def move(self):
        pass

class Car(Vehicle):
    def move(self):
        print("Car moves on the road")

class Bike(Vehicle):
    def move(self):
        print("Bike pedals and moves on the road")

class Truck(Vehicle):
    def move(self):
        print("Truck moves on the road")

vehicles = [Car(), Truck(), Bike()]
for v in vehicles:
    v.move()

```

## Abstraction
Hiding complexity + Show only whats necessary. Focus is on what the object does, and not how it does it. Cn be achieved using abstract classes, interfaces, and clean public APIs.

An abstract class defines a common behavior for related classes. It can contain both abstract methods (declared but not implemented), and concrete methods (fully implemented). Subclasses must implement the abstract methods but inherit the concrete ones as is.

This is what makes abstract classes different from interfaces: they let you share behavior, not just a contract.

```
from abc import ABC, abstractmethod

class Logger(ABC):
    def __init__(self, level: int):
        self.__level = level
    
    @abstractmethod
    def log(self, message: str):
        pass

    def format_message(message: str):
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        return f"[{timestamp}] [{self._level}] {message}"

class ConsoleLogger(Logger):
    def __init__(self, level):
        super().__init__(level)
    
    def log(self, message):
        print(self.format_message(message))

```


## Encapsulation

Data hiding + controlled data access. i.e. hides internal complexity only exposing whats necessary.

```

class BankAccount:
    def __init__(self, user_account_name):
        self.user_name = user_account_name
        self.__balance = 0.0 #mangling access by keeping it private
    
    @property
    def get_current_balance(self) -> float:
        return self.__balance
    
    @property
    def get_user_name(self) -> str:
        return self.user_name
    
    #public entry points validating user inputs
    def withdraw(self, amount: float) -> None:
        if amount > self.__balance:
            raise ValueError("Insufficient funds")
        if amount <= 0:
            raise ValueError("Amount to withdraw Must Be  greater than zero and positive")            
        self.__balance -= amount

    def deposit(self, amount: float) -> None:
        if amount <= 0:
            raise ValueError("Deposit Must Be  greater than zero and positive")
        self.__balance += amount
    
```

## Inheritance

Common logic reside in base/parent class, while specialized logic lies in multiple derived/sub/child classes while allowing these subclasses inherit the common behaviors.

```
from abc import ABC, abstractmethod

class Car(ABC):
    def __init__(self, make, model, year):
        self._make=make
        self._model=model
        self.year = year

    def start(self):
        print("engine started")
    
    def stop(self):
        print("engine stopped")
    

class ElectricCar(Vehicle):
    
    def __init__(self, make, model, year, battery_capacity: int):
        super().__init__(make, model, year)
        self.__cap = battery_capacity
    
    def charge(self):
        print(f"charging {self.battery_capacity}kWh capacity")

class GasCar(Vehicle):

    def __init__(self, make, model, year, fuel_capacity: int):
        super().__init__(make, model, year)
        self.__cap = fuel_capacity
    
    def refill(self):
        print(f"Filling {self.fuel_capacity}gallon capacity")

```

## Polymorphism
Lets us call the same method on different objects, and have each object respond in its own way, or having the same method name or interface to exhibit different behaviors depending on the caller.

```

class Vehicle():
    def __init(self, make, model, year):
        self._make=make
        self._model=model
        self._year=year

    def move(self):
        pass

class Car(Vehicle):
    def move(self):
        print("Car moves on the road")

class Bike(Vehicle):
    def move(self):
        print("Bike pedals and moves on the road")

class Truck(Vehicle):
    def move(self):
        print("Truck moves on the road")

vehicles = [Car(), Truck(), Bike()]
for v in vehicles:
    v.move()
```

Use an interface when the implementing classes are fundamentally different but share a capability.  An interface defines that contract without forcing a shared hierarchy.
Use an abstract class when the implementing classes are a family with shared logic.
