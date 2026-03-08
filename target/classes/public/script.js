const apiUrl = '/api/products';

// Ambil element form & tabel
const productForm = document.getElementById('productForm');
const tableBody = document.getElementById('productTableBody');
const btnExport = document.getElementById('btnExport');
const btnSave = document.getElementById('btnSave');
const emptyState = document.getElementById('emptyState');
const toastContainer = document.getElementById('toastContainer');

// Sistem Toast Notification Kustom
function showToast(message, type = 'success') {
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    
    const icon = type === 'success' ? 'fa-check-circle' : 'fa-exclamation-circle';
    toast.innerHTML = `<i class="fa-solid ${icon}"></i> <span>${message}</span>`;
    
    toastContainer.appendChild(toast);
    
    // Trigger animation
    setTimeout(() => toast.classList.add('show'), 10);
    
    // Remove after 3s
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 400); // wait for fade out
    }, 3000);
}

// Format Harga ke Rupiah
const formatRp = (number) => {
    return new Intl.NumberFormat('id-ID', {
        style: 'currency',
        currency: 'IDR',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    }).format(number);
};

// Fetch dan render data
async function loadProducts() {
    try {
        const response = await fetch(apiUrl);
        const products = await response.json();
        
        tableBody.innerHTML = '';
        
        if (products.length === 0) {
            emptyState.style.display = 'block';
            document.querySelector('table').style.display = 'none';
        } else {
            emptyState.style.display = 'none';
            document.querySelector('table').style.display = 'table';
            
            products.forEach((product, index) => {
                const tr = document.createElement('tr');
                // Staggered animation delay
                tr.style.animationDelay = `${index * 0.05}s`;
                tr.innerHTML = `
                    <td><strong>#${product.id}</strong></td>
                    <td>${product.name}</td>
                    <td><span style="background: #e5e7eb; padding: 4px 8px; border-radius: 4px; font-size: 0.85rem;">${product.category}</span></td>
                    <td>${product.quantity}</td>
                    <td style="font-weight: 500;">${formatRp(product.price)}</td>
                    <td class="text-center">
                        <button class="btn btn-danger" onclick="deleteProduct(${product.id})" title="Hapus">
                            <i class="fa-solid fa-trash-can"></i>
                        </button>
                    </td>
                `;
                tableBody.appendChild(tr);
            });
        }
    } catch (error) {
        showToast('Gagal memuat data produk.', 'error');
    }
}

// Tambah produk baru
productForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    // UI State: Loading
    const originalText = btnSave.innerHTML;
    btnSave.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Menyimpan...';
    btnSave.disabled = true;
    
    const newProduct = {
        name: document.getElementById('name').value,
        category: document.getElementById('category').value,
        quantity: parseInt(document.getElementById('qty').value),
        price: parseFloat(document.getElementById('price').value)
    };
    
    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newProduct)
        });
        
        if (response.ok) {
            showToast('Produk berhasil ditambahkan!');
            productForm.reset();
            loadProducts();
        } else {
            throw new Error('Server error');
        }
    } catch (error) {
        showToast('Gagal menyimpan produk.', 'error');
    } finally {
        // Reset UI
        btnSave.innerHTML = originalText;
        btnSave.disabled = false;
    }
});

// Hapus produk
async function deleteProduct(id) {
    // Kustom confirm bisa dibuat, tapi untuk safety kita pakai confirm bawaan dengan logika UX yang baik
    if (confirm('Yakin ingin menghapus produk ini secara permanen?')) {
        try {
            const response = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
            if (response.ok) {
                showToast('Produk berhasil dihapus.');
                loadProducts();
            }
        } catch (error) {
            showToast('Gagal menghapus produk.', 'error');
        }
    }
}

// Ekspor ke CSV
btnExport.addEventListener('click', async () => {
    const originalText = btnExport.innerHTML;
    btnExport.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Mengekspor...';
    btnExport.disabled = true;
    
    try {
        const response = await fetch('/api/export', { method: 'POST' });
        if (response.ok) {
            showToast('Data berhasil diekspor ke inventory_report.csv');
        } else {
            throw new Error('Export failed');
        }
    } catch (error) {
        showToast('Gagal mengekspor data.', 'error');
    } finally {
        btnExport.innerHTML = originalText;
        btnExport.disabled = false;
    }
});

// Load awal
loadProducts();

