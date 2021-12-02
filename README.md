# NetworkComputing
Architecture: Single-tier Client-Server

WLFB is a bank with only 3 clients and offers transaction services to its clients. 

**Transaction services:**
1) Add Money (Deposit)
2) Subtract Money (Withdraw)
3) Transfer Money (Transfer)

**Instructions on How to run the application** <br />
_Please ensure Java is installed in your workstation_ <br />
Instructions on how to run the Source Code:<br />
1. Download the WLFB_BankAPP file.
2. Unzip the file.
3. Open up a JAVA IDE (Eclipse)
4. Import the file into the IDE.
5. Open 4 Console windows. 
6. Open the server class and Run it.
7. Open Client class and Run it.
8. Open Client2 class and Run it.
9. Open Client3 class and Run it.
10. Assign console view to server and each client.
11. Ready to perform Transactions.

Instructions on how to run the Compiled Code:<br />
1. Download WLFB Compile Code file.
2. Unzip the file.
3. Open four Command Prompts(CMD).
4. In all the Command Prompt change the directory using **cd /** command and change it to the location where the files are saved.
5. Use **java Server** command to run the server.
6. Use **java Client** command to run the client1.
7. Use **java Client2** command to run the client2.
8. Use **java Client3** command to run the client3.
9. Ready to perform Transactions.

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
