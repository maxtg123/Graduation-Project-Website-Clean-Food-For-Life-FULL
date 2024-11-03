package com.example.Graduation_Project_Website_Clean_Food_For_Life.controller;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.Exception.ResourceNotFoundException;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.ProductDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.ProductDetailDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Category;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Product;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.ProductRepository;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.service.CategoryService;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/list")
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getImageUrl(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }


    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String fileName) {
        Path filePath = Paths.get("uploads/images", fileName); // Đường dẫn đến thư mục chứa hình ảnh

        try {
            byte[] imageBytes = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Điều chỉnh kiểu MIME nếu cần
                    .body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


   @GetMapping("/{id}")
   public ResponseEntity<Product> getProductDetails(@PathVariable Long id) {
       Product product = productService.getProductById(id);
      return ResponseEntity.ok(product);
   }






    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam("category_id") Long categoryId,
                                                 @RequestParam("image") MultipartFile image) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Category category = optionalCategory.get();

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        if (image != null && !image.isEmpty()) {
            try {
                // Set the directory path to store images
                String uploadDir = "uploads/images";
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);

                // Save the image file to the directory
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, image.getBytes());

                // Save the relative file path in the database
                product.setImageUrl("/api/products/image/" + fileName);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }


}
