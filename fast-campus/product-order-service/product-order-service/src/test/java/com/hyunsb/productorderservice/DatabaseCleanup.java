package com.hyunsb.productorderservice;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseCleanup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> {
                    String tableName = e.getJavaType().getAnnotation(Table.class).name();
                    return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()) : tableName;
                })
                .collect(Collectors.toList());

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .toList();

        tableNames.addAll(entityNames);
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        // 참조 무결성 무시 H2 방식이라 MySQL 언어로 변경 필요
//        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        entityManager.createNativeQuery("SET @@foreign_key_checks = 0").executeUpdate();

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
//            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate();
        }

//        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        entityManager.createNativeQuery("SET @@foreign_key_checks = 1").executeUpdate();
    }

}
