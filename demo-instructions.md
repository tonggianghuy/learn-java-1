# Hướng Dẫn Chạy và Demo Chương Trình

## Cách Chạy

### Option 1: Sử dụng Script
```bash
./run-student-management.sh
```

### Option 2: Chạy Thủ Công
```bash
# Biên dịch
mkdir -p bin
javac -d bin src/main/java/com/learn/Student.java src/main/java/com/learn/StudentManagement.java

# Chạy
java -cp bin com.learn.StudentManagement
```

## Demo Sử Dụng

### Bước 1: Đăng Ký Sinh Viên (Option 1)

Khi chương trình khởi động, chọn `1` để đăng ký sinh viên:

```
Nhap lua chon cua ban: 1

--- Sinh vien thu 1 ---
Nhap ma sinh vien: FX00001
Nhap ten sinh vien: Nguyen Van A
Nhap hoc ky (VD: Fall2022, Spring2023): Fall2022
Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++
Chon khoa hoc (1-3): 1
Dang ky thanh cong!
```

Tiếp tục đăng ký thêm sinh viên:

```
--- Sinh vien thu 2 ---
Nhap ma sinh vien: FX00002
Nhap ten sinh vien: Nguyen Van B
Nhap hoc ky: Fall2022
Chon khoa hoc (1-3): 2
Dang ky thanh cong!
```

**Lưu ý:** Phải đăng ký ít nhất 10 sinh viên. Sau 10 sinh viên, chương trình sẽ hỏi:
```
Ban da dang ky 10 sinh vien. Ban co muon tiep tuc? (Y/N): N
```

### Bước 2: Xem Báo Cáo (Option 4)

Sau khi đăng ký, chọn `4` để xem báo cáo:

```
Nhap lua chon cua ban: 4

--- BAO CAO ---
--------------------------------------------------------------------------------
Ma SV        | Ten SV                    | Cac khoa hoc             | Tong so
--------------------------------------------------------------------------------
FX00001      | Nguyen Van A              | Java                     | 1
FX00002      | Nguyen Van B              | .Net                     | 1
```

### Bước 3: Tìm Kiếm và Sắp Xếp (Option 2)

Chọn `2` để tìm kiếm sinh viên theo tên:

```
Nhap lua chon cua ban: 2

--- TIM KIEM VA SAP XEP ---
Nhap ten sinh vien can tim (co the nhap mot phan): Nguyen

Ket qua tim kiem (da sap xep theo ten):
--------------------------------------------------------------------------------
Ten sinh vien             | Hoc ky          | Khoa hoc
--------------------------------------------------------------------------------
Nguyen Van A              | Fall2022        | Java
Nguyen Van B              | Fall2022        | .Net
```

### Bước 4: Cập Nhật/Xóa (Option 3)

Chọn `3` để cập nhật hoặc xóa thông tin sinh viên:

```
Nhap lua chon cua ban: 3

--- CAP NHAT/XOA SINH VIEN ---
Nhap ma sinh vien can tim: FX00001

Danh sach cac ban ghi cua sinh vien FX00001:
------------------------------------------------------------------------------------------
STT   | Ma SV      | Ten SV                    | Hoc ky          | Khoa hoc
------------------------------------------------------------------------------------------
1     | FX00001    | Nguyen Van A              | Fall2022        | Java

Chon so thu tu ban ghi muon cap nhat/xoa: 1
Ban muon Cap nhat (U) hay Xoa (D)? U

--- CAP NHAT THONG TIN ---
Nhan Enter de giu nguyen thong tin hien tai
Ten moi (Nguyen Van A): Nguyen Van An
Hoc ky moi (Fall2022): [Enter]
Khoa hoc hien tai: Java
Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++, 0. Giu nguyen
Chon khoa hoc moi: 0
Cap nhat thanh cong!
```

### Bước 5: Thoát (Option 5)

Chọn `5` để thoát chương trình:

```
Nhap lua chon cua ban: 5

Cam on ban da su dung chuong trinh!
```

## Dữ Liệu Mẫu Để Test

Dưới đây là 10 sinh viên mẫu bạn có thể dùng để test:

1. FX00001 - Nguyen Van A - Fall2022 - Java
2. FX00002 - Nguyen Van B - Fall2022 - .Net
3. FX00003 - Nguyen Van C - Spring2023 - Java
4. FX00001 - Nguyen Van A - Spring2023 - Java (cùng sinh viên, khác kỳ - OK)
5. FX00004 - Tran Thi D - Fall2022 - C/C++
6. FX00005 - Le Van E - Summer2023 - Java
7. FX00006 - Pham Thi F - Fall2022 - .Net
8. FX00007 - Hoang Van G - Spring2023 - C/C++
9. FX00008 - Vo Thi H - Fall2022 - Java
10. FX00009 - Dang Van I - Summer2023 - .Net

## Test Case Đặc Biệt

### Test Validation: Đăng Ký Trùng

Thử đăng ký sinh viên FX00001 với Java và Fall2022 lần thứ 2:

```
Nhap ma sinh vien: FX00001
Nhap ten sinh vien: Nguyen Van A
Nhap hoc ky: Fall2022
Chon khoa hoc (1-3): 1

Sinh vien FX00001 da dang ky khoa hoc Java trong ky Fall2022 roi!
Khong the dang ky trung khoa hoc trong cung mot ky!
```

### Test Tìm Kiếm Một Phần

Nhập "Van" để tìm tất cả sinh viên có tên chứa "Van":
```
Nhap ten sinh vien can tim: Van

Ket qua tim kiem:
- Nguyen Van A
- Nguyen Van B
- Nguyen Van C
- Le Van E
...
```

## Báo Cáo Sau Khi Test

Sau khi đăng ký đầy đủ 10 sinh viên ở trên, báo cáo sẽ hiển thị:

```
Ma SV        | Ten SV                    | Cac khoa hoc             | Tong so
--------------------------------------------------------------------------------
FX00001      | Nguyen Van A              | Java                     | 2
FX00002      | Nguyen Van B              | .Net                     | 1
FX00003      | Nguyen Van C              | Java                     | 1
FX00004      | Tran Thi D                | C/C++                    | 1
...
```

**Chú ý:** FX00001 có tổng số = 2 vì đăng ký 2 lần (Fall2022 và Spring2023)
