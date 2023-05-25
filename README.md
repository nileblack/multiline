# INTRO
[Inspiration](https://github.com/adrianwalker/multiline-string)

# Example
maven repo https://repo.repsy.io/mvn/sam/code123
```xml
        <dependency>
            <groupId>in.code123</groupId>
            <artifactId>multiline</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
```

```java
import static io.code123.MultilineUtils.*;
...
    /**
     * SELECT * FROM user
     * WHERE ID = ?
     */
    @Multiline
    String queryUser;
...

    System.out.println("queryUser" + queryUser());
```

