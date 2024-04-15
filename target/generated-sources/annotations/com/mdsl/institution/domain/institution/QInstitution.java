package com.mdsl.institution.domain.institution;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInstitution is a Querydsl query type for Institution
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInstitution extends EntityPathBase<Institution> {

    private static final long serialVersionUID = 1990881855L;

    public static final QInstitution institution = new QInstitution("institution");

    public final com.mdsl.institution.domain.common.base.QBaseEntity _super = new com.mdsl.institution.domain.common.base.QBaseEntity(this);

    //inherited
    public final StringPath authorizedBy = _super.authorizedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> authorizedDate = _super.authorizedDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<java.math.BigDecimal> id = createNumber("id", java.math.BigDecimal.class);

    public final StringPath institutionCode = createString("institutionCode");

    public final StringPath institutionName = createString("institutionName");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final EnumPath<com.mdsl.institution.domain.common.RecordStatus> recordStatus = _super.recordStatus;

    public QInstitution(String variable) {
        super(Institution.class, forVariable(variable));
    }

    public QInstitution(Path<? extends Institution> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstitution(PathMetadata metadata) {
        super(Institution.class, metadata);
    }

}

