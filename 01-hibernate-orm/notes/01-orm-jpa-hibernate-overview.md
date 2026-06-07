
# ORM, JPA and Hibernate Overview

## ORM là gì?
ORM, viết tắt của Object-Relational Mapping, là kỹ thuật ánh xạ giữa object trong Java và bảng trong relational database.

Trong Java, chương trình thường làm việc với object như `User`, `Department`, `Job`. Còn trong database, dữ liệu được lưu dưới dạng table, row và column. ORM giúp ánh xạ:

- Class/Entity trong Java với table trong database
- Field trong class với column trong table
- Object instance với một row dữ liệu

Ví dụ, class `User` có các field `id`, `name`, `email` có thể được mapping với bảng `users` có các cột `id`, `name`, `email`.

ORM ra đời để giảm việc mapping thủ công khi dùng JDBC. Với JDBC thuần, lập trình viên phải tự viết SQL, tự đọc dữ liệu từ `ResultSet`, rồi tự gán từng column vào từng field của object. Hibernate/JPA giúp tự động hóa phần lớn quá trình này, 

## Vì sao Java object và relational database cần mapping?

Java object và relational database cần mapping vì hai bên biểu diễn dữ liệu theo hai mô hình khác nhau.

Trong Java, chương trình làm việc với class, object, field và reference giữa các object. Ví dụ, class `User` có thể có field `Department department`, tức là object `User` giữ reference trực tiếp tới object `Department`.

Trong relational database, dữ liệu được lưu bằng table, row và column. Database không lưu object bên trong object, mà biểu diễn quan hệ bằng primary key và foreign key. Ví dụ, bảng `users` có thể có cột `department_id` để liên kết với bảng `departments`.

Vì vậy, ORM cần mapping để biết:
- Class nào tương ứng với table nào
- Field nào tương ứng với column nào
- Reference object nào tương ứng với foreign key nào
- Collection hoặc relationship trong Java tương ứng với quan hệ nào trong database

## JPA là gì?

JPA, viết tắt của Java Persistence API, là một specification dùng để chuẩn hóa cách làm ORM trong Java.

JPA không phải là một ORM provider cụ thể. Nó định nghĩa bộ API và quy tắc chung để làm việc với persistent object, bao gồm cách đánh dấu entity, cách mapping class với table, cách quản lý entity lifecycle, cách thao tác với database thông qua `EntityManager`, và cách viết query bằng JPQL.

Các ORM provider như Hibernate, EclipseLink hoặc OpenJPA sẽ implement JPA specification. Khi lập trình viên sử dụng các annotation và interface trong `jakarta.persistence.*`, code đang phụ thuộc vào chuẩn JPA. Còn provider như Hibernate chịu trách nhiệm thực thi thật sự phía dưới.


## Hibernate là gì?

Hibernate là một ORM framework/provider trong Java. Nó giúp mapping giữa Java object và relational database, đồng thời hỗ trợ các thao tác persistence như insert, update, delete, query, transaction, lazy loading, dirty checking và caching.

Hibernate có native API riêng, thường được gọi là Hibernate Core. Khi dùng Hibernate Core, code sẽ phụ thuộc trực tiếp vào các class/interface của Hibernate, ví dụ `Session`, `SessionFactory`, hoặc các API nằm trong package `org.hibernate.*`.

Ngoài native API, Hibernate cũng implement JPA specification. Điều này cho phép lập trình viên code theo chuẩn JPA thông qua các annotation và interface như `@Entity`, `@Table`, `EntityManager`, `EntityManagerFactory`, `Query`, `TypedQuery`.

Nếu code theo chuẩn JPA, chương trình sẽ ít phụ thuộc trực tiếp vào Hibernate hơn và có khả năng thay đổi ORM provider dễ hơn, ví dụ chuyển từ Hibernate sang EclipseLink. Tuy nhiên trong thực tế, nhiều dự án vẫn dùng Hibernate làm provider chính vì Hibernate mạnh, phổ biến, nhiều tính năng và tích hợp tốt với Spring Boot.

## Vì sao ORM giúp giảm code so với JDBC?

Nếu dùng JDBC thuần, lập trình viên phải tự xử lý nhiều bước khi làm việc với database: mở connection, tạo transaction, viết SQL, tạo `PreparedStatement`, set parameter, execute query, đọc `ResultSet`, rồi tự map từng column sang từng field của object.

Ví dụ, khi query bảng `users`, JDBC chỉ trả về dữ liệu dạng `ResultSet`. Lập trình viên phải tự lấy từng column như `id`, `name`, `email`, sau đó tự tạo object `User` và gán dữ liệu vào object đó. Việc này dễ bị lặp code, dài dòng và dễ sai nếu tên column hoặc kiểu dữ liệu không khớp.

Hibernate/JPA giúp giảm phần lớn công việc mapping thủ công này. Lập trình viên có thể dùng annotation hoặc XML để khai báo quan hệ giữa entity class và database table. Sau đó, thông qua các API như `EntityManager`, Hibernate/JPA có thể hỗ trợ các thao tác cơ bản như `persist`, `find`, `merge`, `remove`, tự generate SQL cơ bản, quản lý entity state trong Persistence Context và hỗ trợ mapping relationship giữa các entity.

Tuy nhiên, ORM không có nghĩa là lập trình viên không cần biết SQL. Khi query phức tạp, tối ưu performance, xử lý N+1 query hoặc debug lỗi database, việc hiểu SQL vẫn rất quan trọng.