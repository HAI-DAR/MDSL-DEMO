package com.mdsl.institution.domain.businessmessage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessMessage is a Querydsl query type for BusinessMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessMessage extends EntityPathBase<BusinessMessage> {

    private static final long serialVersionUID = 700530493L;

    public static final QBusinessMessage businessMessage = new QBusinessMessage("businessMessage");

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

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> msgCode = createNumber("msgCode", Integer.class);

    public final StringPath msgDescriptionAr = createString("msgDescriptionAr");

    public final StringPath msgDescriptionEn = createString("msgDescriptionEn");

    public final StringPath msgDescriptionFr = createString("msgDescriptionFr");

    public final StringPath msgTitleAr = createString("msgTitleAr");

    public final StringPath msgTitleEn = createString("msgTitleEn");

    public final StringPath msgTitleFr = createString("msgTitleFr");

    //inherited
    public final EnumPath<com.mdsl.institution.domain.common.RecordStatus> recordStatus = _super.recordStatus;

    public QBusinessMessage(String variable) {
        super(BusinessMessage.class, forVariable(variable));
    }

    public QBusinessMessage(Path<? extends BusinessMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessMessage(PathMetadata metadata) {
        super(BusinessMessage.class, metadata);
    }

}

