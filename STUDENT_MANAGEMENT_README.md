# Hệ Thống Quản Lý Đăng Ký Học Phần Sinh Viên

## Mô tả
Chương trình quản lý đăng ký học phần của sinh viên theo từng kỳ học, sử dụng Java với mô hình lập trình hướng đối tượng (OOP) và ArrayList.

## Thông tin kỹ thuật
- **Ngôn ngữ:** Java (Core Java)
- **Số dòng code:** ~385 LOC
- **Cấu trúc:** OOP với 2 class chính:
  - `Student.java`: Lớp đại diện cho thông tin đăng ký của sinh viên
  - `StudentManagement.java`: Lớp quản lý hệ thống

## Cách biên dịch

```bash
# Tạo thư mục bin nếu chưa có
mkdir -p bin

# Biên dịch
javac -d bin src/main/java/com/learn/Student.java src/main/java/com/learn/StudentManagement.java
```

## Cách chạy

```bash
# Chạy từ thư mục gốc của project
java -cp bin com.learn.StudentManagement
```

## Chức năng chính

### 1. Đăng ký học phần từng sinh viên
- Cho phép thêm sinh viên đăng ký khóa học
- Yêu cầu nhập: Mã SV, Tên SV, Học kỳ, Khóa học (Java/.Net/C/C++)
- Phải đăng ký tối thiểu 10 sinh viên
- Sau 10 sinh viên, hỏi có muốn tiếp tục (Y/N)
- **Quy tắc:** Sinh viên có thể đăng ký nhiều khóa học nhưng không được trùng khóa học trong cùng một kỳ

**Ví dụ hợp lệ:**
- FX00001 - Java - Fall2022 ✓
- FX00001 - Java - Spring2023 ✓
- FX00001 - .Net - Fall2022 ✓

**Ví dụ không hợp lệ:**
- FX00001 - Java - Fall2022 ✓
- FX00001 - Java - Fall2022 ✗ (Trùng)

### 2. Tìm kiếm và Sắp xếp
- Tìm sinh viên theo tên (có thể tìm một phần của tên)
- Tự động sắp xếp kết quả theo tên
- Hiển thị: Tên sinh viên, Học kỳ, Khóa học

### 3. Cập nhật/Xóa
- Tìm sinh viên theo Mã sinh viên (ID)
- Cho phép chọn cập nhật (U) hoặc xóa (D)
- Nếu sinh viên có nhiều đăng ký, hiển thị danh sách để chọn

### 4. Báo cáo
- Hiển thị tổng hợp theo sinh viên
- Thông tin: Mã SV | Tên SV | Các khóa học | Tổng số khóa học
- Tự động đếm số khóa học duy nhất mỗi sinh viên đã đăng ký

**Ví dụ báo cáo:**
```
Ma SV        | Ten SV                    | Cac khoa hoc             | Tong so
--------------------------------------------------------------------------------
FX00001      | Nguyen Van A              | Java, .Net               | 2
FX00002      | Nguyen Van B              | .Net                     | 1
FX00003      | Nguyen Van C              | Java                     | 1
```

### 5. Thoát
- Thoát khỏi chương trình

## Đặc điểm kỹ thuật

### Kiến trúc OOP
- **Student class:** Đại diện cho một bản ghi đăng ký
  - Thuộc tính: ordinalNumber, id, studentName, semester, courseName
  - Phương thức: getters, setters, toString()

- **StudentManagement class:** Quản lý toàn bộ hệ thống
  - Sử dụng ArrayList để lưu trữ danh sách sinh viên
  - Sử dụng Collections.sort() với Comparator để sắp xếp
  - Validation cho đăng ký trùng lặp

### Cấu trúc dữ liệu
- **ArrayList<Student>:** Lưu trữ tất cả các bản ghi đăng ký
- **HashMap:** Sử dụng trong báo cáo để tổng hợp dữ liệu
- **HashSet:** Đếm số khóa học duy nhất của mỗi sinh viên

### Validation
- Kiểm tra đăng ký trùng lặp (cùng sinh viên, cùng khóa học, cùng kỳ)
- Validate input số nguyên
- Validate lựa chọn khóa học (chỉ 3 khóa: Java, .Net, C/C++)

## Hướng dẫn sử dụng

1. Chạy chương trình
2. Chọn chức năng từ menu (1-5)
3. Làm theo hướng dẫn trên màn hình
4. Nhập dữ liệu khi được yêu cầu

## Lưu ý
- Dữ liệu chỉ lưu trong RAM, sẽ mất khi thoát chương trình
- Nhập Enter để giữ nguyên giá trị khi cập nhật
- Tên khóa học phải chính xác: Java, .Net, hoặc C/C++
- Mã sinh viên nên theo format: FXxxxxx (ví dụ: FX00001)

## Ví dụ sử dụng

### Đăng ký sinh viên mẫu
```
Nhap ma sinh vien: FX00001
Nhap ten sinh vien: Nguyen Van A
Nhap hoc ky: Fall2022
Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++
Chon khoa hoc (1-3): 1
```

### Tìm kiếm
```
Nhap ten sinh vien can tim: Nguyen
(Hệ thống sẽ tìm tất cả sinh viên có tên chứa "Nguyen")
```

## Tác giả
Phát triển theo yêu cầu bài tập Java - Quản lý đăng ký học phần sinh viên
