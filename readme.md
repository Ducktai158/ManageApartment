# Ứng dụng quản lý thu phí dịch vụ phòng trọ

Là ứng dụng sử dụng để thêm, sửa, xóa và thống kê người thuê phòng trọ cùng với chi phí dịch vụ


## 🧐 Tính năng
- Tra cứu và tìm kiếm các chủ hộ
- Thêm chủ hộ
- Sửa thông tin chủ hộ 
- Xóa chủ hộ khỏi danh sách chủ hộ (phòng trọ có thể: chủ hộ ở, chủ hộ cho thuê, không ai ở)

- Tra cứu và tìm kiếm người thuê phòng trọ
- Thêm người thuê
- Sửa thông tin người thuê
- Xóa người thuê khỏi danh sách người thuê

Tại tab thu tiền phòng:
- Chỉnh sửa các giá trị phí dịch vụ (tiền phòng theo loại phòng, tiền điện, tiền vệ sinh, tiền nước, tiền bảo dưỡng, số lượng xe theo loại để tính tiền thuê xe)
- Chỉnh sửa trạng thái nộp tiền (Đã nộp/Chưa nộp)
- Thống kê doanh thu từ các phòng đã nộp.
- Xuất file excel thông tin về thu tiền phí dịch vụ phòng trọ
- Lưu ý: (có thể tuỳ chỉnh cho phù hợp với mô hình kinh doanh)
+ Tiền phòng: Loại 1: 500.000đ, Loại 2: 1.000.000đ, Loại 3: 2.000.000đ, Loại 4: 3.000.000đ
+ Tiền thuê xe: Xe ô tô: 10.000đ, Xe máy: 5.000đ, Xe đạp: 2.000đ

## 🛠️ Cài đặt

#### Cài đặt môi trường

* Cài đặt [jdk 21](https://www.oracle.com/java/technologies/downloads/#java21)

* Cài đặt [Apache Netbeans 21](https://netbeans.apache.org/front/main/download/nb21/)


 #### Tải mã nguồn project

* Link mã nguồn [ManageApartment](https://github.com/Ducktai158/ManageApartment)



#### Cài đặt project

Chạy với mã nguồn

* Mở project trong Apache Netbeans 21

* Chạy ứng dụng bằng cách chạy file ManageApartment.java

Chạy với file jar

```bash
  java -jar ManageApartment-1.0-SNAPSHOT-jar-with-dependencies.jar
```
## 💻 Demo

#### Màn hình đăng nhập

![Login](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/LoginFrame.png?raw=true)

- Tài khoản đăng nhập mặc định cho ứng dụng là "admin" và mật khẩu là "admin"
- Nếu nhập sai tài khoản mật khẩu, ứng dụng sẽ đưa ra thông báo tài khoản mật khẩu bị sai

![LoginFalse](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/LoginFalse.png?raw=true)

- Sau khi đăng nhập thành công, ứng dụng sẽ chuyển vào màn hình quản lý thông tin chủ hộ

#### Màn hình quản lý thông tin chủ hộ

![ChuHo](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/ChuHo.png?raw=true)

- Màn hình Quản lí thông tin chủ hộ gồm các chức năng tìm kiếm, sắp xếp và hiển thị các chủ hộ

![ChuHo2](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/ChuHo2.png?raw=true)

- Ví dụ khi tìm chủ hộ có tên là "San Nha", ngoài ra có thể tìm kiếm theo mã phòng:

![SearchChuHo](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/SearchChuHo.png?raw=true)

- Ví dụ khi sắp xếp các chủ hộ theo Tên từ A-Z, ở đây có thể sắp xếp theo các tiêu chí khác nhau như Tên từ Z-A, Trả phí nhiều nhất, Trả phí ít nhất:

![SortChuHo](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/SortChuHo.png?raw=true)

- Màn hình Quản lý người thuê cũng có các chức năng tương tự quản lý chủ hộ:

![Thue](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/Thue.png?raw=true)

#### Màn hình quản lý thu phí dịch vụ

![ThuTien](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/ThuTien.png?raw=true)

- Ví dụ chỉnh sửa các giá trị tiền phải thu từ các phòng:

![EditThuTien](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/EditThuTien.png?raw=true)

- Ví dụ về thống kê doanh thu với ví dụ trên bằng cách nhân vào nút "Thống kê" (vì mới có 2 phòng đã nộp nên có doanh thu từ 2 phòng đó):

![ThongKe](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/ThongKe.png?raw=true)

- Ví dụ xuất thông tin bằng cách nhấn vào nút "Xuất thông tin":

Sau khi nhấn nút "Xuất thông tin":

![InThongTin](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/InThongTin.png?raw=true)

File excel thông tin hiện ở thư mục project:

![FileThongTin](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/FileThongTin.png?raw=true)

Mở file excel thông tin để kiểm tra:

![FileThongTin2](https://github.com/Ducktai158/ManageApartment/blob/master/readmeImage/FileThongTin2.png?raw=true)


