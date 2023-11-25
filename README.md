# WWWLab02

# Mô Tả Ứng Dụng

Ứng dụng web bán hàng này được xây dựng để quản lý thông tin về nhân viên, sản phẩm, khách hàng, đơn đặt hàng và các chi tiết liên quan. Để tối ưu hóa trải nghiệm người dùng, ứng dụng cung cấp REST API để thực hiện các thao tác CRUD cho các đối tượng và cung cấp các thao tác thống kê đơn đặt hàng. Cụ thể, các thao tác thống kê bao gồm thống kê theo từng ngày, thống kê trong một khoảng thời gian và thống kê theo nhân viên trong một khoảng thời gian.

Ở phía front-end, chúng tôi đã hiện thực giao diện người dùng để tương tác với các REST API. Giao diện người dùng này không chỉ đơn giản là một cổng để thực hiện các thao tác CRUD mà còn bao gồm các chức năng phức tạp như:

Vẽ Biểu Đồ Giá: Hiển thị biểu đồ giá theo thời gian để cung cấp cái nhìn trực quan về biến động giá của sản phẩm.

Tạo Giỏ Hàng: Cho phép người dùng tạo giỏ hàng và quản lý các sản phẩm đã chọn.

Hiển Thị Danh Sách Sản Phẩm: Thực hiện hiển thị danh sách sản phẩm, bao gồm thông tin chi tiết và giá.

Thêm Sản Phẩm Vào Giỏ: Đơn giản hóa quá trình thêm sản phẩm vào giỏ hàng từ danh sách sản phẩm.

Sắp Xếp Theo Giá: Cho phép người dùng sắp xếp danh sách sản phẩm theo giá để dễ dàng tìm kiếm và so sánh.

Tìm Kiếm Sản Phẩm: Cung cấp chức năng tìm kiếm để giúp người dùng nhanh chóng tìm được sản phẩm mong muốn.

Giả Lập Thanh Toán Qua Thẻ: Tích hợp chức năng giả lập thanh toán qua thẻ để người dùng có thể trải nghiệm quy trình thanh toán một cách thực tế.
# Kiến Trúc Ứng Dụng

## Ứng dụng được xây dựng trên kiến trúc 3-tiers:

Ứng dụng được xây dựng dựa trên kiến trúc 3-tiers với các gói chính như sau:

- **Data Access Layer (`repositories`):** Chứa các lớp truy xuất cơ sở dữ liệu sử dụng JPA.

- **Business Logic Layer (`services`):** Chứa các lớp xử lý nghiệp vụ của ứng dụng.

- **Presentation Layer (`resources`):** Cung cấp REST API cho phần front-end.

Phần front end được tích hợp sẵn vào project để cho thuận tiện (dùng HTML,JS,CSS thuần để FETCH API để xử lý dữ liệu ) và được chi thành cấu trúc như sau : 
![img_3.png](image/img_3.png)

## Cài Đặt và Triển Khai

## Cài đặt

Để chạy web, bạn cần có môi trường sau:

- MariaDB
- Một máy chủ web (ví dụ: Apac Tomcat).
- Môi trường java JDK 17

### Cài đặt MariaDB

1. Tải và cài đặt MariaDB hoặc MySQL từ [https://mariadb.org/download/](https://mariadb.org/download/).

2. Đăng nhập vào MariaDBbằng tài khoản root và tạo một tài khoản với tên "sa" và mật khẩu "sapsword". Sử dụng các lệnh
   SQL sau:
   CREATE USER 'sa'@'localhost' IDENTIFIED BY 'sapsword';
   GRANT ALL PRIVILEGES ON . TO 'sa'@'localhost' WITH GRANT OPTION;
   FLUSH PRIVILEGES.

### Cài đặt Apache Tomcat

1. Đảm bảo bạn đã cài đặt máy chủ ứng dụng servlet như Apache Tomcat trên máy chủ web của bạn.Có thể tại ở
   đây (https://tomcat.apache.org/download-10.cgi)

2. Triển khai mã nguồn của ứng dụng lên máy chủ ứng dụng servlet. Đảm bảo tệp WAR của ứng dụng được đặt trong thư mục
   webapps của Apache Tomcat..Xem hướng
   đẫn (https://www.jetbrains.com/idea/guide/tutorials/working-with-apache-tomcat/using-existing-application/)

### Cài đặt Java 17

1. Cài đặt môi trường java cho ứng dụng (Đề nghị dùng JDK 17),Có thể tải ở
   đây (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

2. Setup môi trường java.(https://www3.cs.stonybrook.edu/~amione/CSE114_Course/materials/resources/InstallingJava17.pdf)

### Start Project

1. **Triển Khai Ứng Dụng:**
    - Triển khai ứng dụng trên  Apache Tomcat.
    - Chỉnh sửa tệp `persistence.xml` trong gói `resources/META-INF` để định cấu hình kết nối với cơ sở dữ liệu.

2. **Setup tomcat để run Project:**
    - ![img.png](image/img.png)
    - ![img_1.png](image/img_1.png)
    - Nhấn nút start WEB sẽ hiển thị ![img_2.png](image/img_2.png)
    - Hoặc bạn có thể truy cập url "http://localhost:8080/"

### Cấu trúc 

1. **DATABASE**

   ![img_4.png](image/img_4.png)

2. **BACKEND**

   ![img_5.png](image/img_5.png)

3. **FRONTEND**

   ![img_6.png](image/img_6.png)

### Danh sách các API của ứng dụng 

![img_7.png](image/img_7.png) 
![img_8.png](image/img_8.png)


### Triển khai ứng dụng

1. Sau khi start project. UI sẽ được hiển thị ![img_9.png](image/img_9.png) 
2. Bạn chọn role mà bạn muốn thực hiện các thao tác. Có hai role là ADMIN và CUSTOMER
3. Giả sử bạn đang chọn role là CUSTOMER.
   ![img_10.png](image/img_10.png)
   UI sẽ hiển thị danh sách các sản phẩm đang còn được ACTIVE sẽ load từ API
   ![img_11.png](image/img_11.png)
4. Có thể sắp xếp sản phẩm theo giá của nó qua hai nút (SORT ASC,SORT DESC) 
   
   Tăng
   ![img_12.png](image/img_12.png)
   Giảm
   ![img_13.png](image/img_13.png)
5. Có thể thực hiện tìm kiếm sản phẩm qua ô Search 
   ![img_14.png](image/img_14.png)

6. Bạn có thể xem biểu đồ giá của sản phẩm khi nhấn vào nút VIEW PRICE CHART
   
   ![img_15.png](image/img_15.png)
   API get giá của sản phẩm trong tất cả khoản thời gian sẽ được gọi 
   ![img_22.png](image/img_22.png)

7. Nhấn vào nút ADD TO CART để thêm sản phẩm vào giỏ hàng, khi thêm thành công nó sẽ hiển thị một thông báo 

   ![img_16.png](image/img_16.png)
   
8. Bấm vào biểu tượng giỏ hàng để xem giỏ hàng của bạn 
   ![img_17.png](image/img_17.png)
9. Ở giỏ hàng bạn sẽ xem được tên sản phẩm cùng với giá tiền của sản phẩm bạn đã thêm vào giỏ hàng (VD: đã chọn 2 sản phẩm sam sung , Huawei)
   ![img_18.png](image/img_18.png)
10. Khi bạn muốn thanh toán các sản phẩm trong giỏ hàng bạn có thể bấm nút Check out để chuyển đến mục thanh toán ![img_20.png](image/img_20.png)

11. Sau khi xác nhận thông báo, bạn sẽ được chuyển đến trang UI giả lập thanh toán ![img_19.png](image/img_19.png)
12. Khi nhập đầy đủ thông tin và nhấn nút bạn sẽ nhận được một thông báo thanh toán thành công và được chuyển về trang ProductListing (UI đầu tiền khi bạn đăng nhập dưới quyền CUSTOMER) ![img_21.png](image/img_21.png)
13. Giả sử bạn đang đăng nhập dưới quyền ADMIN 
    ![img_23.png](image/img_23.png)
14. Ở đây bạn sẽ được lựa chọn chức năng như LIST(xem danh sách của các đối tượng:CUSTOMER,EMPLOYEE,PRODUCT,ORDER,...) và Statistics(thống kế by date , by range , by employee and range), nhấn nút HOME để quay về trang chính 
   ![img_24.png](image/img_24.png)
   ![img_25.png](image/img_25.png)

###  Thao tác trên list CUSTOMER

1. Danh sách các CUSTOMER sẽ được load lên qua api (/all)
   ![img_26.png](image/img_26.png)
2. Bấm vào nút ADD CUSTOMER. Bạn sẽ được chuyển qua trang Add 
   ![img_27.png](image/img_27.png)
3. Khi bạn nhập đầy đủ các field phù hợp,sẽ được nhận được một thông báo và được chuyển sang trang customerListing với customer vừa add vào (Gọi API method POST)
   ![img_30.png](image/img_30.png)
   ![img_29.png](image/img_29.png)
4. Bạn có thể nhấn vào phần update để có thể update một customer. Sau khi nhấn vào bạn sẽ được chuyển qua một trang Update với các thông tin phù hợp với customer 
   ![img_31.png](image/img_31.png)
5. Sau khi sửa đổi các thông tin theo yêu cầu của bạn và nhấn vào nút Update customer 
   ![img_32.png](image/img_32.png)
   Bạn sẽ được thông báo là update thành công ( gọi API method PUT để update customer) và được chuyển sang trang customerListing với customer vừa update vào  
6. Bạn có thể xóa một Customer bằng cách nhấn vào nút DELETE. UI sẽ hiện thị một thông báo 
   ![img_33.png](image/img_33.png)
7. Khi bạn xác nhận xóa . UI sẽ thông báo xóa thành công (gọi API method DELETE để xóa customer) và list Customer đã được cập nhật lại
   ![img_34.png](image/img_34.png)
   ![img_35.png](image/img_35.png)
8. Nhấn nút Back để quay lại trang ADMIN

###  Thao tác trên list EMPLOYEE

1. Danh sách các EMPLOYEE sẽ được load lên qua api (/all)
   ![img_36.png](image/img_36.png)
2. Bấm vào nút ADD EMPLOYEE. Bạn sẽ được chuyển qua trang Add 
   ![img_37.png](image/img_37.png)
3. Khi bạn nhập đầy đủ các field phù hợp,sẽ được nhận được một thông báo và được chuyển sang trang employeeListing với employee vừa add vào 
   ![img_38.png](image/img_38.png)
   ![img_39.png](image/img_39.png)
4. Bạn có thể nhấn vào phần update để có thể update một employee. Sau khi nhấn vào bạn sẽ được chuyển qua một trang Update với các thông tin phù hợp với employee 
   ![img_40.png](image/img_40.png)
5. Sau khi sửa đổi các thông tin theo yêu cầu của bạn và nhấn vào nút Update employee
   ![img_41.png](image/img_41.png)
   ![img_42.png](image/img_42.png)
6. Bạn có thể xóa một Employee bằng cách nhấn vào nút DELETE. UI sẽ hiện thị một thông báo 
   ![img_43.png](image/img_43.png)
7. Khi bạn xác nhận xóa . UI sẽ thông báo xóa thành công (gọi API method DELETE để xóa employee) và list employee đã được cập nhật lại
   ![img_44.png](image/img_44.png)
   ![img_45.png](image/img_45.png)
8. Dữ liệu ở List sẽ được cập nhật lại và Employee đã xóa sẽ được chuyên trạng thành thành DELETE
   ![img_46.png](image/img_46.png)
9. Nhấn nút Back để quay lại trang ADMIN

###  Thao tác trên list Product 
1. Danh sách các Product sẽ được load lên qua api (/all)
   ![img_47.png](image/img_47.png)
2. Bấm vào nút ADD PRODUCT. Bạn sẽ được chuyển qua trang Add
   ![img_48.png](image/img_48.png)
3. Khi bạn nhập đầy đủ các field phù hợp,sẽ được nhận được một thông báo và được chuyển sang trang productListing với product vừa add vào 
   ![img_49.png](image/img_49.png)
   ![img_50.png](image/img_50.png)
4. Bạn có thể nhấn vào phần update để có thể update một product. Sau khi nhấn vào bạn sẽ được chuyển qua một trang Update với các thông tin phù hợp với product
   ![img_54.png](image/img_54.png)
5. Sau khi sửa đổi các thông tin theo yêu cầu của bạn và nhấn vào nút Update employee
   ![img_52.png](image/img_52.png)
   Bạn sẽ được thông báo là update thành công ( gọi API method PUT để update product) và được chuyển sang trang productListing với product vừa product vào
   ![img_53.png](image/img_53.png)
6. Bạn có thể xóa một Product bằng cách nhấn vào nút DELETE. UI sẽ hiện thị một thông báo 
   ![img_55.png](image/img_55.png)
7. Khi bạn xác nhận xóa . UI sẽ thông báo xóa thành công (gọi API method DELETE để xóa product) và list product đã được cập nhật lại
   ![img_56.png](image/img_56.png)
8. Dữ liệu ở List sẽ được cập nhật lại và Product đã xóa sẽ được chuyên trạng thành thành DELETE
   ![img_57.png](image/img_57.png)
9. Bạn có thể xem chi tiết của một product qua việc nhấn vào nút Details 
   ![img_58.png](image/img_58.png)
   Các thông tin về sản phẩm sẽ được get lên từ API và hiển thị lên (API method get để get được Price và Image)
   ![img_59.png](image/img_59.png)
   ![img_60.png](image/img_60.png)
   ![img_61.png](image/img_61.png)
10. Bạn có thể add giá mới cho sản phẩm bằng nút Add price.Bạn sẽ chuyển đến trang addPrice
   ![img_62.png](image/img_62.png)
   Sau khi điền vào các field(Thời gian addPrice sẽ được gán cứng là thời gian hiện tại).Bạn sẽ nhận được thông báo add thành công (gọi API method POST để addPrice mới cho product)
   ![img_63.png](image/img_63.png)
11. Sau khi bạn bấm xác nhận thì sẽ được chuyển đến trang Product Details của sản phẩm bạn add giá (Hiển thị giá đã được add mới )
   ![img_64.png](image/img_64.png)
12. Nhấn nút Back để quay lại trang ADMIN

###  Thao tác trên list Orders
1. Danh sách các Orders sẽ được load lên qua api (/info)
   ![img_65.png](image/img_65.png)
2. Bấm vào nút ADD Orders. Bạn sẽ được chuyển qua trang Add(Trang này được giả lập như 1 giỏ hàng)
   ![img_67.png](image/img_67.png)
3. Các input trong đây được tạo từ datalist (dữ liệu mỗi input get từ 1 API riêng)
   ![img_68.png](image/img_68.png)
4. Sau khi nhập đầy đủ các field và bấm nút Submit Order bạn sẽ được thông báo Order thành công 
   ![img_69.png](image/img_69.png)
5. Sau khi reload lại BackEnd Order vừa add sẽ được cập nhật vào ListOrder(tương lai sẽ dùng webSocket để tự dộng cập nhật mà không cần reload)
   ![img_70.png](image/img_70.png)
6. Bạn có thể xem chi tiết của một Order qua nút Details(test trên sản phẩm vừa add được trên giỏ hàng)
   ![img_71.png](image/img_71.png)
7. Bạn có thể xóa sản phẩm qua nút Delete 
   ![img_73.png](image/img_73.png)
   UI sẽ thông báo xóa thành công (gọi API method DELETE để xóa order) và list order đã được cập nhật lại(set Note lạo thành DELETED)
   ![img_74.png](image/img_74.png)
8. Nhấn nút Back để quay lại trang ADMIN

### Thao tác trên Statistics 

1. Khi bạn nhấn vào by Date, UI sẽ chuyển bạn đến trang thống kế theo ngày và hiển thị tất cả các ngày có order được tạo 
   ![img_75.png](image/img_75.png)

   (API method get statisticsByDate được gọi để lấy được thống kê các order theo ngày , tổng tiền được tính bằng giá * số lượng )
   ![img_76.png](image/img_76.png)

   Nhấn nút Back để quay lại
2. Khi bạn nhấn vào by Range, UI sẽ chuyển bạn đến trang thống kê theo khoản thời gian bạn chọn 
   ![img_77.png](image/img_77.png)
   
   Sau khi chọn đủ 2 khoản thời gian , và bấm nút get statistics . UI sẽ hiển thị ra thống kê các order được tạo trong khoảng thời gian này 
   ![img_78.png](image/img_78.png)
   (API method get statisticsByDateRange với hai tham số được gọi để lấy dữ liệu thống kê theo khoản thời gian được chọn )
   ![img_79.png](image/img_79.png) 
   ![img_80.png](image/img_80.png)
   
   Nhấn nút Back để quay lại

3. Khi bạn nhấn vào by Emp, UI sẽ chuyển bạn đến trang thống kê theo nhân viên với khoản thời gian bạn chọn
   
   ![img_81.png](image/img_81.png)

   Dữ liệu trong ô input được load từ dataList thuận tiện cho việc tìm kiếm (DATA được get từ API )

   Khi bạn nhập đầy đủ thông tin cần thiết cho việc thống kê bạn và bấm nút get Statistic. UI sẽ hiển thị thống các order được tạo ra bởi employee trong một khoảng thời gian đã được chọn
   ![img_82.png](image/img_82.png)
   (API method get statisticsByEmployeeAndDateRange với ba tham số được gọi để lấy dữ liệu thông kê theo một employee theo khoảng thời gian đã được chọn)
   
   ![img.png](img.png)
   ![img_83.png](image/img_83.png)
   Nhấn nút Back để quay lại






   
