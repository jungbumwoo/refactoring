

### The First Step in Refactoring


Whenever I do refactoring, the first step is always the same. I need to build a solid set of tests for that section of code.
If you don't, you end up spending time hand checking some numbers from the test against some numbers of a desk pad, and that slows you down.

**Before you start refactoring, check that you have a solid suite of tests. These tests must be self-checking.**

### Decomposing and Refactoring

1.
Tip. Refactoring changes the programs in small steps. If you make a mistake, it is easy to find the bug.

2.
Is renaming worth the effort?
Good code should communicate what it is doing clearly, and variable names are a key to clear code

Tip. Any fool can write code that a computer can understand. Good programmers write code that humans can understand.

3.
Moving the Amount Calculation

I like to get rid of temporary variables such as this as much as possible. 
Temps are often a problem in that they cause a lot of parameters to be passed around when they don't have to be. 
You can easily lose track of what they are there for. They are particularly insidious in long methods.


### Replacing the Conditional Logic on Price Code with Polymorphism

The first part of this problem is that switch statement. It is a bad idea to do a switch based on an attribute of another object.
If you must use a switch statement, it should be on your own data, not on someone else's.

Why do I prefer to pass the length of rental to the movie rather than the movie type to the rental? 
It's because the proposed changes are all about adding new types. Type information generally tends to be more volatile. 
If I change the movie type, I want the least ripple effect, 
so I prefer to calculate the charge within the movie.