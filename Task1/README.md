## TASK-1
This folder contains **6 standalone Java mini-applications**, each designed to demonstrate **core object-oriented concepts, design patterns, and real-world problem solving**. All apps are **interactive CLI-based**, modular, and ready for extension.

---

## Mini-Apps Overview

| # | Project Name | Design Patterns | OOP Principles / Concepts | Summary |
|---|--------------|----------------|---------------------------|---------|
| 1 | **Meal Builder** | Builder | Encapsulation, Polymorphism | Users can build a custom meal by selecting items and quantities. The Builder pattern allows incremental meal construction. |
| 2 | **Coffee Shop Customizer** | Decorator | Open/Closed Principle, Runtime Composition | Add dynamic toppings to coffee orders without modifying existing classes. Demonstrates flexibility and composability. |
| 3 | **Vehicle Factory** | Factory | Abstraction, Polymorphism | Create vehicle instances (Car, Bike, Truck) dynamically. Users select vehicle type at runtime. Ensures single point of object creation. |
| 4 | **Parking Spot Manager** | Flyweight, Singleton | DRY (Don’t Repeat Yourself), Efficient Memory Usage | Manages multiple parking spots efficiently using Flyweight pattern for intrinsic/extrinsic state and Singleton for centralized management. |
| 5 | **Pizza Order Tracker** | Observer | Loose Coupling, Single Responsibility | Tracks pizza orders for Customer, Driver, and Manager. Observers are notified when order status changes. Supports multiple simultaneous updates. |
| 6 | **Ride Fare Calculator** | Strategy | Open/Closed Principle, Runtime Behavior Switching | Calculates fares for Normal, Premium, and Shared rides using interchangeable fare strategies. Allows adding new fare types without modifying core ride logic. |

---

## Features Across Mini-Apps

- **Dynamic CLI input handling** with validation.
- **Edge case handling** for invalid inputs and unusual scenarios.
- **Design pattern implementations** demonstrating clean architecture.
- **Extensible and modular code structure** for future growth.

---

Key Concepts Highlighted

Builder: Incremental construction, hides complexity.

Decorator: Dynamic behavior addition, open/closed principle.

Factory: Abstract object creation, promotes runtime flexibility.

Flyweight: Efficient memory management for repeated objects.

Singleton: Single point of access for shared resources.

Observer: Loose coupling between subject and observers, event-driven updates.

Strategy: Runtime selection of interchangeable behaviors.
