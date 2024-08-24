package com.example.sqlitep2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitep2.data.dao.ProductDao;
import com.example.sqlitep2.data.dao.UserDao;
import com.example.sqlitep2.data.db.DatabaseManager;
import com.example.sqlitep2.data.model.Product;
import com.example.sqlitep2.data.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OUT1";
    DatabaseManager dbManager;
    private UserDao userDao;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseManager dbManager = DatabaseManager.getInstance(this);
        userDao = new UserDao(dbManager.openDatabase());
        productDao = new ProductDao(dbManager.openDatabase());

        // Ejemplo de uso de UserDao
        exampleUserCrud();

        // Ejemplo de uso de ProductDao
        exampleProductCrud();
    }

    private void exampleUserCrud() {
        // CREATE - Insertar 3 usuarios colombianos
        User user1 = new User("Carlos Rodríguez", "carlos.rodriguez@gmail.com");
        User user2 = new User("María López", "maria.lopez@hotmail.com");
        User user3 = new User("Andrés Gómez", "andres.gomez@outlook.com");

        long userId1 = userDao.insert(user1);
        long userId2 = userDao.insert(user2);
        long userId3 = userDao.insert(user3);

        Log.d(TAG, "Insertados usuarios con IDs: " + userId1 + ", " + userId2 + ", " + userId3);

        // READ - Obtener todos los usuarios
        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            Log.d(TAG, "Usuario: " + user.getUsername() + ", Correo: " + user.getEmail());
        }

        // UPDATE - Actualizar el segundo usuario
        User userToUpdate = userDao.getUserById(userId2);
        if (userToUpdate != null) {
            userToUpdate.setEmail("maria.lopez.nueva@gmail.com");
            int updatedRows = userDao.update(userToUpdate);
            Log.d(TAG, "Actualizados " + updatedRows + " usuario(s)");
        }

        // DELETE - Eliminar el tercer usuario
        int deletedRows = userDao.delete(userId3);
        Log.d(TAG, "Eliminados " + deletedRows + " usuario(s)");

        // READ - Obtener todos los usuarios nuevamente para verificar los cambios
        allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            Log.d(TAG, "Después de operaciones - Usuario: " + user.getUsername() + ", Correo: " + user.getEmail());
        }
    }

    private void exampleProductCrud() {
        // CREATE - Insertar 3 productos típicos colombianos
        Product product1 = new Product("Café Juan Valdez", 25000);  // Precio en pesos colombianos
        Product product2 = new Product("Arepa de chócolo", 3500);
        Product product3 = new Product("Sombrero vueltiao", 80000);

        long productId1 = productDao.insert(product1);
        long productId2 = productDao.insert(product2);
        long productId3 = productDao.insert(product3);

        Log.d(TAG, "Insertados productos con IDs: " + productId1 + ", " + productId2 + ", " + productId3);

        // READ - Obtener todos los productos
        List<Product> allProducts = productDao.getAllProducts();
        for (Product product : allProducts) {
            Log.d(TAG, "Producto: " + product.getName() + ", Precio: $" + product.getPrice() + " COP");
        }

        // UPDATE - Actualizar el segundo producto
        Product productToUpdate = productDao.getProductById(productId2);
        if (productToUpdate != null) {
            productToUpdate.setPrice(4000);  // Actualizar precio
            int updatedRows = productDao.update(productToUpdate);
            Log.d(TAG, "Actualizados " + updatedRows + " producto(s)");
        }

        // DELETE - Eliminar el tercer producto
        int deletedRows = productDao.delete(productId3);
        Log.d(TAG, "Eliminados " + deletedRows + " producto(s)");

        // READ - Obtener todos los productos nuevamente para verificar los cambios
        allProducts = productDao.getAllProducts();
        for (Product product : allProducts) {
            Log.d(TAG, "Después de operaciones - Producto: " + product.getName() + ", Precio: $" + product.getPrice() + " COP");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDatabase();
    }
}