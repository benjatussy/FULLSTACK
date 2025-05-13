package com.usuario_gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.usuario_gateway.model.Usuario;
import com.usuario_gateway.service.User_service;

@RestController
@RequestMapping("/api/v1/usuarios")
public class Usuario_controller {

    @Autowired
    private User_service userService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Usuario user = userService.getById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Usuario no encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> add(@RequestBody Usuario user){
        Usuario newUser = userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Usuario user){
        Usuario updatedUser = userService.update(id, user);
        if (updatedUser != null){
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Usuario no encontrado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Usuario deletedUser = userService.delete(id);
        if (deletedUser != null){
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Usuario no encontrado"));
        }
    }




}
