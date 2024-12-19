document.getElementById("employee-form").addEventListener("submit", function (e) {
    e.preventDefault();

    // Lấy dữ liệu từ form
    const name = document.getElementById("employee-name").value;
    const phone = document.getElementById("employee-phone").value;
    const email = document.getElementById("employee-email").value;
    const status = document.getElementById("employee-status").value;

    // Trạng thái text và màu
    const statusText = {
      success: "ĐANG HOẠT ĐỘNG",
      warning: "TẠM NGHỈ",
      danger: "ĐÃ NGHỈ VIỆC",
    };

    const table = document.querySelector("#employee-table tbody");
    const newRow = `
  <tr>
    <td>${table.rows.length + 1}</td>
    <td>${name}</td>
    <td>${phone}</td>
    <td>${email}</td>
    <td><span class="badge badge-${status}">${statusText[status]}</span></td>
    <td class="align-middle text-center">
      <div class="action-buttons">
        <button class="btn-icon btn-info" title="Chi tiết">
          <i class="material-symbols-rounded">visibility</i>
        </button>
        <button class="btn-icon btn-danger" title="Xóa">
          <i class="material-symbols-rounded">delete</i>
        </button>
      </div>
    </td>
  </tr>
`;

    table.insertAdjacentHTML("beforeend", newRow);

    // Đóng modal và reset form
    bootstrap.Modal.getInstance(document.getElementById("addEmployeeModal")).hide();
    e.target.reset();
  });

  // Xử lý nút Chi Tiết và Xóa
  document.addEventListener("click", function (e) {
    const row = e.target.closest("tr");
    if (e.target.closest(".btn-info")) {
      document.getElementById("detail-name").innerText = row.children[1].innerText;
      document.getElementById("detail-phone").innerText = row.children[2].innerText;
      document.getElementById("detail-email").innerText = row.children[3].innerText;
      document.getElementById("detail-status").innerText = row.children[4].innerText;
      new bootstrap.Modal(document.getElementById("detailEmployeeModal")).show();
    }
    if (e.target.closest(".btn-danger")) {
      if (confirm("Bạn có chắc chắn muốn xóa nhân viên này không?")) {
        row.remove();
        updateSTT();
      }
    }
  });

  // Cập nhật lại STT
  function updateSTT() {
    document.querySelectorAll("#employee-table tbody tr").forEach((row, index) => {
      row.children[0].innerText = index + 1;
    });
  }

  let currentRow; // Lưu trữ dòng đang được chỉnh sửa

  // Hàm hiển thị chi tiết nhân viên
  function showEmployeeDetails(row) {
    currentRow = row; // Lưu dòng hiện tại
    document.getElementById("detail-name").innerText = row.children[1].innerText;
    document.getElementById("detail-phone").innerText = row.children[2].innerText;
    document.getElementById("detail-email").innerText = row.children[3].innerText;
    document.getElementById("detail-status").innerText = row.children[4].innerText;

    // Gán giá trị cho form chỉnh sửa
    document.getElementById("edit-name").value = row.children[1].innerText;
    document.getElementById("edit-phone").value = row.children[2].innerText;
    document.getElementById("edit-email").value = row.children[3].innerText;
    document.getElementById("edit-status").value = row.children[4].querySelector("span").classList[1].split("-")[1];
  }

  // Sự kiện khi nhấn nút "Sửa"
  document.getElementById("edit-button").addEventListener("click", function () {
    // Ẩn chế độ xem chi tiết và hiển thị form chỉnh sửa
    document.getElementById("detail-view").classList.add("d-none");
    document.getElementById("edit-employee-form").classList.remove("d-none");
    document.getElementById("edit-button").classList.add("d-none");
    document.getElementById("save-edit-button").classList.remove("d-none");
  });

  // Sự kiện khi nhấn nút "Lưu"
  document.getElementById("save-edit-button").addEventListener("click", function () {
    // Lấy thông tin từ form chỉnh sửa
    const newName = document.getElementById("edit-name").value;
    const newPhone = document.getElementById("edit-phone").value;
    const newEmail = document.getElementById("edit-email").value;
    const newStatus = document.getElementById("edit-status").value;

    const statusText = {
      success: "ĐANG HOẠT ĐỘNG",
      warning: "TẠM NGHỈ",
      danger: "ĐÃ NGHỈ VIỆC",
    };

    // Cập nhật dữ liệu vào bảng
    currentRow.children[1].innerText = newName;
    currentRow.children[2].innerText = newPhone;
    currentRow.children[3].innerText = newEmail;
    currentRow.children[4].innerHTML = `<span class="badge badge-${newStatus}">${statusText[newStatus]}</span>`;

    // Đóng form chỉnh sửa và quay lại chế độ xem chi tiết
    document.getElementById("detail-view").classList.remove("d-none");
    document.getElementById("edit-employee-form").classList.add("d-none");
    document.getElementById("edit-button").classList.remove("d-none");
    document.getElementById("save-edit-button").classList.add("d-none");

    // Hiển thị thông báo
    alert("Thông tin nhân viên đã được cập nhật thành công!");
  });