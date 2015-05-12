[slides token from the mockito github wiki](https://github.com/mockito/mockito/wiki/Mockito-vs-EasyMock#similarities)

Again, hats down before the _EasyMock_ gang (record-playback is one of the coolest ideas I came across) - **THANKS!!!** Mockito started off as an _EasyMock_ fork but we evolved too much and we don't share any code with _EasyMock_. Most of the logic was rewritten, some of the code was also inspired by _jMock_ (like the excellent `ClassImposterizer`).
  
## Similarities

* Allow the same level verification as _EasyMock_ (unexpected invocations, redundant invocations, verification in order) 
* Argument matchers (`anyInt()`, `anyObject()`, etc.)

## Differences
 
* No record/replay modes - no need for them. There only 2 things you can do with Mockito mocks - **verify** or **stub**. Stubbing goes before execution and verification afterwards.

* All mocks are _nice_ (even somehow nicer, because collection-returning methods return empty collections instead of nulls). Even though mocks are nice, you can verify them as strictly as you want and detect any unwanted interaction.

* Explicit language for better readability: `verify()` and `when()` VS the mixture of `expect(mock.foo())` and `mock.foo()` (plain method call without _expect_). I'm sure some of you will find this argument subjective :)

* Simplified stubbing model - stubbed methods replay all the time with stubbed value no matter how many times they are called. Works exactly like EasyMock's `andStubReturn()`, `andStubThrow()`. Also, you can stub with different return values for different arguments (like in EasyMock). 

* Verification of stubbed methods is optional because usually it's more important to test if the stubbed value is used correctly rather than where's it come from.

* Verification is explicit - verification errors point at line of code showing what interaction failed.

* Verification in order is flexible and doesn't require to verify every single interaction.

* Custom argument matchers use hamcrest matchers, so you can use your existing hamcrest matchers. (EasyMock can also integrate with Hamcrest though it is not a part of EasyMock but Hamcrest. See the [documentation of Hamcrest](https://code.google.com/p/hamcrest/wiki/Tutorial)).