package com.example.likelionspringboot.domain.base.attr.repository;

import com.example.likelionspringboot.domain.base.attr.entity.Attr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttrRepository extends JpaRepository<Attr, Long> {
}
