# UDP Message Encoder (Java)

##  Problem Statement
This project implements a **UDP Client-Server communication system** in Java.

- The **Client** sends a message to the **Server**.
- The **Server** encodes the message by shifting each character to the next one (Caesar cipher with shift = 1).
  - Example:  
    `a → b`, `b → c`, ..., `z → a`  
    `HELLO → IFMMP`
- The **Server** sends back the encoded message to the Client.
- The **Client** displays the encoded message.

---

##  Files
- `UDPServer.java` → Handles server-side operations.
- `UDPClient.java` → Handles client-side operations.
- `README.md` → Project description and usage instructions.

---

##  How to Run

1. Compile both files:
   ```bash
   javac UDPServer.java UDPClient.java
