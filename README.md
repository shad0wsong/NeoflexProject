# NeoflexProject
Hi this is a study project for Neoflex. Credit conveoyer with the logic below.</nr>

<h1>Project logic</h1>
The user submits a loan application.
MS Application performs prescoring of the application and if the prescoring passes, then the application is saved in MC Deal and sent to the CC.
CC returns through the MS Application to the user 4 offers (entity "LoanOffer") for a loan with different conditions (for example, without insurance, with insurance, with a payroll client, with insurance and a payroll client) or a refusal.
The user selects one of the offers, a request is sent to the MC Application, and from there to the MC Deal, where the loan application and the loan itself are stored in the database.
MS dossier sends a letter to the client with the text "Your application has been preliminarily approved, complete the registration."
The client sends a request to MS Deal with all his full data about the employer and registration. The data is scored in the CC, the CC calculates all data on the loan (TCP, payment schedule, etc.), the Deal MS saves the updated application and the loan entity made on the basis of the CreditDTO received from the CC with the CALCULATED status in the database.
After validation, the MC dossier sends an email to the client with approval or rejection. If the loan is approved, then the letter contains a link to the request "Generate documents"
The client sends a request for the formation of documents to MS Deal, MS Deal sends the client documents for signing and a link to the request for agreement to the terms by mail.
The client can refuse the conditions or agree. If he agrees, MC Deal sends a code and a link to sign documents to the mail, where the client must send the received code to MC Deal.
If the received code matches the one sent, MS dossier issues a loan (changes the status of the "Credit" entity to ISSUED, and the status of the application to CREDIT_ISSUED)

<h1>Architecture</h1>
![image](https://user-images.githubusercontent.com/101453514/185654790-13be0850-3066-48de-8cb8-3d6ae9aef9ae.png)

