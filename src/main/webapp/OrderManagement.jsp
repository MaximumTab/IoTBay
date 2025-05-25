<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <title>Order Management</title>
    <!-- 其余 head 内容… -->
    <!DOCTYPE html>
    <html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>Order Management</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- 引入 Bootstrap 5 CSS -->
        <link
                href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                rel="stylesheet"
        >
    </head>
    <body class="bg-light">

    <div class="container py-4">
        <div class="row">
            <!-- 左侧：创建 / 更新 订单 -->
            <div class="col-md-6 mb-3">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h4 class="card-title">Create Order</h4>
                        <form id="orderForm">
                            <div class="mb-3">
                                <label for="deviceSelect" class="form-label">Choose Devices</label>
                                <select id="deviceSelect" class="form-select"></select>
                            </div>
                            <div class="mb-3">
                                <label for="quantityInput" class="form-label">quantity</label>
                                <input
                                        type="number"
                                        id="quantityInput"
                                        class="form-control"
                                        min="1"
                                        value="1"
                                        required
                                >
                            </div>
                            <input type="hidden" id="editingOrderId" value="">
                            <div>
                                <button type="submit" class="btn btn-primary" id="submitBtn">
                                    Submit order
                                </button>
                                <button type="button" class="btn btn-secondary d-none" id="cancelEditBtn">
                                    Cancel edit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- 右侧：我的订单 列表 & 搜索 -->
            <div class="col-md-6 mb-3">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h4 class="card-title">My order</h4>

                        <!-- 搜索框 -->
                        <div class="d-flex mb-2">
                            <input
                                    type="text"
                                    id="searchInput"
                                    class="form-control form-control-sm me-2"
                                    placeholder="Search by order number or device"
                            >
                            <button class="btn btn-secondary btn-sm" id="searchBtn">Search</button>
                            <button class="btn btn-outline-secondary btn-sm ms-2" id="clearSearchBtn">
                                Clear
                            </button>
                        </div>

                        <!-- 订单表格 -->
                        <div class="table-responsive">
                            <table class="table table-hover align-middle" id="orderTable">
                                <thead class="table-light">
                                <tr>
                                    <th>Order number</th>
                                    <th>Order time</th>
                                    <th>Status &amp; Details</th>
                                    <th>Operation</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>

                        <!-- 提交所有 saved 订单 按钮 -->
                        <button class="btn btn-success mt-2 d-none" id="submitAllBtn">
                            Submit all orders
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 依赖的 Popper 和 JS（可选）-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // === 模拟的设备数据 ===
        const devices = [
            { id: 1, name: 'device 1', stock: 10, price: 1000 },
            { id: 2, name: 'device 2', stock: 5,  price:  500 },
            { id: 3, name: 'device 3', stock: 20, price: 1500 }
        ];

        // 存储订单的数组
        let orders = [];
        let nextOrderId = 1;  // 简单自增 ID

        // 页面元素
        const deviceSelect    = document.getElementById('deviceSelect');
        const quantityInput   = document.getElementById('quantityInput');
        const orderForm       = document.getElementById('orderForm');
        const submitBtn       = document.getElementById('submitBtn');
        const cancelEditBtn   = document.getElementById('cancelEditBtn');
        const editingOrderId  = document.getElementById('editingOrderId');
        const orderTableBody  = document.querySelector('#orderTable tbody');
        const searchInput     = document.getElementById('searchInput');
        const searchBtn       = document.getElementById('searchBtn');
        const clearSearchBtn  = document.getElementById('clearSearchBtn');
        const submitAllBtn    = document.getElementById('submitAllBtn');

        // 初始化设备下拉
        function initDeviceSelect() {
            deviceSelect.innerHTML = devices.map(d =>
                `<option value="${d.id}">
      ${d.name} (inventory：${d.stock}) - ¥${d.price}
    </option>`
            ).join('');
        }

        // 渲染订单列表
        function renderOrders(filterKeyword = '') {
            const kw = filterKeyword.trim().toLowerCase();
            const filtered = orders.filter(o => {
                if (!kw) return true;
                return o.id.toString().includes(kw) ||
                    o.device.name.toLowerCase().includes(kw);
            });

            // 构建表格行
            orderTableBody.innerHTML = filtered.map(o => {
                const timeStr = new Date(o.time).toLocaleString();
                const detail  = `<ul class="mb-1"><li>${o.device.name} × ${o.quantity}</li></ul>`;
                // 操作按钮：仅 saved 状态可编辑/取消
                let ops = '-';
                if (o.status === 'saved') {
                    ops = `
        <button class="btn btn-sm btn-outline-primary me-1"
                data-id="${o.id}" onclick="startEdit(${o.id})">
          Edit
        </button>
        <button class="btn btn-sm btn-outline-danger"
                data-id="${o.id}" onclick="cancelOrder(${o.id})">
          Cancel
        </button>`;
                }
                return `
      <tr>
        <td>${o.id}</td>
        <td>${timeStr}</td>
        <td>
          <span class="text-capitalize">${o.status}</span>
          ${detail}
        </td>
        <td>${ops}</td>
      </tr>`;
            }).join('');

            // 控制「提交所有」按钮显示
            const hasSaved = orders.some(o => o.status === 'saved');
            submitAllBtn.classList.toggle('d-none', !hasSaved);
        }

        // 提交／更新表单
        orderForm.addEventListener('submit', e => {
            e.preventDefault();
            const devId = +deviceSelect.value;
            const qty   = +quantityInput.value;
            const dev   = devices.find(d => d.id === devId);
            const editId = editingOrderId.value;

            if (editId) {
                // 更新订单
                const o = orders.find(x => x.id === +editId);
                o.device   = dev;
                o.quantity = qty;
            } else {
                // 新建订单
                orders.push({
                    id: nextOrderId++,
                    time: Date.now(),
                    status: 'saved',
                    device: dev,
                    quantity: qty
                });
            }

            // 重置表单状态
            resetForm();
            renderOrders(searchInput.value);
        });

        // 开始编辑
        function startEdit(id) {
            const o = orders.find(x => x.id === id);
            if (!o || o.status !== 'saved') return;
            editingOrderId.value = o.id;
            deviceSelect.value  = o.device.id;
            quantityInput.value = o.quantity;
            submitBtn.textContent = 'Update order';
            cancelEditBtn.classList.remove('d-none');
        }

        // 取消编辑
        cancelEditBtn.addEventListener('click', () => {
            resetForm();
        });

        // 取消单个订单
        function cancelOrder(id) {
            const idx = orders.findIndex(x => x.id === id);
            if (idx < 0) return;
            orders[idx].status = 'canceled';
            renderOrders(searchInput.value);
        }

        // 提交所有 saved 订单
        submitAllBtn.addEventListener('click', () => {
            orders.forEach(o => {
                if (o.status === 'saved') o.status = 'submitted';
            });
            renderOrders(searchInput.value);
        });

        // 重置表单到初始创建状态
        function resetForm() {
            editingOrderId.value = '';
            orderForm.reset();
            quantityInput.value = 1;
            submitBtn.textContent = 'Submit order';
            cancelEditBtn.classList.add('d-none');
        }

        // 搜索
        searchBtn.addEventListener('click', () => {
            renderOrders(searchInput.value);
        });
        clearSearchBtn.addEventListener('click', () => {
            searchInput.value = '';
            renderOrders();
        });

        // 页面加载时初始化
        window.onload = () => {
            initDeviceSelect();
            renderOrders();
        };
    </script>

    </body>
    </html>

