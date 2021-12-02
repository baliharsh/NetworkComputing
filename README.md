# NetworkComputing
Architecture: Single-tier Client-Server

WLFB is a bank with only 3 clients and offers transaction services to its clients. 

**Transaction services:**
1) Add Money (Deposit)
2) Subtract Money (Withdraw)
3) Transfer Money (Transfer)

**Instructions on how to run the application** <br />
Instructions on how to run the Source Code:<br />

Instructions on how to run the Compiled Code:<br />

**Current Bug**
_#BUG1_<br />
Thread1 --> Thread2 --> Thread3 <br />
Client1 --> Client2 --> Client3<br />
Server uses threads to distinguish between the clients and fetches their user account based on the associated thread.<br />
So, clients must be executed in chronological manner to support concurrent transactions.<br />
Client2 can only be connected after Client1.<br />
Client3 can only be connectied after Client2.<br />

**Future of WLFB bank Application**<br />
In future, WLFB will have more than 3 clients. <br />
Probably 10, 20, 30 or more? <br />

**Future Updates**
1) Update required, to mitigate the bug.
2) Update required, to handle more than 3 clients. 
