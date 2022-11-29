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
        1. Control-flow graph and Identify `defs`, `c-uses` and `p-uses` \
            ![Control-flow graph](./img/strip-string.svg)
        2. Tabular summary for each variable
            - Variable `text`
                | pair id | def | use      | path                 |
                | ---     | --- | ---      | ---                  |
                | 1       | L76 |  L77     | <L76, L77>           |
                | 2       | L76 | (L78, T) | <L76, L77, L78, L79> |
                | 3       | L76 | (L78, F) | <L76, L77, L78, L81> |
                | 4       | L76 |  L79     | <L76, L77, L78, L79> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: paths 1, 4
                    - all-p-uses: paths 2, 3
                    - all-uses: paths 1, 2, 3, 4
            - Variable `length`
                | pair id | def | use      | path                 |
                | ---     | --- | ---      | ---                  |
                | 1       | L76 | (L78, T) | <L76, L77, L78, L79> |
                | 2       | L76 | (L78, F) | <L76, L77, L78, L81> |
                | 3       | L76 |  L79     | <L76, L77, L78, L79> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: path 3
                    - all-p-uses: paths 1, 2
                    - all-uses: paths 1, 2, 3
            - Variable `result`
                | pair id | def | use      | path                 |
                | ---     | --- | ---      | ---                  |
                | 1       | L77 |  L81     | <L77, L78, L79, L81> |
                | 2       | L77 |  L81     | <L77, L78, L81>      |
                | 3       | L79 |  L81     | <L79, L81>           |
                - Coverage criteria:
                    - all-defs: paths 2, 3
                    - all-c-uses: paths 2, 3
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: paths 2, 3
    - Unit tests generated

2. `Cbc.encrypt(byte[] data, int length)` (Cbc.java, line 161)
    - Function definition
        > Encrypts the first `length` bytes of the array `data`.
    - Step-by-step of the ‘Dataflow Testing’ algorithm
        1. Control-flow graph and Identify `defs`, `c-uses` and `p-uses` \
            ![Control-flow graph](./img/encrypt.svg)
        2. Tabular summary for each variable
            - Variable `data`
                | pair id | def  | use       | path                                                        |
                | ---     | ---  | ---       | ---                                                         |
                | 1       | L161 | (L162, T) | <L161, L162, L163>                                          |
                | 2       | L161 | (L162, F) | <L161, L162, L166 (`int i = 0`)>                            |
                | 3       | L161 |  L167     | <L161, L162, L166 (`int i = 0`), L166 (`i < length`), L167> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: path 3
                    - all-p-uses: paths 1, 2
                    - all-uses: paths 1, 2, 3
            - Variable `length`
                | pair id | def  | use                      | path                                                        |
                | ---     | ---  | ---                      | ---                                                         |
                | 1       | L161 | (L162, T)                | <L161, L162, L163>                                          |
                | 2       | L161 | (L162, F)                | <L161, L162, L166 (`int i = 0`)>                            |
                | 3       | L161 | (L166 (`i < length`), T) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`), L167> |
                | 4       | L161 | (L166 (`i < length`), F) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`), end>  |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: N/A (there are no c-uses)
                    - all-p-uses: paths 1, 2, 3, 4
                    - all-uses: paths 1, 2, 3, 4
            - Variable `i`
                | pair id | def                | use                      | path                 |
                | ---     | ---                | ---                      | ---                  |
                | 1       | L166 (`int i = 0`) | (L166 (`i < length`), T) | <L166 (`int i = 0`), L166 (`i < length`), L167> |
                | 2       | L166 (`int i = 0`) | (L166 (`i < length`), F) | <L166 (`int i = 0`), L166 (`i < length`), end>  |
                | 3       | L166 (`int i = 0`) |  L166 (`++i`)            | <L166 (`int i = 0`), L166 (`i < length`), L167, L168, L169, L170, L171, L166 (`++i`)> |
                | 4       | L166 (`int i = 0`) |  L166 (`++i`)            | <L166 (`int i = 0`), L166 (`i < length`), L167, L168,                   L166 (`++i`)> |
                | 5       | L166 (`int i = 0`) |  L167                    | <L166 (`int i = 0`), L166 (`i < length`), L167> |
                | 6       | L166 (`++i`)       | (L166 (`i < length`), T) | <L166 (`++i`)      , L166 (`i < length`), L167> |
                | 7       | L166 (`++i`)       | (L166 (`i < length`), F) | <L166 (`++i`)      , L166 (`i < length`), end>  |
                | 8       | L166 (`++i`)       |  L166 (`++i`)            | <L166 (`++i`)      , L166 (`++i`)>              |
                | 9       | L166 (`++i`)       |  L167                    | <L166 (`++i`)      , L166 (`i < length`), L167> |
                - Coverage criteria:
                    - all-defs: paths 1, 9
                    - all-c-uses: paths 4, 5, 8, 9
                    - all-p-uses: paths 1, 2, 6, 7
                    - all-uses: paths 1, 2, 4, 5, 6, 7, 8, 9
            - Variable `BLOCK_SIZE`
                | pair id | def  | use       | path  |
                | ---     | ---  | ---       | ---   |
                | 1       | L161 | (L168, T) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`>), L167, L168, L169> |
                | 2       | L161 | (L168, F) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`>), L167, L168, L166> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: N/A (there are no c-uses)
                    - all-p-uses: paths 1, 2
                    - all-uses: paths 1, 2
            - Variable `this._overflow`
                | pair id | def  | use  | path                                                                    |
                | ---     | ---  | ---  | ---                                                                     |
                | 1       | L161 | L169 | <L161, L162, L166 (`int i = 0`), L166 (`i < length`), L167, L168, L169> |
                | 2       | L161 | end  | <L161, L162, L166 (`int i = 0`), end>                                   |
                | 3       | L167 | L169 | <L167, L168, L169>                                                      |
                | 4       | L167 | end  | <L167, L168, L169, L170, L171, L166 (`++i`), L166 (`i < length`), end>  |
                | 5       | L167 | end  | <L167, L168,                 , L166 (`++i`), L166 (`i < length`), end>  |
                - There isn't a def-clear path between the definition in L161 and the c-use in L169 since the variable is redefined in L167. So, this variable cannot meet the coverage criteria of `all-defs`, `all-c-uses`, `all-p-uses` and `all-uses`.
                - Coverage criteria:
                    - all-defs: paths 2, 3
                    - all-c-uses: There isn't a def-clear path between all the definitions and all the c-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L169 since the variable is redefined in L167.
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: There isn't a def-clear path between all the definitions and all the c-uses and p-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L169 since the variable is redefined in L167.
            - Variable `this._overflowUsed`
                | pair id | def  | use       | path                 |
                | ---     | ---  | ---       | ---                  |
                | 1       | L161 |  L167     | <L161, L162, L166 (`int i = 0`), L166 (`i < length`>), L167>                     |
                | 2       | L161 | (L168, T) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`>), L167, L168, L169>         |
                | 3       | L161 | (L168, F) | <L161, L162, L166 (`int i = 0`), L166 (`i < length`>), L167, L168, L166 (`++i`)> |
                | 4       | L167 |  L167     | <L167, L167>                                                                     |
                | 5       | L161 | (L168, T) | <L167, L168, L169>                                                               |
                | 6       | L161 | (L168, F) | <L167, L168, L166 (`++i`)>                                                       |
                | 7       | L171 |  L167     | <L171, L166 (`++i`), L166 (`i < length`), L167>                                  |
                | 8       | L161 | (L168, T) | <L171, L166 (`++i`), L166 (`i < length`), L167, L168, L169>                      |
                | 9       | L161 | (L168, F) | <L171, L166 (`++i`), L166 (`i < length`), L167, L168, L166 (`++i`)>              |
                - Coverage criteria:
                    - all-defs: paths 1, 4, 7
                    - all-c-uses: There isn't a def-clear path between all the definitions and all the c-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L167 since the variable is redefined in L167.
                    - all-p-uses: There isn't a def-clear path between all the definitions and all the p-uses
                        - There isn't a def-clear path between the definition in L161 and the p-use in L168 since the variable is redefined in L167.
                    - all-uses: There isn't a def-clear path between all the definitions and all the c-uses and p-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L167 or the p-use in L168 since the variable is redefined in L167.
            - Variable `this._output`
                | pair id | def  | use  | path                                                                      |
                | ---     | ---  | ---  | ---                                                                       |
                | 1       | L161 | L170 | <L161, L162, L166 (int i = 0), L166 (i < length), L167, L168, L169, L170> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: path 1
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: path 1
            - Variable `this._outBuffer`
                | pair id | def  | use  | path                 |
                | ---     | ---  | ---  | ---                  |
                | 1 | L161 | L170 | <L161, L162, L166 (`int = 0`), L166 (`i < length`), L167, L168, L166(`++i`), L166 (`i < length`), L167, L168, L169, L170> |
                | 2       | L161 | L170 | <L161, L162, L166 (`int = 0`), L166 (`i < length`), L167, L168, L169, L170>                            |
                | 3       | L161 | end  | <L161, L162, L166 (`int = 0`), L166 (`i < length`), L167, L168, L166(`++i`), L166 (`i < length`), end> |
                | 4       | L161 | end  | <L161, L162, L166 (`int = 0`), L166 (`i < length`), end>                                               |
                | 5       | L169 | L170 | <L169, L170>                                                                                           |
                | 6       | L169 | end  | <L169, L170, L171, L166 (`++i`), L166 (`i < length`), end>                                             |
                - Coverage criteria:
                    - all-defs: path 4, 5
                    - all-c-uses: There isn't a def-clear path between all the definitions and all the c-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L170 since the variable is redefined in L169.
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: There isn't a def-clear path between all the definitions and all the c-uses
                        - There isn't a def-clear path between the definition in L161 and the c-use in L170 since the variable is redefined in L169.
    - Unit tests generated

3. `Aes256.mul(int a, byte b)` (Aes256.java, line 310)
    - Note: we changed this function from private to public to use it in our tests
    - Function definition
        > Multiplies the two polynomials `a` and `b` (cryptographic function).
    - Step-by-step of the ‘Dataflow Testing’ algorithm
        1. Control-flow graph and Identify `defs`, `c-uses` and `p-uses` \
            ![Control-flow graph](./img/mul.svg)
        2. Tabular summary for each variable
            - Variable `a`
                | pair id | def  | use   | path               |
                | ---     | ---  | ---   | ---                |
                | 1       | L310 |  L312 | <L310, L311, L312> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: path 1
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: path 1
            - Variable `b`
                | pair id | def  | use   | path                     |
                | ---     | ---  | ---   | ---                      |
                | 1       | L310 |  L313 | <L310, L311, L312, L313> |
                - Coverage criteria:
                    - all-defs: path 1
                    - all-c-uses: path 1
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: path 1
            - Variable `result`
                | pair id | def  | use  | path                                                         |
                | ---     | ---  | ---  | ---                                                          |
                | 1       | L311 | L316 | <L311, L312, L313, L314, L315, L316>                         |
                | 2       | L311 | L316 | <L311, L312, L313, L314, L315, L318, L319, L314, L315, L316> |
                | 3       | L311 | L321 | <L311, L312, L313, L314, L315, L316, L318, L319, L314, L321> |
                | 4       | L311 | L321 | <L311, L312, L313, L314, L315, L318, L319, L314, L321>       |
                | 5       | L311 | L321 | <L311, L312, L313, L314, L321>                               |
                | 6       | L316 | L316 | <L316, L316>                                                 |
                | 7       | L316 | L321 | <L316, L318, L319, L314, L321>                               |
                - Coverage criteria:
                    - all-defs: paths 1, 6
                    - all-c-uses: paths 1, 5, 6, 7
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: paths 1, 5, 6, 7
            - Variable `first`
                | pair id | def  | use       | path                                 |
                | ---     | ---  | ---       | ---                                  |
                | 1       | L312 | (L314, T) | <L312, L313, L314, L315>             |
                | 2       | L312 | (L314, F) | <L312, L313, L314, L321>             |
                | 3       | L312 | (L315, T) | <L312, L313, L314, L315, L316>       |
                | 4       | L312 | (L315, F) | <L312, L313, L314, L315, L318>       |
                | 5       | L312 |  L318     | <L312, L313, L314, L315, L316, L318> |
                | 6       | L312 |  L318     | <L312, L313, L314, L315, L318>       |
                | 7       | L318 | (L314, T) | <L318, L319, L314, L315>             |
                | 8       | L318 | (L314, F) | <L318, L319, L314, L321>             |
                | 9       | L318 | (L315, T) | <L318, L319, L314, L315, L316>       |
                | 10      | L318 | (L315, F) | <L318, L319, L314, L315, L318>       |
                | 11      | L318 |  L318     | <L318, L318>                         |
                - Coverage criteria:
                    - all-defs: paths 1, 7 
                    - all-c-uses: paths 6, 11
                    - all-p-uses: paths 1, 2, 3, 4, 7, 8, 9, 10
                    - all-uses: paths 1, 2, 3, 4, 6, 7, 8, 9, 10, 11
            - Variable `current`
                | pair id | def  | use   | path                                 |
                | ---     | ---  | ---   | ---                                  |
                | 1       | L313 |  L316 | <L313, L314, L315, L316>             |
                | 2       | L313 |  L319 | <L313, L314, L315, L316, L318, L319> |
                | 3       | L313 |  L319 | <L313, L314, L315, L318, L319>       |
                | 4       | L319 |  L316 | <L319, L314, L315, L316>             |
                | 5       | L319 |  L319 | <L319, L319>                         |
                - Coverage criteria:
                    - all-defs: paths 1, 5
                    - all-c-uses: paths 1, 3, 4, 5
                    - all-p-uses: N/A (there are no p-uses)
                    - all-uses: paths 1, 3, 4, 5
    - Unit tests generated
