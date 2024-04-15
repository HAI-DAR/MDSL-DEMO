package com.mdsl.institution.dto.common.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDto is a Querydsl query type for BaseDto
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDto extends EntityPathBase<BaseDto> {

    private static final long serialVersionUID = 1263163006L;

    public static final QBaseDto baseDto = new QBaseDto("baseDto");

    public final StringPath authorizedBy = createString("authorizedBy");

    public final DateTimePath<java.time.LocalDateTime> authorizedDate = createDateTime("authorizedDate", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath recordStatus = createString("recordStatus");

    public QBaseDto(String variable) {
        super(BaseDto.class, forVariable(variable));
    }

    public QBaseDto(Path<? extends BaseDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDto(PathMetadata metadata) {
        super(BaseDto.class, metadata);
    }

}

