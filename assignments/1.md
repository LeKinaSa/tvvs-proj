# Assignment 1

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## Questions

### Project description: what is it and how is the source code organized

Our project is JPass. It is a password manager application that allows the user to store their passwords (along with other relevant information, such as usernames and links) in an encrypted file. This file is password protected and stores the information between multiple application restarts.

Taking a quick look at the source code, there are hints of an MVC architecture being used. The source code is organized in multiple directories:

- `crypt`: Encryption primitives
- `data`: Home to the data model of the project, which is a singleton
- `ui`: Ui-related code
- `util`: Utility functions of various kinds
- `xml`: Code related to loading and saving `xml` files

### Static testing: what is it and why is it important and relevant

Static testing is a software testing method that doesn't require program execution. Instead, it analyses its code and resources to detect possible faults with the way the code was written. In certain ways, programming languages themselves do some of this static testing when they do type-checking, for example. The Rust programming language takes this idea to the next level with algebraic data types and statically verified safe concurrency guarantees.

Static analysis is important to help avoid failures when the program executes. It also improves code maintainability and readability.

### Static testing tools used: description and configuration

#### Checkstyle
A development tool designed to facilitate the enforcement of a coding standard in Java.

Configuration:
- Added configuration files used in the Recitation class #1.
- Changed the `basicOffset`, `braceAdjustment` and `caseIndent` properties in the `Indentation` configuration from **2** to **4**.
- Changed the maximum line length from **100** to **150** in the `LineLength` configuration.
- Deactivate the `CustomImportOrder` configuration.
- Removed `PACKAGE_DEF` from the values of `tokens` in the `EmptyLineSeparator` configuration.
- Changed pattern from `^[a-z][a-z0-9][a-zA-Z0-9]*$` to `^[a-z][a-zA-Z0-9]*$` when matching variable names in the `MemberName` configuration.
- Modified the patterns from `[a-z]([a-z0-9][a-zA-Z0-9]*)?$` to `^[a-z]([a-zA-Z0-9]*)?$` in the `ParameterName` and `LocalVariableName` configurations.
- Added `URL` as an allowed abbreviation in the `allowedAbbreviations` property of the `AbbreviationAsWordInName` configuration.

#### PMD
A source code analyzer is used to find common programming flaws like unused variables and empty catch blocks, among other common mistakes. It supports multiple languages, including Java.

Configuration:
- Added configuration files used in the Recitation class #1.
- Removed rule regarding `CommentSize` from the `pmd-comments.xml` file.

### Report description
#### Checkstyle
Checkstyle outputs an `HTML` page with multiple tables where it presents its findings separated into information (info), warnings, and errors.
Firstly, it presents a summary of all the findings to report. Afterwards, the number of reports per file, followed by the number of reports per violated rule. Finally, it presents a detailed view of each of the reports grouped by file, where each file has its own detailed problems overview.
After being configured for the project, it reports 0 info, 103 warnings, and 0 errors.

#### PMD
PMD organizes its information in a very different way.
There is no summary or count of the number of violations encountered.
The feedback is detailed (similar to the detailed view from Checkstyle) and organized in groups, first by priority and then by files.

### Selected bugs: description and how to fix them

#### Checkstyle
1. `SummaryJavadocSummaryJavadoc` - jpass/crypt/Aes256.java, line 82
> The comment doesn't have a period in its first sentence.

Before:
```java
82      /**
83       * expanded key
84       */
```

After:
```java
82      /**
83       * Expanded key.
84       */
```

2. `JavadocTagContinuationIndentation` - jpass/ui/GeneratePasswordDialog.java, line 126
> Line continuation doesn't have indentation; however, the convention specifies a 4-space indentation in these cases.

Before:
```java
121     /**
122      * Initializes the GeneratePasswordDialog instance.
123      *
124      * @param parent parent component
125      * @param showAcceptButton if true then the dialog shows an "Accept" and "Cancel" button,
126      * otherwise only a "Close" button
127      *
128      */
```
After:
```java
121     /**
122      * Initializes the GeneratePasswordDialog instance.
123      *
124      * @param parent parent component
125      * @param showAcceptButton if true then the dialog shows an "Accept" and "Cancel" button,
126      *     otherwise only a "Close" button
127      *
128      */
```

3. `VariableDeclarationUsageDistance` - jpass/crypt/Aes256.java, line 369
> The non-final variable is declared early but only used further down in the code (4 instructions away).

Before:
```java
362     private void mixColumn(int index) {
363         int s0 = mul(2, this._tmp[index]) ^ mul(3, this._tmp[index + 1])
364                 ^ (this._tmp[index + 2] & 0xff) ^ (this._tmp[index + 3] & 0xff);
365         int s1 = (this._tmp[index] & 0xff) ^ mul(2, this._tmp[index + 1])
366                 ^ mul(3, this._tmp[index + 2]) ^ (this._tmp[index + 3] & 0xff);
367         int s2 = (this._tmp[index] & 0xff) ^ (this._tmp[index + 1] & 0xff)
368                 ^ mul(2, this._tmp[index + 2]) ^ mul(3, this._tmp[index + 3]);
369         int s3 = mul(3, this._tmp[index]) ^ (this._tmp[index + 1] & 0xff)
370                 ^ (this._tmp[index + 2] & 0xff) ^ mul(2, this._tmp[index + 3]);
371         this._tmp[index] = (byte) (s0 & 0xff);
372         this._tmp[index + 1] = (byte) (s1 & 0xff);
373         this._tmp[index + 2] = (byte) (s2 & 0xff);
374         this._tmp[index + 3] = (byte) (s3 & 0xff);
375     }
```
After:
```java
362     private void mixColumn(int index) {
363         final int s0 = mul(2, this._tmp[index]) ^ mul(3, this._tmp[index + 1])
364                 ^ (this._tmp[index + 2] & 0xff) ^ (this._tmp[index + 3] & 0xff);
365         final int s1 = (this._tmp[index] & 0xff) ^ mul(2, this._tmp[index + 1])
366                 ^ mul(3, this._tmp[index + 2]) ^ (this._tmp[index + 3] & 0xff);
367         final int s2 = (this._tmp[index] & 0xff) ^ (this._tmp[index + 1] & 0xff)
368                 ^ mul(2, this._tmp[index + 2]) ^ mul(3, this._tmp[index + 3]);
369         final int s3 = mul(3, this._tmp[index]) ^ (this._tmp[index + 1] & 0xff)
370                 ^ (this._tmp[index + 2] & 0xff) ^ mul(2, this._tmp[index + 3]);
371         this._tmp[index] = (byte) (s0 & 0xff);
372         this._tmp[index + 1] = (byte) (s1 & 0xff);
373         this._tmp[index + 2] = (byte) (s2 & 0xff);
374         this._tmp[index + 3] = (byte) (s3 & 0xff);
375     }
```

4. `OverloadMethodsDeclarationOrder` - jpass/crypt/Cbc.java, line 173
> Overload methods are separated. The previous overload is at line 147.

Before:
```java
147     public void encrypt(byte[] data) throws IOException {
                ...}
159     public void decrypt(byte[] data) throws IOException {
                ...}
173     public void encrypt(byte[] data, int length) throws IOException {
                ...}
```
After:
```java
147     public void encrypt(byte[] data) throws IOException {
                ...}
161     public void encrypt(byte[] data, int length) throws IOException {
                ...}
182     public void decrypt(byte[] data) throws IOException {
                ...}
```

5. `OperatorWrap` - jpass/ui/MessageDialog.java, line 285
> Operator used at the end of the line instead of at the beginning of the new line.

Before:
```java
284     showErrorMessage(parent,
285                 "Cannot generate password hash:\n" +
286                 StringUtils.stripString(e.getMessage()) +
287                 "\n\nOpening and saving files are not possible!");
```
After:
```java
284     showErrorMessage(parent,
285                 "Cannot generate password hash:\n"
286                 + StringUtils.stripString(e.getMessage())
287                 + "\n\nOpening and saving files are not possible!");
```

#### PMD

1. `VariableNamingConventions` - jpass/crypt/Cbc.java, line 63
> Variable name starts with an underscore and that violates the naming conventions (it should be declared as `final`).

Before:
```java

63     private byte[] _buffer = null;
```

After:
```java
63     private final byte[] _buffer;
```

2. `MethodArgumentCouldBeFinal` - jpass/crypt/Cbc.java, line 131
> Parameter is not modified inside the function and is not declared `final`.

Before:
```java
131     private void decryptBlock(byte[] inBuffer) {
            ...}
```

After:
```java
131     private void decryptBlock(final byte[] inBuffer) {
            ...}
```

3. `ClassWithOnlyPrivateConstructorsShouldBeFinal` - jpass/util/SpringUtilities.java, lines 42-189
> This class doesn't have any public/protected constructors, only private ones, even though the class isn't final.

Before:
```java
42     public class SpringUtilities {
43
44         private SpringUtilities() {
45             // utility class
46         }
           ...}
```

After:
```java
42     public final class SpringUtilities {
43
44         private SpringUtilities() {
45             // utility class
46         }
           ...}
```

4. `CommentRequired` - jpass/util/Configuration.java, line 45
> A field is declared without a comment explaining what it represents.

Before:
```java
43     public final class Configuration {
44         
45         private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
46         private static Configuration INSTANCE;
47         private Properties properties = new Properties();
           ...}
```

After:
```java
43     public final class Configuration {
44         /**
45          * Reference to a Logger object.
46          */
47         private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
48         private static Configuration INSTANCE;
49         private Properties properties = new Properties();
           ...}
```

5. `RedundantFieldInitializer` - jpass/data/DataModel.java, line 49
> Variables are already initialized as `null` so this initialization is redundant.

Before:
```java
49         private byte[] password = null;
```

After:
```java
49         private byte[] password;
```
