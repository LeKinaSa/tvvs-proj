# Assignment 8

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## White-box testing: Dataflow Testing

These functions were selected mostly because they can easily be tested independently and are very different from one another, in terms of used variables.

Selected functions:
1. `StringUtils.stripString(String text, int length)` (StringUtils.java, line 76)
    - Function definition
        > Truncates the input string (`text`) to a determined size (`length`), adding "..." at the end if the string was truncated. Should return null if the input `text` is null. Should return "" if the input `length` is invalid.
    - Step-by-step of the ‘Dataflow Testing’ algorithm
        1. Control-flow graph
        2. Identify `defs`, `c-uses` and `p-uses`
        3. Tabular summary for each variable
            - Variable `text`
            - Variable `length`
            - Variable `result`
    - Unit tests generated

2. `Cbc.encrypt(byte[] data, int length)` (Cbc.java, line 161)
    - Function definition
        > Encrypts the first `length` bytes of the array `data`.
    - Step-by-step of the ‘Dataflow Testing’ algorithm
        1. Control-flow graph
        2. Identify `defs`, `c-uses` and `p-uses`
        3. Tabular summary for each variable
            - Variable `data`
            - Variable `length`
            - Variable `i`
            - Variable `this._overflow`
            - Variable `this._overflowUsed`
            - Variable `this._output`
            - Variable `this._outBuffer`
            (TODO: _output, ... definidos na linha 161, junto com data e length)
    - Unit tests generated

3. `Aes256.mul(int a, byte b)` (Aes256.java, line 310)
    - Note: we changed this function from private to public to use it in our tests
    - Function definition
        > Multiplies the two polynomials `a` and `b` (cryptographic function).
    - Step-by-step of the ‘Dataflow Testing’ algorithm
        1. Control-flow graph
        2. Identify `defs`, `c-uses` and `p-uses`
        3. Tabular summary for each variable
            - Variable `a`
            - Variable `b`
            - Variable `result`
            - Variable `first`
            - Variable `current`
    - Unit tests generated
