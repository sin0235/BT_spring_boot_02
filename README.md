# Category CRUD Application

## Thông tin dự án

**Sinh viên thực hiện:** Trần Phúc Toàn  
**MSSV:** 23110344  
**Lớp:** 231102A  
**Môn học:** Lập trình Web  
**Chủ đề:** Category CRUD với Spring Boot & Thymeleaf  

## Mô tả dự án

Ứng dụng web quản lý Category sử dụng Spring Boot với các chức năng CRUD (Create, Read, Update, Delete) và tìm kiếm phân trang. Giao diện được xây dựng bằng Thymeleaf với thiết kế responsive và hiện đại.

## Tính năng chính

### ✅ CRUD Operations
- **Create**: Thêm category mới với tên và icon
- **Read**: Xem danh sách category với phân trang
- **Update**: Chỉnh sửa thông tin category
- **Delete**: Xóa category với xác nhận

### ✅ Tìm kiếm & Phân trang
- Tìm kiếm theo tên category (không phân biệt hoa thường)
- Phân trang với các tùy chọn kích thước trang (5, 10, 20, 50)
- Sắp xếp theo ID, tên, ngày tạo (tăng/giảm dần)
- Hiển thị thông tin trang hiện tại và tổng số bản ghi

### ✅ Upload File
- Upload icon cho category (JPG, PNG, GIF)
- Xem trước hình ảnh trước khi upload
- Kéo thả file để upload
- Giới hạn kích thước file (5MB)

### ✅ Giao diện người dùng
- Thiết kế responsive với Bootstrap 5
- Header có hình đại diện và thông tin ứng dụng
- Footer hiển thị thông tin sinh viên
- Thông báo flash messages
- Xác nhận trước khi xóa

## Công nghệ sử dụng

- **Backend**: Spring Boot 3.x
- **Frontend**: Thymeleaf + Bootstrap 5 + Font Awesome
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Java Version**: 17+

## Cấu trúc dự án

```
src/
├── main/
│   ├── java/vn/iotstar/
│   │   ├── BtwebApplication.java          # Main application
│   │   ├── controller/
│   │   │   ├── CategoryController.java    # Category CRUD controller
│   │   │   └── HomeController.java        # Root redirect
│   │   ├── entity/
│   │   │   └── Category.java              # Category entity
│   │   ├── repository/
│   │   │   └── CategoryRepository.java    # Category repository
│   │   ├── service/
│   │   │   └── CategoryService.java       # Category service
│   │   └── config/
│   │       └── WebConfig.java             # Web configuration
│   └── resources/
│       ├── templates/
│       │   ├── categories/
│       │   │   ├── list.html             # Danh sách category
│       │   │   ├── form.html             # Form thêm/sửa
│       │   │   └── view.html             # Chi tiết category
│       │   └── layout/
│       │       └── main.html             # Layout chính
│       └── application.properties         # Cấu hình ứng dụng
```

## Hướng dẫn cài đặt

### 1. Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code...)

### 2. Thiết lập database
```sql
-- Chạy script database_setup.sql
mysql -u root -p < database_setup.sql
```

### 3. Cấu hình database
Cập nhật file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bt_spring_boot
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Chạy ứng dụng
```bash
# Clone hoặc download project
cd BT_spring_boot

# Compile và chạy
mvn spring-boot:run

# Hoặc package và chạy jar
mvn clean package
java -jar target/btweb-0.0.1-SNAPSHOT.jar
```

### 5. Truy cập ứng dụng
Mở trình duyệt và truy cập: `http://localhost:8080`

## Hướng dẫn sử dụng

### Xem danh sách Category
- Truy cập trang chủ để xem danh sách category
- Sử dụng ô tìm kiếm để tìm theo tên
- Chọn số bản ghi hiển thị mỗi trang
- Nhấn vào tiêu đề cột để sắp xếp

### Thêm Category mới
1. Nhấn nút "Thêm Category"
2. Nhập tên category (bắt buộc, 2-255 ký tự)
3. Chọn icon (tùy chọn)
4. Nhấn "Thêm mới"

### Chỉnh sửa Category
1. Nhấn nút "Chỉnh sửa" trên hàng category
2. Cập nhật thông tin
3. Nhấn "Cập nhật"

### Xem chi tiết Category
- Nhấn nút "Xem" để xem thông tin chi tiết
- Hiển thị đầy đủ thông tin và thời gian tạo/cập nhật

### Xóa Category
1. Nhấn nút "Xóa" trên hàng category
2. Xác nhận trong dialog popup
3. Category sẽ bị xóa vĩnh viễn

## Cấu trúc Database

### Bảng Category
| Cột | Kiểu dữ liệu | Mô tả |
|-----|-------------|-------|
| cate_id | INT (PK, AI) | ID tự động tăng |
| cate_name | VARCHAR(255) | Tên category (duy nhất) |
| icon_path | VARCHAR(1000) | Đường dẫn file icon |
| icon_filename | VARCHAR(255) | Tên file icon |
| created_at | TIMESTAMP | Thời gian tạo |
| updated_at | TIMESTAMP | Thời gian cập nhật |

## Tính năng nổi bật

### 🎨 Giao diện đẹp mắt
- Gradient background header
- Card-based layout
- Hover effects
- Responsive design
- Font Awesome icons

### 🔍 Tìm kiếm thông minh
- Tìm kiếm không phân biệt hoa thường
- Tìm kiếm theo từ khóa trong tên
- Kết hợp với sắp xếp và phân trang

### 📱 Responsive Design
- Tương thích mobile, tablet, desktop
- Bootstrap 5 responsive grid
- Touch-friendly interface

### 🖼️ Upload File
- Drag & drop support
- Image preview
- File validation (type, size)
- Error handling

### ⚡ Performance
- JPA pagination
- Database indexing
- Optimized queries
- Lazy loading

## Thông tin liên hệ

**Email**: tranphuctoan@student.hcmute.edu.vn  
**GitHub**: [Profile GitHub]  
**LinkedIn**: [Profile LinkedIn]  

---

*© 2025 Trần Phúc Toàn - 23110344*
