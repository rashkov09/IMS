# IMS - (INVENTORY MANAGEMENT SYSTEM)

# E-commerce Console Application

To start application run **mvn clean install** and run.

## Current Functionality

### Default Admin User
- Username: admin
- Password: admin

### Admin
1. Add or remove employee users
2. Activate ADMIN status for other employees
3. Add suppliers
4. Add products to suppliers
5. Add and remove items
6. Perform all tasks that employees can do

### Employee
1. Add and remove items
2. Add BUY orders to resupply or remove orders
3. View all orders
4. Search inventory

### Customer
1. View profile
2. View all products
3. Add items to the personal cart
4. View items in the cart
5. Create SELL orders and add items from the cart to the order
6. Cancel orders if the status is "CREATED" or "PROCESSED"
7. Search

Orders are created with a status of "CREATED" and need to be processed.

## Future Development

### Payment Service
- Process payments

### Order Service

#### SELL Order
1. Process orders (set status of orders to processed) and trigger the payment process
2. Payment process (after triggering, the status of orders is changed to "PENDING-PAYMENT")
3. After successful payment, the status of orders is changed to "PAID," and item quantity is deducted from the current item quantity

#### BUY Order
1. Process orders (set status of orders to processed) and trigger the payment process to the supplier
2. Payment process (after triggering, the status of orders is changed to "PENDING-PAYMENT")
3. After successful payment, the quantity of the order is added to the current item quantity

### Reporting Service

1. Report profit from the difference in BUY/SELL orders
2. Reports by product
3. Reports by specific client
