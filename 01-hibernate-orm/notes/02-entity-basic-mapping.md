## @Entity

`@Entity` dùng để đánh dấu một Java class là entity class. Khi một class được đánh dấu bằng `@Entity`, JPA/Hibernate hiểu rằng class này đại diện cho một đối tượng có thể được quản lý bởi persistence layer và có thể được mapping với một table trong database.

Ví dụ:
```java
@Entity
public class User {
}
```
## @Table

`@Table` dùng để cấu hình table mà entity sẽ mapping tới trong database.

Ví dụ:

```java
@Entity
@Table(name = "users")
public class User {
}
```

Trong ví dụ này, entity `User` được mapping với table `users`.

Nếu không khai báo `@Table`, JPA/Hibernate sẽ dùng tên entity/class làm tên table mặc định. Tuy nhiên trong thực tế, tên table mặc định có thể bị ảnh hưởng bởi naming strategy của Hibernate hoặc Spring Boot.

Ngoài `name`, `@Table` còn có thể cấu hình thêm `schema`, `catalog`, `indexes`, `uniqueConstraints`.


## @Id và @GeneratedValue

`@Id` dùng để đánh dấu field là primary key của entity. Mỗi entity bắt buộc phải có một identifier để JPA/Hibernate phân biệt các object với nhau và mapping với row tương ứng trong database.

Ví dụ:

```java
@Id
private Long id;
```

`@GeneratedValue` dùng để cấu hình cách sinh giá trị cho primary key. Annotation này thường được dùng khi id không nhập thủ công mà được sinh tự động.

Ví dụ:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

Trong ví dụ trên:

- `@Id`: field `id` là primary key.
- `@GeneratedValue`: giá trị `id` sẽ được sinh tự động.
- `GenerationType.IDENTITY`: database chịu trách nhiệm tự sinh id, thường thông qua cơ chế auto-increment hoặc identity column.

Một số strategy phổ biến của `@GeneratedValue` gồm:

- `AUTO`: để provider tự chọn strategy phù hợp.
- `IDENTITY`: database tự tăng id.
- `SEQUENCE`: dùng database sequence.
- `TABLE`: dùng một bảng riêng để quản lý id.

## @Column

`@Column` dùng để cấu hình cách một field trong entity mapping với một column trong database table.

Ví dụ:

```java
@Column(name = "email", nullable = false, unique = true, length = 100)
private String email;
```

Trong ví dụ trên:

- `name = "email"`: chỉ định tên column trong database là `email`.
- `nullable = false`: column này không được phép nhận giá trị `NULL`.
- `unique = true`: giá trị trong column này phải là duy nhất trong table.
- `length = 100`: giới hạn độ dài của column, thường áp dụng với kiểu `String`.

Nếu không khai báo `@Column`, JPA/Hibernate vẫn có thể tự mapping field với column dựa trên tên field. Ví dụ field `email` có thể được mapping với column `email`.

## Một entity class cơ bản cần những gì?

Một entity class cơ bản cần được đánh dấu bằng `@Entity` để JPA/Hibernate biết đây là class cần được quản lý và có thể mapping với database table.

Entity cũng cần có primary key, thường được khai báo bằng `@Id`. Primary key giúp JPA/Hibernate định danh từng entity object và liên kết nó với row tương ứng trong database.

Ví dụ:

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    protected User() {
    }

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
```

Một entity class cơ bản thường có:

- `@Entity` để đánh dấu class là entity.
- `@Table` nếu muốn cấu hình tên table.
- `@Id` để khai báo primary key.
- `@GeneratedValue` nếu id được sinh tự động.
- Các field đại diện cho dữ liệu cần lưu.
- `@Column` nếu muốn cấu hình column cụ thể.
- Constructor không tham số để JPA/Hibernate có thể khởi tạo object.
- Getter/setter hoặc phương thức truy cập phù hợp để làm việc với dữ liệu.

Trong JPA thuần, entity class cần được provider nhận diện thông qua cấu hình như `persistence.xml` hoặc cơ chế scan. Trong Spring Boot, việc scan entity thường được tự động nếu entity nằm trong package phù hợp.