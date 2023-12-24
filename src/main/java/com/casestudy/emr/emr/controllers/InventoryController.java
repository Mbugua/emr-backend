package com.casestudy.emr.emr.controllers;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.emr.emr.models.Inventory;
import com.casestudy.emr.emr.repositories.InventoryRepository;

/**
 * This class represents a Java class called 'InventoryController' that serves
 * as a RESTful API controller for managing inventory products.
 * It provides endpoints for retrieving all products, retrieving a specific
 * product by ID, creating a new product, updating an existing product, and
 * deleting a product.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Retrieves all products or products filtered by title.
     * Returns a list of products or an empty response with HTTP status code 204 if
     * no products are found.
     *
     * @param title (optional) The title to filter the products by
     * @return ResponseEntity<List<Inventory>> The response entity containing the
     *         list of products or an empty response
     */
    @GetMapping("/products")
    public ResponseEntity<List<Inventory>> getAllProducts(@RequestParam(required = false) String title) {
        try {
            List<Inventory> products = new ArrayList<Inventory>();
            if (title == null) {
                inventoryRepository.findAll().forEach(products::add);
            } else {
                inventoryRepository.findByTitle(title).forEach(products::add);
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a specific product by ID.
     * Returns the product if found or an empty response with HTTP status code 404
     * if not found.
     *
     * @param id The ID of the product to retrieve
     * @return ResponseEntity<Inventory> The response entity containing the product
     *         or an empty response
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Inventory> getProductById(@PathVariable("id") Long id) {
        Optional<Inventory> productData = inventoryRepository.findById(id);
        return productData.map(product -> new ResponseEntity<>(product,
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new product with the provided details.
     * Returns the created product with HTTP status code 201 if successful or an
     * empty response with HTTP status code 500 if an error occurs.
     *
     * @param product The product to create
     * @return ResponseEntity<Inventory> The response entity containing the created
     *         product or an empty response
     */
    @PostMapping("/products")
    public ResponseEntity<Inventory> createProduct(@RequestBody Inventory product) {
        try {
            Inventory _product = inventoryRepository
                    .save(new Inventory(
                            product.getTitle(),
                            product.getDescription(),
                            product.getBatchNo(),
                            product.getQuantity(),
                            product.getExpirationDate(),
                            product.getPrice(),
                            product.isInStock()));
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing product with the provided details.
     * Returns the updated product with HTTP status code 200 if successful or an
     * empty response with HTTP status code 404 if the product is not found.
     *
     * @param id      The ID of the product to update
     * @param product The updated product details
     * @return ResponseEntity<Inventory> The response entity containing the updated
     *         product or an empty response
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Inventory> updateProduct(@PathVariable("id") Long id, @RequestBody Inventory product) {
        Optional<Inventory> productData = inventoryRepository.findById(id);

        if (productData.isPresent()) {
            Inventory _product = productData.get();
            _product.setTitle(product.getTitle());
            _product.setDescription(product.getDescription());
            _product.setBatchNo(product.getBatchNo());
            _product.setQuantity(product.getQuantity());
            _product.setExpirationDate(product.getExpirationDate());
            _product.setPrice(product.getPrice());
            _product.setInStock(product.isInStock());
            return new ResponseEntity<>(inventoryRepository.save(_product), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a product by ID.
     * Returns an empty response with HTTP status code 204 if successful or an empty
     * response with HTTP status code 500 if an error occurs.
     *
     * @param id The ID of the product to delete
     * @return ResponseEntity<HttpStatus> The response entity containing an empty
     *         response
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            inventoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes all products.
     * Returns an empty response with HTTP status code 204 if successful or an empty
     * response with HTTP status code 500 if an error occurs.
     *
     * @return ResponseEntity<HttpStatus> The response entity containing an empty
     *         response
     */
    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            inventoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
