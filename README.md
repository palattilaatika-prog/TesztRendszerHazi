Testing without JUnit:
The Main class starts the test runner and gives it the test class.
The TesztFuttato class finds all methods marked with @Teszt.
A new test object is created for every test method.(as asked)
The @Elotte method runs before each test, and the @Utana method runs after each test.
The test runner calls each test method using Method.invoke().
The @VartKivetel annotation checks if a test throws the expected exception.
The Assert class checks if the results are correct. If a check fails, it throws an AssertHiba exception.
<img width="788" height="829" alt="image" src="https://github.com/user-attachments/assets/751072d6-0f22-4bf3-a494-8a7360343b66" />
Used AI for the following: generateing PeldaTeszt class, understanding annotations better and working out TesztFuttato logic.
