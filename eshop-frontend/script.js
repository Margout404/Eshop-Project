const API_URL = "http://localhost:8080/api";
let currentUser = { afm: 0, role: null };

// --- NAVIGATION ---
function showSection(id) {
    document.querySelectorAll('.container > div').forEach(div => {
        if (div.id !== 'message-box') div.classList.add('hidden');
    });
    document.getElementById(id).classList.remove('hidden');
    hideMessage();
}

function showMessage(msg, type) {
    const box = document.getElementById('message-box');
    box.textContent = msg;
    box.className = type === 'success' ? 'success' : 'error';
    box.style.display = 'block';
    setTimeout(() => box.style.display = 'none', 3000);
}
function hideMessage() { document.getElementById('message-box').style.display = 'none'; }

// --- AUTH ---
async function login() {
    const afm = document.getElementById('login-afm').value;
    const password = document.getElementById('login-pass').value;

    try {
        const res = await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ afm, password })
        });
        const data = await res.json();

        if (data.success) {
            currentUser = { afm: data.afm, role: data.role };
            if (data.role === 'STORE') {
                document.getElementById('store-name-display').textContent = data.name;
                showSection('store-dashboard');
                loadStoreItems();
            } else {
                document.getElementById('citizen-name-display').textContent = data.name;
                showSection('citizen-dashboard');
                loadCart();
                loadAllProducts(); // <--- ΑΥΤΟ ΦΟΡΤΩΝΕΙ ΤΑ ΠΡΟΪΟΝΤΑ ΑΥΤΟΜΑΤΑ
            }
        } else {
            showMessage(data.message, 'error');
        }
    } catch (e) { showMessage("Σφάλμα σύνδεσης server", 'error'); }
}

async function register(endpoint, dto) {
    try {
        const res = await fetch(API_URL + endpoint, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        });
        if (res.ok) {
            showMessage("Εγγραφή επιτυχής! Κάντε σύνδεση.", 'success');
            showSection('login-section');
        } else {
            showMessage("Σφάλμα εγγραφής.", 'error');
        }
    } catch (e) { showMessage("Network Error", 'error'); }
}

async function registerCitizen() {
    const dto = {
        afm: document.getElementById('reg-cit-afm').value,
        firstName: document.getElementById('reg-cit-fname').value,
        lastName: document.getElementById('reg-cit-lname').value,
        email: document.getElementById('reg-cit-email').value,
        password: document.getElementById('reg-cit-pass').value
    };
    await register('/citizens', dto);
}

async function registerStore() {
    const dto = {
        afm: document.getElementById('reg-store-afm').value,
        storeName: document.getElementById('reg-store-name').value,
        owner: document.getElementById('reg-store-owner').value,
        password: document.getElementById('reg-store-pass').value
    };
    await register('/stores', dto);
}

function logout() {
    currentUser = { afm: 0, role: null };
    document.getElementById('login-afm').value = '';
    document.getElementById('login-pass').value = '';
    showSection('login-section');
}

// --- STORE FUNCTIONS ---
async function addProduct() {
    const dto = {
        name: document.getElementById('prod-name').value,
        brand: document.getElementById('prod-brand').value,
        description: document.getElementById('prod-desc').value,
        price: parseFloat(document.getElementById('prod-price').value),
        quantity: parseInt(document.getElementById('prod-qty').value)
    };

    const res = await fetch(`${API_URL}/stores/${currentUser.afm}/items`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dto)
    });

    if (res.ok) {
        showMessage("Το προϊόν προστέθηκε!", 'success');
        loadStoreItems();
        document.getElementById('prod-name').value = '';
    } else {
        showMessage("Σφάλμα προσθήκης.", 'error');
    }
}

async function loadStoreItems() {
    const res = await fetch(`${API_URL}/stores/${currentUser.afm}/items`);
    const items = await res.json();
    const list = document.getElementById('store-items-list');
    list.innerHTML = items.map(i => `
        <div class="list-item">
            <span><b>${i.name}</b> (${i.quantity} τμχ) - ${i.price}€</span>
        </div>
    `).join('');
}


async function loadAllProducts() {
    // Αν δεν έβαλες το endpoint "/all", δοκίμασε το: "/items/search?name="
    try {
        const res = await fetch(`${API_URL}/items/all`); 
        if(!res.ok) throw new Error("Failed");
        const items = await res.json();
        renderProducts(items);
    } catch(e) {
        // Fallback αν δεν έβαλες το /all
        searchItems("");
    }
}

async function searchItems(forceTerm) {
    let term = document.getElementById('search-term').value;
    if (forceTerm !== undefined) term = forceTerm;
    
    const res = await fetch(`${API_URL}/items/search?name=${term}`);
    const items = await res.json();
    renderProducts(items);
}

function renderProducts(items) {
    const list = document.getElementById('all-products-list');
    if (items.length === 0) {
        list.innerHTML = "<p>Δεν βρέθηκαν προϊόντα.</p>";
        return;
    }
    list.innerHTML = items.map(i => `
        <div class="list-item">
            <div>
                <b>${i.name}</b> <span style="color:#666">(${i.brand})</span> - <b style="color:#28a745">${i.price}€</b><br>
                <small>${i.description}</small> <br>
                <small>Απόθεμα: ${i.quantity}</small>
            </div>
            <div style="text-align:right">
                <input type="number" id="qty-${i.itemId}" value="1" min="1" style="width: 50px; text-align:center;">
                <button onclick="addToCart(${i.itemId})" style="width: auto; padding: 5px 10px;">+</button>
            </div>
        </div>
    `).join('');
}

async function addToCart(itemId) {
    const qty = document.getElementById(`qty-${itemId}`).value;
    const dto = {
        citizenAfm: currentUser.afm,
        itemId: itemId,
        quantity: parseInt(qty)
    };

    const res = await fetch(`${API_URL}/cart/items`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(dto)
    });

    if (res.ok) {
        showMessage("Προστέθηκε στο καλάθι!", 'success');
        loadCart();
    } else {
        showMessage("Σφάλμα (ίσως δεν υπάρχει απόθεμα)", 'error');
    }
}

async function loadCart() {
    try {
        const res = await fetch(`${API_URL}/cart/${currentUser.afm}`);
        const cart = await res.json();
        
        document.getElementById('cart-total').textContent = cart.totalPrice.toFixed(2);
        
        const list = document.getElementById('cart-items');
        if (cart.items && cart.items.length > 0) {
            list.innerHTML = cart.items.map(i => `
                <div style="border-bottom: 1px dotted #ccc; padding: 5px; display:flex; justify-content:space-between;">
                    <span>${i.name} x ${i.quantity}</span>
                    <span><b>${(i.price * i.quantity).toFixed(2)}€</b></span>
                </div>
            `).join('');
        } else {
            list.innerHTML = "Το καλάθι είναι άδειο.";
        }
    } catch(e) { console.log(e); }
}

async function checkout() {
    const res = await fetch(`${API_URL}/cart/checkout/${currentUser.afm}`, { method: 'POST' });
    if (res.ok) {
        showMessage("Η αγορά ολοκληρώθηκε επιτυχώς!", 'success');
        loadCart();
        loadAllProducts(); // Ανανέωσε τη λίστα για να φανούν τα μειωμένα αποθέματα
    } else {
        showMessage("Σφάλμα κατά την αγορά.", 'error');
    }
}