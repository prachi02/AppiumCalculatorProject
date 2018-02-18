Feature: Sum calculator

Scenario: Verification of results from decision table

Given the SumCalculator app is launched 
When give a list of numbers to first field
| 8 | 
| 121 | 
| 131 | 
And give a list of numbers to second field
| 13 | 
| 100 | 
| 99 | 
Then click Calculate Sum 
Then match the value with ExpectedSum
| 21|
| 201 |
| 230 |
