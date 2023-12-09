package TokoHp.Objects;

import static TokoHp.Objects.Variable.toastSuccess;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        // Cek apakah produk sudah ada di keranjang
        for (Product item : items) {
            if (item.getIdProduk() == product.getIdProduk()) {
                // Jika sudah ada, tambahkan jumlahnya
                Variable.popUpErrorMessage("Barang sudah ada", "Anda hanya dapat menambah stok di keranjang jika barang sudah ada");
                return;
            }
        }
        items.add(product);
        toastSuccess("Ditambah ke keranjang");
    }

    public void updateItemQuantity(Product product, int stokBaru) {
        // Perbarui jumlah produk dalam keranjang
        for (Product item : items) {
            if (item.getIdProduk() == product.getIdProduk()) {
                item.setStok(stokBaru);
                toastSuccess("Jumlah barang diperbarui");
                return;
            }
        }
        Variable.popUpErrorMessage("Produk tidak ada", "Silahkan tambah produk terlebih dahulu");
    }

    public void removeItem(Product product) {
        // Hapus produk dari keranjang
        for (Product item : items) {
            if (product.getIdProduk() == item.getIdProduk()) {
                items.remove(item);
                return;
            }
        }
    }

    public List<Product> getItems() {
        return items;
    }

    public int calculateTotal() {
        // Hitung total belanja
        int total = 0;

        for (Product item : items) {
            total += item.getHarga() * item.getStok();
        }
        return total;
    }
}
