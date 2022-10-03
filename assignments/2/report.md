# Assignment 1

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## Black-box testing: Category-Partition 

These functions were selected mostly because they can easily be tested independently and are very different from one another, in terms of parameters.

Selected package: `jpass.util`
Selected functions:
1. `DateUtils.formatIsoDateTime(String dateString, DateTimeFormatter formatter)`
    - Function definition
        > Transform a date (`dateString`) from the ISO format into the specified format (`formatter`).
    - Step-by-step of the ‘Category-Partition’ algorithm
        1. Parameters
            - String `dateString`
            - DateTimeFormatter `formatter`
        2. Characteristics of the parameters
            - String `dateString`: valid ISO format, invalid ISO format, null
            - DateTimeFormatter `formatter`: ISO format, other format, null
        3. Constraints / Invalid Input
            - String `dateString`: invalid ISO format, null
            - DateTimeFormatter `formatter`: null
        4. Combinations
            | String `dateString` | DateTimeFormatter `formatter` |
            | --- | --- |
            | valid ISO format | ISO format |
            | valid ISO format | other format |
            | valid ISO format  |  null |
            | invalid ISO format | ISO format |
            | null | ISO format |
    - Unit tests generated
        | String `dateString` | DateTimeFormatter `formatter` | Expected Output | Outcome |
        | --- | --- | --- | --- |
        | "2022-10-03T01:00:00" | ISO_LOCAL_DATE_TIME | "2022-10-03T01:00:00" | Test passed |
        | "2022-10-03T01:00:00" | ISO_LOCAL_DATE | "2022-10-03" | Test passed |
        | "2022-10-03T01:00:00" | null | "1970-01-01T01:00:00" | Test failed (with NullPointerException) |
        | "2022-10-03" | ISO_LOCAL_DATE_TIME | "1970-01-01T01:00:00" | Test passed |
        | null | ISO_LOCAL_DATE_TIME | "1970-01-01T01:00:00" | Test passed |

2. `StringUtils.stripNonValidXMLCharacters(final String in)`
    - Function definition
        > Replaces invalid XML unicode characters with '?' from the input string (`in`). Should return an empty string if the input is null.
    - Step-by-step of the ‘Category-Partition’ algorithm
        1. Parameters
            - String `in`
        2. Characteristics of the parameters
            - String `in`: null, empty string, clean string, string with invalid XML characters, string with both valid and invalid XML characters
        3. Constraints / Invalid Input
            - String `in`: (all inputs are valid)
        4. Combinations
            | String `in` |
            | --- |
            | null |
            | empty string |
            | string with only valid XML characters |
            | string with only invalid XML characters |
            | string with both valid and invalid XML characters |
    - Unit tests generated
        | String `in` | Expected Output | Outcome |
        | --- | --- | --- |
        | null | "" | Test failed (output was null) |
        | "" | "" | Test passed |
        | "abcdef" | "abcdef" | Test passed |
        | "\u0012" | "?" | Test passed |
        | "abc\u0012" | "abc?" | Test passed |

3. `StringUtils.stripString(String text, int length)`
    - Function definition
        > Truncates the input string (`text`) to a determined size (`length`). Should return null if the input is null.
    - Step-by-step of the ‘Category-Partition’ algorithm
        1. Parameters
            - String `text`
            - int `length`
        2. Characteristics of the parameters
            - String `text`: null, empty string, normal string
            - int `length`: negative value, 0, smaller than `text` length, same as `text` length, bigger than `text` length
        3. Constraints / Invalid Input
            - String `text`: null
            - int `length`: negative value (invalid), 0 and smaller than `text` length are the same when refering to the normal string
        4. Combinations
            | String `text` | int `length` |
            | --- | --- |
            | null | positive value |
            | empty string | positive value |
            | empty string | 0 |
            | normal string | negative value |
            | normal string | smaller than `text` length |
            | normal string | same as `text` length |
            | normal string | bigger than `text` length |
    - Unit tests generated
        | String `text` | int `length` | Expected Output | Outcome |
        | --- | --- | --- | --- |
        | null | 3 | null | Test passed |
        | "" | 3 | "" | Test passed |
        | "" | 0 | "" | Test passed |
        | "abcdef" | -1 | "" | Test failed (with StringIndexOutOfBoundsException) |
        | "abcdef" | 3 | "abc..." | Test passed |
        | "abcdef" | 6 | "abcdef" | Test passed |
        | "abcdef" | 9 | "abcdef" | Test passed |

4. `CryptUtils.getSha256Hash(final char[] text)`
    - Function definition
        > Calculates the `text`'s SHA-256 hash.
    - Step-by-step of the ‘Category-Partition’ algorithm
        1. Parameters
            - char[] `text`
        2. Characteristics of the parameters
            - char[] `text`: null, empty, normal array
        3. Constraints / Invalid Input
            - char[] `text`: null
        4. Combinations
            | char[] `text` |
            | --- |
            | null |
            | empty |
            | normal array |
    - Unit tests generated
        | char[] `text` | Expected Output | Outcome |
        | --- | --- | --- |
        | null | Exception thrown during execution | Test passed |
        | {} | "E3B0C44298FC1C149AFBF4C8996FB92427AE41E4649B934CA495991B7852B855" | Test passed |
        | {'a', 'b', 'c', 'd', 'd', 'e'} | "27410DDD3671EFF7BDE332C234EB3E3154F34FD2FFA34477EC61AB87F58BF4FE" | Test passed |

5. `DateUtils.createFormatter(String format)`
    - Function definition
        > Creates a `DateTimeFormatter` based on the input `format`.
    - Step-by-step of the ‘Category-Partition’ algorithm
        1. Parameters
            -String `format`
        2. Characteristics of the parameters
            - String `format`: null, empty, valid format, invalid format
        3. Constraints / Invalid Input
            - String `format`: null, empty, invalid format
        4. Combinations
            | String `format` |
            | --- |
            | null |
            | empty |
            | invalid format |
            | valid format |
    - Unit tests generated
        | String `format` | Expected Output | Outcome |
        | --- | --- | --- |
        | null | ISO_DATE | Test passed |
        | "" | ISO_DATE | Test failed (TODO) |
        | "q" | ISO_DATE | Test failed (TODO) |
        | "HH:mm:ss" | ISO_LOCAL_TIME | Test failed (TODO) |

