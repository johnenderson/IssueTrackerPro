package com.issuetracker.engine.controller;

import com.issuetracker.engine.dto.SubscriptionTypeDto;
import com.issuetracker.engine.model.SubscriptionType;
import com.issuetracker.engine.service.SubscriptionTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*
* Pode ser utilizado o @CachePut(), porem é menos performatico. Então de mercado é mais comum utilizar o @CacheEvict(value = "", allEntries = true)
*/

@RestController
@RequestMapping("/subscription-type")
public class SubscriptionTypeController {

    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    @Cacheable(value = "subscriptionType")
    @GetMapping
    public ResponseEntity<List<SubscriptionType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findAll());

    }

    @Cacheable(value = "subscriptionType", key = "#id")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findById(id));
    }

    @CacheEvict(value = "subscriptionType", allEntries = true)
    @PostMapping
    public  ResponseEntity<SubscriptionType> create(@Valid @RequestBody SubscriptionTypeDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionTypeService.create(dto));

    }

    @CacheEvict(value = "subscriptionType", allEntries = true)
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionType> update(@PathVariable("id") Long id, @RequestBody SubscriptionTypeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.update(id, dto ));
    }

    @CacheEvict(value = "subscriptionType", allEntries = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subscriptionTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
