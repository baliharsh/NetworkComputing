# NetworkComputing
Architecture: Single-tier Client-Server

WLFB is a bank with only 3 clients and offers transaction services to its clients. 

**Transaction services:**
1) Add Money (Deposit)
2) Subtract Money (Withdraw)
3) Transfer Money (Transfer)

**Instructions on how to run the application**
Instructions on how to run the Source Code:

Instructions on how to run the Compiled Code:

**Current Bug**
_#BUG1_
Thread1 --> Thread2 --> Thread3 
Client1 --> Client2 --> Client3
Server uses threads to distinguish between the clients and fetches their user account based on the associated thread.
So, clients must be executed in chronological manner to support concurrent transactions.
Client2 can only be connected after Client1.
Client3 can only be connectied after Client2.

**Future of WLFB bank Application**
In future, WLFB will have more than 3 clients. 
Probably 10, 20, 30 or more? 

**Future Updates**
Update required, to mitigate the bug.
Update required, to handle more than 3 clients. 
