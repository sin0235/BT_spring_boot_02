# Category Management System - CRUD với Thymeleaf

## Thông tin sinh viên
- **Họ tên:** Trần Phúc Toàn
- **MSSV:** 23110344
- **Trường:** Đại học Nông Lâm TP.HCM

## Mô tả dự án
Hệ thống quản lý Category với đầy đủ chức năng CRUD (Create, Read, Update, Delete), tìm kiếm và phân trang sử dụng Spring Boot và Thymeleaf.

## Tính năng chính

### 1. Quản lý Category (CRUD)
- ✅ **Thêm mới Category**: Form tạo category với validation
- ✅ **Xem danh sách**: Hiển thị tất cả categories với phân trang
- ✅ **Xem chi tiết**: Xem thông tin chi tiết của từng category
- ✅ **Chỉnh sửa**: Cập nhật thông tin category
- ✅ **Xóa**: Xóa category với xác nhận

### 2. Tìm kiếm và Phân trang
- ✅ **Tìm kiếm**: Tìm kiếm category theo tên (không phân biệt hoa thường)
- ✅ **Phân trang**: Chia nhỏ dữ liệu với navigation
- ✅ **Sắp xếp**: Sắp xếp theo ID, tên, ngày tạo, ngày cập nhật
- ✅ **Điều chỉnh số lượng**: 5, 10, 20, 50 items/trang

### 3. Upload và quản lý hình ảnh
- ✅ **Upload file**: Hỗ trợ JPG, PNG, GIF
- ✅ **Validation**: Kiểm tra kích thước và định dạng file
- ✅ **Preview**: Xem trước hình ảnh trước khi lưu
- ✅ **Quản lý**: Xem, tải xuống hình ảnh

### 4. Giao diện người dùng
- ✅ **Layout responsive**: Sử dụng Bootstrap 5
- ✅ **Header**: Hiển thị avatar người dùng và navigation
- ✅ **Footer**: Thông tin sinh viên thực hiện
- ✅ **Thông báo**: Flash messages cho các thao tác
- ✅ **Validation**: Form validation real-time

## Cấu trúc file

### Controller
```
src/main/java/vn/iotstar/controller/
├── CategoryController.java      # Main CRUD controller
├── IndexController.java         # Home page controller
└── ...
```

### Templates Thymeleaf
```
src/main/resources/templates/
├── layout/
│   └── main.html               # Layout chính với header/footer
├── categories/
│   ├── list.html              # Danh sách categories + phân trang
│   ├── form.html              # Form thêm/sửa category
│   └── view.html              # Chi tiết category
└── index.html                 # Trang chủ
```

### Entity & Repository
```
src/main/java/vn/iotstar/
├── entity/Category.java        # Entity với validation
├── repository/CategoryRepository.java  # Repository với phân trang
└── service/CategoryService.java        # Business logic
```

## Các endpoint chính

| Method | URL | Mô tả |
|--------|-----|--------|
| GET | `/` | Trang chủ |
| GET | `/categories` | Danh sách categories (có phân trang, tìm kiếm) |
| GET | `/categories/new` | Form thêm category mới |
| GET | `/categories/view/{id}` | Xem chi tiết category |
| GET | `/categories/edit/{id}` | Form chỉnh sửa category |
| POST | `/categories/save` | Lưu category (thêm/sửa) |
| POST | `/categories/delete/{id}` | Xóa category |

## Tham số URL cho phân trang và tìm kiếm

```
/categories?page=0&size=10&sortBy=catename&sortDir=asc&keyword=example
```

- `page`: Số trang (bắt đầu từ 0)
- `size`: Số items/trang (5, 10, 20, 50)
- `sortBy`: Trường sắp xếp (cateid, catename, createdAt, updatedAt)
- `sortDir`: Hướng sắp xếp (asc, desc)
- `keyword`: Từ khóa tìm kiếm

## Validation rules

### Category Name
- **Bắt buộc**: Không được để trống
- **Độ dài**: 2-255 ký tự
- **Duy nhất**: Không trùng tên với category khác

### Upload File
- **Định dạng**: JPG, PNG, GIF
- **Kích thước**: Tối đa 5MB
- **Tùy chọn**: Không bắt buộc

## Cách chạy dự án

1. **Clone repository**
```bash
git clone <repository-url>
cd BT_spring_boot
```

2. **Cài đặt dependencies**
```bash
mvn clean install
```

3. **Chạy ứng dụng**
```bash
mvn spring-boot:run
```

4. **Truy cập ứng dụng**
- URL: http://localhost:8080
- Đăng nhập với tài khoản có sẵn

## Công nghệ sử dụng

- **Backend**: Spring Boot 3.x
- **Frontend**: Thymeleaf + Bootstrap 5
- **Database**: JPA/Hibernate
- **Validation**: Spring Validation
- **File Upload**: Spring MultipartFile
- **Icons**: Font Awesome 6

## Screenshots

### 1. Danh sách Categories
- Hiển thị bảng với phân trang
- Thanh tìm kiếm và filter
- Buttons thao tác (View, Edit, Delete)

### 2. Form thêm/sửa Category
- Form validation real-time
- Upload và preview hình ảnh
- Responsive design

### 3. Chi tiết Category
- Hiển thị đầy đủ thông tin
- Modal xem hình ảnh lớn
- Quick stats và actions

## Đặc điểm nổi bật

1. **Responsive Design**: Tương thích mọi thiết bị
2. **User Experience**: Giao diện thân thiện, dễ sử dụng
3. **Performance**: Phân trang tối ưu cho dữ liệu lớn
4. **Security**: Validation cả frontend và backend
5. **Accessibility**: Tuân thủ chuẩn accessibility

## Liên hệ
- **Email**: [email sinh viên]
- **GitHub**: [link GitHub profile]

---
*Dự án được phát triển như một phần của khóa học Spring Boot tại Đại học Nông Lâm TP.HCM*
