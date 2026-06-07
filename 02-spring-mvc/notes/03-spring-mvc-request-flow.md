# 03. Spring MVC Request Flow

## Luồng xử lý request trong Spring MVC

Spring MVC xử lý request theo mô hình **Front Controller**, trong đó `DispatcherServlet` là thành phần trung tâm nhận request đầu tiên và điều phối request đến các thành phần phù hợp.

## Flow tổng quát

```text
[1] Browser gửi request
        |
        v
[2] DispatcherServlet nhận request
        |
        v
[3] HandlerMapping tìm Controller phù hợp
        |
        v
[4] DispatcherServlet gọi Controller method
        |
        v
[5] Controller xử lý request
        |
        |-- gọi Service nếu cần
        |-- Service gọi Repository/DAO nếu cần
        |
        v
[6] Controller đưa dữ liệu vào Model
        |
        v
[7] Controller trả về View name
        |
        v
[8] DispatcherServlet gửi View name cho ViewResolver
        |
        v
[9] ViewResolver tìm file View thật
        |
        v
[10] View dùng Model để render HTML
        |
        v
[11] DispatcherServlet trả response HTML về Browser
```

## Giải thích từng bước

### 1. Browser gửi request

Người dùng gửi request từ trình duyệt đến server.

Ví dụ:

```text
GET /users
```

Request này sẽ được gửi đến ứng dụng web đang chạy trên server.

---

### 2. DispatcherServlet nhận request

`DispatcherServlet` là servlet trung tâm của Spring MVC.

Nó đóng vai trò như một **Front Controller**, nghĩa là mọi request đi vào Spring MVC đều được nhận và điều phối bởi `DispatcherServlet`.

`DispatcherServlet` không trực tiếp xử lý business logic. Nhiệm vụ chính của nó là điều phối request đến đúng controller.

---

### 3. HandlerMapping tìm Controller phù hợp

Sau khi nhận request, `DispatcherServlet` sẽ hỏi `HandlerMapping` để tìm xem request này nên được xử lý bởi controller method nào.

Ví dụ:

```java
@GetMapping("/users")
public String listUsers(Model model) {
    return "user-list";
}
```

Nếu request là:

```text
GET /users
```

thì `HandlerMapping` sẽ xác định rằng request này phù hợp với method `listUsers()`.

---

### 4. DispatcherServlet gọi Controller method

Sau khi tìm được controller method phù hợp, `DispatcherServlet` sẽ gọi method đó để xử lý request.

Controller có thể nhận dữ liệu từ request thông qua:

- `@RequestParam`
- `@PathVariable`
- `@ModelAttribute`
- Form data
- Object binding

---

### 5. Controller xử lý request

Controller chịu trách nhiệm xử lý phần request/response flow.

Controller có thể gọi Service để xử lý nghiệp vụ.

Ví dụ:

```java
@GetMapping("/users")
public String listUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user-list";
}
```

Trong ví dụ trên:

- Controller nhận request `/users`
- Controller gọi `userService.findAll()`
- Service xử lý logic và lấy dữ liệu
- Controller đưa dữ liệu vào `Model`
- Controller trả về tên view `"user-list"`

Controller không nên query database trực tiếp. Việc xử lý nghiệp vụ nên đặt ở Service, còn thao tác database nên đặt ở Repository/DAO.

---

### 6. Controller đưa dữ liệu vào Model

`Model` là nơi chứa dữ liệu mà Controller muốn gửi sang View.

Ví dụ:

```java
model.addAttribute("users", users);
```

Có thể hiểu gần giống như:

```java
Map<String, Object> model = new HashMap<>();
model.put("users", users);
```

Trong đó:

```text
"users" -> tên attribute
users   -> dữ liệu thật, ví dụ List<User>
```

`Model` không phải Entity, DTO, Service hay Repository. Nó chỉ là object chứa dữ liệu dạng key-value để truyền từ Controller sang View.

---

### 7. Controller trả về View name

Controller thường không trả trực tiếp file HTML.

Thay vào đó, Controller trả về **tên view**.

Ví dụ:

```java
return "user-list";
```

Ở đây `"user-list"` là view name, chưa phải file HTML trực tiếp.

---

### 8. DispatcherServlet gửi View name cho ViewResolver

Sau khi Controller trả về view name, `DispatcherServlet` sẽ gửi view name đó cho `ViewResolver`.

Nhiệm vụ của `ViewResolver` là tìm file View thật tương ứng với view name.

Ví dụ:

```text
View name: user-list
```

Với Thymeleaf, `ViewResolver` có thể tìm đến file:

```text
src/main/resources/templates/user-list.html
```

---

### 9. ViewResolver tìm file View thật

`ViewResolver` chỉ có nhiệm vụ tìm View.

Nó không xử lý dữ liệu trong `Model` và cũng không render HTML.

Ví dụ:

```text
"user-list" -> templates/user-list.html
```

Điểm cần nhớ:

```text
ViewResolver chỉ tìm file View.
View mới dùng Model để render HTML.
```

---

### 10. View dùng Model để render HTML

Sau khi tìm được View, View sẽ dùng dữ liệu trong `Model` để render thành HTML.

Ví dụ trong Thymeleaf:

```html
<tr th:each="user : ${users}">
    <td th:text="${user.name}"></td>
</tr>
```

Ở đây:

```text
${users}
```

là dữ liệu được lấy từ `Model`.

Nếu trong Controller có:

```java
model.addAttribute("users", users);
```

thì trong Thymeleaf có thể dùng:

```html
${users}
```

để truy cập dữ liệu đó.

---

### 11. DispatcherServlet trả response về Browser

Sau khi View render xong HTML, response HTML sẽ được trả về cho `DispatcherServlet`.

Sau đó `DispatcherServlet` trả response cuối cùng về browser.

Browser nhận HTML và hiển thị giao diện cho người dùng.

---

## Ví dụ hoàn chỉnh

### Controller

```java
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }
}
```

### Thymeleaf View

File:

```text
src/main/resources/templates/user-list.html
```

Ví dụ:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>User List</title>
</head>
<body>
    <h1>Danh sách người dùng</h1>

    <table>
        <tr>
            <th>Name</th>
        </tr>

        <tr th:each="user : ${users}">
            <td th:text="${user.name}"></td>
        </tr>
    </table>
</body>
</html>
```

## Tóm tắt vai trò các thành phần

| Thành phần | Vai trò |
|---|---|
| Browser | Gửi request và nhận response HTML |
| DispatcherServlet | Front Controller, nhận request đầu tiên và điều phối luồng xử lý |
| HandlerMapping | Tìm controller method phù hợp với request |
| Controller | Xử lý request, gọi Service nếu cần, đưa dữ liệu vào Model và trả về view name |
| Service | Xử lý business logic |
| Repository/DAO | Làm việc với database |
| Model | Chứa dữ liệu dạng key-value để truyền sang View |
| ViewResolver | Tìm file View thật từ view name |
| View | Dùng Model để render HTML |
| Response | HTML hoàn chỉnh trả về browser |

## Ghi nhớ nhanh

```text
DispatcherServlet điều phối request.
HandlerMapping tìm controller.
Controller xử lý request và trả Model + View name.
ViewResolver tìm file View.
View dùng Model để render HTML.
```

Điểm dễ nhầm:

```text
ViewResolver không render dữ liệu.
ViewResolver chỉ tìm View.
View mới dùng Model để render HTML.
```