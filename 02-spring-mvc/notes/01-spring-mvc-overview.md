# 01. Spring MVC Overview

## Spring MVC là gì?

Spring MVC là một **web framework thuộc Spring Framework**, dùng để xây dựng web application theo mô hình **MVC**.

## Spring MVC giải quyết vấn đề gì?

Khi dùng Servlet/JSP thuần, lập trình viên thường phải tự xử lý nhiều việc lặp lại như:

- Mapping URL đến đúng servlet
- Lấy dữ liệu từ request
- Chuyển dữ liệu form sang object
- Gửi dữ liệu sang view
- Điều hướng response

Spring MVC giúp giảm các công việc thủ công đó bằng cách cung cấp một cơ chế xử lý request rõ ràng hơn, dựa trên `DispatcherServlet`, controller và các annotation như `@Controller`, `@GetMapping`, `@PostMapping`, `@RequestParam`, `@ModelAttribute`.

## Luồng xử lý cơ bản

```text
Client request
→ DispatcherServlet
→ Controller
→ Service
→ Repository
→ Model
→ View
→ Response HTML
```

## MVC gồm những thành phần nào?

### Model

**Model** là dữ liệu mà Controller đưa sang View để hiển thị, hoặc object dùng để nhận dữ liệu từ form.

Model trong MVC **không đồng nghĩa với toàn bộ Entity, DTO, Service, Repository**.

### View

**View** là phần giao diện hiển thị cho người dùng.

Trong Spring MVC + Thymeleaf, View thường là file `.html` nằm trong:

```text
src/main/resources/templates/
```

### Controller

**Controller** nhận request từ client, gọi Service nếu cần, đưa dữ liệu vào Model và trả về View.

Controller không nên chứa nhiều business logic và không nên query database trực tiếp.

## Phân biệt MVC và kiến trúc phân tầng

```text
MVC:
Controller → Model → View
```

```text
Layered Architecture:
Controller → Service → Repository → Database
```
## DispatcherServlet là gì?

`DispatcherServlet` là servlet trung tâm của Spring MVC, đóng vai trò như một **Front Controller**.

Khi client gửi request đến server, request sẽ đi qua `DispatcherServlet` trước. Sau đó, `DispatcherServlet` sẽ điều phối request đến đúng controller method dựa trên URL, HTTP method và các mapping annotation như `@GetMapping`, `@PostMapping`, `@RequestMapping`.

Sau khi controller xử lý xong, `DispatcherServlet` nhận lại kết quả từ controller, ví dụ tên view hoặc response data. Nếu controller trả về tên view, `DispatcherServlet` sẽ phối hợp với ViewResolver để tìm view tương ứng và render response HTML trả về cho client.

Tóm lại, `DispatcherServlet` không trực tiếp xử lý business logic, mà chịu trách nhiệm điều phối request và response trong Spring MVC.

## Controller nên làm gì?

Trong Spring MVC, `Controller` là thành phần nhận request từ client và điều phối request đó đến các thành phần phù hợp trong hệ thống.

Controller thường chịu trách nhiệm:

- Nhận request từ client thông qua các mapping như `@GetMapping`, `@PostMapping`
- Lấy dữ liệu từ request parameter, path variable hoặc form object
- Gọi Service để xử lý nghiệp vụ
- Đưa dữ liệu sau xử lý vào `Model`
- Trả về tên View hoặc redirect response

Ví dụ:

```java
@GetMapping("/users")
public String listUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user-list";
}
```
## Model trong Spring MVC là gì?

Trong Spring MVC, `Model` là object dùng để chứa dữ liệu mà Controller muốn gửi sang View.

Controller có thể thêm dữ liệu vào `Model` bằng `model.addAttribute()`. Sau đó View, ví dụ Thymeleaf template, có thể lấy dữ liệu đó ra để hiển thị.

Ví dụ:

```java
@GetMapping("/users")
public String listUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user-list";
}
```