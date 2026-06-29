# JVM Report

## What is JVM?

The Java Virtual Machine (JVM) is responsible for executing Java bytecode.

It enables the Java principle:

**Write Once, Run Anywhere (WORA).**

---

# JVM Components

## Class Loader

Loads `.class` files into memory.

Responsibilities:

- Loading
- Linking
- Initialization

---

## Runtime Data Areas

### Heap Memory

Stores

- Objects
- Arrays

Shared among all threads.

---

### Stack Memory

Stores

- Method Calls
- Local Variables

Each thread has its own stack.

---

### Method Area

Stores

- Class Metadata
- Static Variables
- Constant Pool

---

### Program Counter (PC Register)

Stores the address of the currently executing JVM instruction.

Each thread has its own PC Register.

---

### Native Method Stack

Stores native method execution.

---

# Execution Engine

Responsible for executing bytecode.

Contains

- Interpreter
- JIT Compiler
- Garbage Collector

---

## Interpreter

Executes bytecode line by line.

Slower but starts execution immediately.

---

## JIT Compiler

Compiles frequently executed code into native machine code.

Improves performance significantly.

---

## Garbage Collector

Automatically frees unused objects from Heap Memory.

Advantages

- Prevents memory leaks
- Efficient memory management

---

# Java Execution Flow

```
Java Source Code (.java)

↓

Compiler (javac)

↓

Bytecode (.class)

↓

JVM

↓

Machine Code

↓

Execution
```

---

# Advantages of JVM

- Platform Independent
- Automatic Memory Management
- Security
- Performance using JIT
- Robust Exception Handling

---

# Conclusion

The JVM provides platform independence, automatic memory management, and optimized execution through the JIT compiler, making Java reliable for enterprise applications.