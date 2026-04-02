Scenarios Covered
The test suite consists of 10 comprehensive test cases:

- Success Get Data: Basic validation for 200 OK.

- Authentication Check: Handling incorrect x-api-key (401/403).

- Missing Key Check: Ensuring API is protected when key is missing.

- Performance Check: Ensuring response time is below 500ms.

- Data Integrity: Validating that key response objects are not null.

- Schema Validation: Ensuring all expected fields exist in the response.

- Empty State Handling: Validation when no data is found (Empty Array).

- Pagination Consistency: Matching per_page value with actual array size.

- Strict Parameter Validation: Ensuring 400 Bad Request when passing string in page parameter.

- Data Consistency: Verifying requested page matches the response page field.

How to Setup & Run :

1. Prerequisites
Install Katalon Studio (Stand-alone or Enterprise).

Ensure you have Git installed on your machine.

2. Installation
Clone this repository

3. Execution
Navigate to Test Explorer on the left.

Open the folder Test Suites.

Double-click on automation_api_daffa.

Click the Run (Green Arrow) icon at the top toolbar.

Wait for execution to finish and check the Log Viewer for results.

Reports/ folder inside the project directory.

The reports include step-by-step logs, status codes, and assertion results.
