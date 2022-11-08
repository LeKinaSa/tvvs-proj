# Assignment 4

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## White-box testing: Structural Testing

### Code Coverage from the Tests from Assignment 2 and 3
When collecting line and branch coverage for the tests developed for assignment 2 and 3, we had to ignore the results for the tests with the command `mvn test -DskipTests` since some of those tests resulted in a failure. This command allows us to skip the test cases when building, so JaCoCo can run after and generate its report.

After running, the JaCoCo *framework* presented the following result, not achieving the desired 100% line and branch coverage for all packages except `ui`.

![JaCoCo Before](img/jacoco_before.png)

### Test Development

#### Package `jpass.crypt`

| Function | Input(s) | Expected Result | Outcome |
| -------- | -------- | --------------- | ------- |
| encrypt(byte[] data) | data = null | _encrypted stays the same | Passed |
| encrypt(byte[] data, int length) | data = null, length = 0 | _encryted stays the same | Passed |
| decrypt(byte[] data) | data = null | _decrypted stays the same | Passed |
| decrypt(byte[] data, int length) | data = null, length = 0 | _decrypted stays the same | Passed |

#### Package `jpass.crypt.io`
In this case, we copied the test `shouldDecryptAnEncryptedRandomMessage` and modified it in order to use the constructors `CryptOutputStream(OutputStream parent, byte[] key, byte[] iv)` and `public CryptInputStream(InputStream parent, byte[] key, byte[] iv)` and the function `CryptOutputStream.write(int b)`. The test passed.

#### Package `jpass.`



### Code Coverage after implementing more tests
Once again, we run the command `mvn test -DskipTests` to skip the tests during the build phase and be able to run JaCoCo and generate its report.

![JaCoCo After](img/jacoco_after.png)