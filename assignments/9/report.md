# Assignment 9

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## White-box testing: Mutation testing

To generate the report from the PIT library, run: `mvn test-compile org.pitest:pitest-maven:mutationCoverage`.

Mutation testing requires a green suite. The following table resumes the corrections / modifications made to each of the failing tests from previous assignments.
| Test                                         | Previous Outcome                                   | Action Taken   | Current Outcome |
| ---                                          | ---                                                | ---            | ---             |
| DateUtilsTest.validNullFormatIsoDateTimeTest | Test failed (with NullPointerException)            | Commented out test | N/A         |
| nullStripNonValidXMLCharactersTest           | Test failed (expected "", output was null)         | Fixed source code  | Test passed |
| normalNegativeStripStringTest                | Test failed (with StringIndexOutOfBoundsException) | Fixed source code  | Test passed |
| boundaryNegativeStripStringTest              | Test failed (with StringIndexOutOfBoundsException) | Fixed source code  | Test passed |

### Before
![Before](img/before.png)

### Mutation Testing

Equivalent mutants: Cbc lines 162, 197 -> mutant changed conditional boundary (probably to <) which doesn't affect the code


TODO: 
Cbc.finishDecryption

### After
![After](after.png)
